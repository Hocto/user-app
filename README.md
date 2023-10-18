User App

1. Correct all of the deficiencies in index.html(React app created in ui folder)

2. "Sectors" selectbox:
   2.1. Add all the entries from the "Sectors" selectbox to database
   2.2. Compose the "Sectors" selectbox using data from database

3. Perform the following activities after the "Save" button has been pressed:
   3.1. Validate all input data (all fields are mandatory)
   3.2. Store all input data to database (Name, Sectors, Agree to terms)
   3.3. Refill the form using stored data
   3.4. Allow the user to edit his/her own data during the session

Steps to initialize server and database:

- ./gradlew build -x test
- docker image build -t user-app .
- docker-compose up -d

Steps to initialize UI:

- 'cd ui/ && npm install && npm start'

Application server will be ready on 8080 port.
UI will be ready on 3000 port.
User database dump exists as 'user_db_dump.sql' in root path
You can see the API doc here(http://localhost:8080/swagger-ui/) 