CREATE TABLE IF NOT EXISTS book(
    UUID VARCHAR(50) NOT NULL PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    AUTHOR VARCHAR(50) NOT NULL,
    PUBLISHER VARCHAR(50),
    PUBLISH_DATE DATE NOT NULL
);