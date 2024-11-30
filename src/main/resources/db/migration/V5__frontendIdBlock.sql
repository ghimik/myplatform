ALTER TABLE Pages DROP CONSTRAINT unique_frontend_id;

-- Удаляем некорректное поле frontend_id из таблицы Pages
ALTER TABLE Pages DROP COLUMN IF EXISTS frontend_id;

-- Добавляем новое поле frontend_id в таблицы Pages и Blocks
ALTER TABLE Pages ADD COLUMN frontend_id INT;
ALTER TABLE Blocks ADD COLUMN frontend_id INT;

-- Устанавливаем уникальность frontend_id для пар (workspace_id, frontend_id) в Pages
ALTER TABLE Pages ADD CONSTRAINT unique_frontend_id_per_workspace UNIQUE (workspace_id, frontend_id);

-- Устанавливаем уникальность frontend_id для пар (page_id, frontend_id) в Blocks
ALTER TABLE Blocks ADD CONSTRAINT unique_frontend_id_per_page UNIQUE (page_id, frontend_id);

-- Заполняем начальные значения для frontend_id в Pages
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

-- Заполняем начальные значения для frontend_id в Blocks
DO $$
DECLARE
    pg_id INT;
    block_id INT;
    frontend_counter INT;
BEGIN
    FOR pg_id IN (SELECT id FROM Pages) LOOP
        frontend_counter := 0;
        FOR block_id IN (SELECT id FROM Blocks WHERE page_id = pg_id ORDER BY id) LOOP
            UPDATE Blocks SET frontend_id = frontend_counter WHERE id = block_id;
            frontend_counter := frontend_counter + 1;
        END LOOP;
    END LOOP;
END $$;

-- Создаём функцию для пересчёта frontend_id в Pages
CREATE OR REPLACE FUNCTION recalculate_page_frontend_ids()
RETURNS TRIGGER AS $$
BEGIN
    -- Пересчёт frontend_id для всех страниц в пределах рабочего пространства
    WITH ordered_pages AS (
        SELECT id, ROW_NUMBER() OVER (ORDER BY id) - 1 AS new_frontend_id
        FROM Pages
        WHERE workspace_id = OLD.workspace_id
    )
    UPDATE Pages
    SET frontend_id = ordered_pages.new_frontend_id
    FROM ordered_pages
    WHERE Pages.id = ordered_pages.id;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Создаём функцию для пересчёта frontend_id в Blocks
CREATE OR REPLACE FUNCTION recalculate_block_frontend_ids()
RETURNS TRIGGER AS $$
BEGIN
    -- Пересчёт frontend_id для всех блоков в пределах страницы
    WITH ordered_blocks AS (
        SELECT id, ROW_NUMBER() OVER (ORDER BY id) - 1 AS new_frontend_id
        FROM Blocks
        WHERE page_id = OLD.page_id
    )
    UPDATE Blocks
    SET frontend_id = ordered_blocks.new_frontend_id
    FROM ordered_blocks
    WHERE Blocks.id = ordered_blocks.id;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;

-- Добавляем триггер для пересчёта frontend_id в Pages при удалении
CREATE TRIGGER trigger_recalculate_pages_on_delete
AFTER DELETE ON Pages
FOR EACH ROW EXECUTE FUNCTION recalculate_page_frontend_ids();


-- Добавляем триггер для пересчёта frontend_id в Blocks при удалении
CREATE TRIGGER trigger_recalculate_blocks_on_delete
AFTER DELETE ON Blocks
FOR EACH ROW EXECUTE FUNCTION recalculate_block_frontend_ids();

-- Добавляем триггер для пересчёта frontend_id в Blocks при добавлении
CREATE TRIGGER trigger_recalculate_blocks_on_insert
AFTER INSERT ON Blocks
FOR EACH ROW EXECUTE FUNCTION recalculate_block_frontend_ids();
