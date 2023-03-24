CREATE TABLE tasks (
    id bigserial PRIMARY KEY,
    author varchar(50),
    room varchar(50),
    description text
);