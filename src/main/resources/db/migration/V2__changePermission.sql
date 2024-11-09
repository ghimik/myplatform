ALTER TABLE Permissions
    ADD COLUMN workspace_id INT;

ALTER TABLE Permissions
    DROP CONSTRAINT IF EXISTS fk_permissions_page_id;
ALTER TABLE Permissions
    ADD CONSTRAINT fk_permissions_workspace_id FOREIGN KEY (workspace_id) REFERENCES Workspaces(id);

ALTER TABLE Permissions
    DROP COLUMN page_id;

ALTER TABLE Permissions
    ADD CONSTRAINT permission_type_check CHECK (permission_type IN ('read', 'write', 'admin'));
