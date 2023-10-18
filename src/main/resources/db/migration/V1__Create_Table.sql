CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255),
    is_agreed_to_terms BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS sector_item (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    is_leaf BOOLEAN NOT NULL default FALSE,
    parent_id INT,
    FOREIGN KEY (parent_id) REFERENCES sector_item(id)
);

CREATE TABLE IF NOT EXISTS users_sectors (
    id SERIAL PRIMARY KEY NOT NULL,
    users_id INT,
    sector_item_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id),
    FOREIGN KEY (sector_item_id) REFERENCES sector_item(id)
);