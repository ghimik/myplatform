
CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE TABLE Workspaces (
    id SERIAL PRIMARY KEY,
    owner_id INT REFERENCES Users(id),
    name VARCHAR(100) NOT NULL
);

CREATE TABLE Pages (
    id SERIAL PRIMARY KEY,
    workspace_id INT REFERENCES Workspaces(id),
    parent_page_id INT REFERENCES Pages(id),
    title VARCHAR(255),
    content TEXT
);

CREATE TABLE Blocks (
    id SERIAL PRIMARY KEY,
    page_id INT REFERENCES Pages(id),
    type VARCHAR(50), --  "text", "image"
    content TEXT
);

CREATE TABLE Permissions (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES Users(id),
    page_id INT REFERENCES Pages(id),
    permission_type VARCHAR(50) -- "read", "write"
);
