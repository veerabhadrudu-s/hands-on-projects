DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS address;
 
CREATE TABLE users (
  id INT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);
 
CREATE TABLE address (
  user_id INT,
  address_type VARCHAR(250) NOT NULL,
  location VARCHAR(250) NOT NULL,
  country VARCHAR(250) DEFAULT NULL,
  FOREIGN KEY(user_id) REFERENCES users(id)
);