import './App.css';
import React, { useState, useEffect } from 'react';

function App() {
  const [name, setName] = useState('');
  const [selectedSectors, setSelectedSectors] = useState([]);
  const [agreeToTerms, setAgreeToTerms] = useState(false);
  const [allSectors, setAllSectors] = useState([]);
  const [savedUserData, setSavedUserData] = useState(null);

  const handleSectorChange = (e) => {
    const selectedOptions = Array.from(e.target.selectedOptions);
    const selectedValues = selectedOptions.map((option) => option.value);
    setSelectedSectors(selectedValues);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (selectedSectors.length < 1 || selectedSectors.length > 5) {
        alert('Please select between 1 and 5 sectors.');
        return;
      }

    fetch('http://localhost:8080/api/v1/user', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: name,
        sectorItemIds: selectedSectors,
        agreeToTerms: agreeToTerms,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setSavedUserData(data.user);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  useEffect(() => {
    fetch('http://localhost:8080/api/v1/sectorItems')
      .then((response) => response.json())
      .then((data) => {
        setAllSectors(data.sectorItems);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div>
      <h1>Please enter your name and pick the Sectors you are currently involved in.</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
        <br />
        <br />
        <label htmlFor="sectorItems">Sectors:</label>
        <select
          id="sectorItems"
          name="sectorItems"
          multiple
          size="5"
          value={selectedSectors}
          onChange={handleSectorChange}
          required
        >
          {allSectors.map((sector) => (
            <option key={sector.id} value={sector.id}>
              {sector.name}
            </option>
          ))}
        </select>
        <br />
        <br />
        <label htmlFor="agree">Agree to terms:</label>
        <input
          type="checkbox"
          id="agree"
          name="agreeToTerms"
          checked={agreeToTerms}
          onChange={(e) => setAgreeToTerms(e.target.checked)}
          required
        />
        <br />
        <br />
        <input type="submit" value="Save" />
      </form>
      {savedUserData && (
        <div>
          <h2>Saved User Data</h2>
          <p>ID: {savedUserData.id}</p>
          <p>Name: {savedUserData.name}</p>
          <p>
            Sectors: {savedUserData.sectorItems.map((sector) => sector.name).join(', ')}
          </p>
          <p>Agreed to Terms: {savedUserData.agreedToTerms ? 'Yes' : 'No'}</p>
        </div>
      )}
    </div>
  );
}

export default App;
