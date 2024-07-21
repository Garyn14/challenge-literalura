CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birth_year INTEGER,
    death_year INTEGER
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id INTEGER,
    language VARCHAR(50),
    download_count BIGINT,
    CONSTRAINT fk_author
        FOREIGN KEY(author_id)
        REFERENCES authors(id)
);
