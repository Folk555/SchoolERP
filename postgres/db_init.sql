CREATE TABLE tasks (
    id bigserial PRIMARY KEY,
    author varchar(50),
    room varchar(50),
    description text
);

CREATE TABLE users (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE roles (
  id bigserial PRIMARY KEY,
  role_name VARCHAR(100) NOT NULL
);

CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  role_id bigint NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username),
  FOREIGN KEY (role_id) REFERENCES roles(id),
  UNIQUE (username, role_id)
);

INSERT INTO roles(id, role_name) VALUES
(1, 'USER'),
(2, 'ADMIN');

--пароли admin и user соответственно
INSERT INTO users(username, password) VALUES
('admin', '$2a$12$Uqy7EslaqhCXSge4CEXfO.PdRGwOKsGcnIovONJrBjegraJY4mXrK'),
('user', '$2a$12$usRLMoGl3/XHSwFjiH7Jz.uvf5cTFDr4F8jTBjDPvQDFM3pJ3TSvG');

INSERT INTO authorities(username, role_id) VALUES
('admin', 2),
('user', 1);