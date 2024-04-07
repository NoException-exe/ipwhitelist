CREATE TABLE IF NOT EXISTS admins(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    authenticated BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE IF NOT EXISTS ipwhitelist (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nickname VARCHAR(255) NOT NULL,
    ip VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);