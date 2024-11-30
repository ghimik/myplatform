-- Добавляем новое поле
ALTER TABLE Pages ADD COLUMN frontend_id INT;

-- Уникальное ограничение
ALTER TABLE Pages ADD CONSTRAINT unique_frontend_id UNIQUE (frontend_id);

-- Скрипт заполнения frontend_id
DO $$
DECLARE
    ws_id INT;
    page_id INT;
    frontend_counter INT;
BEGIN
    FOR ws_id IN (SELECT id FROM Workspaces) LOOP
        frontend_counter := 0;
        FOR page_id IN (SELECT id FROM Pages WHERE workspace_id = ws_id ORDER BY id) LOOP
            UPDATE Pages SET frontend_id = frontend_counter WHERE id = page_id;
            frontend_counter := frontend_counter + 1;
        END LOOP;
    END LOOP;
END $$;
