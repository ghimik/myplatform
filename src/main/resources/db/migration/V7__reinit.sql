CREATE OR REPLACE FUNCTION recalculate_block_frontend_ids()
RETURNS TRIGGER AS $$
BEGIN
    -- Пересчёт frontend_id для всех блоков, связанных со страницей
    WITH ordered_blocks AS (
        SELECT id, ROW_NUMBER() OVER (ORDER BY id) - 1 AS new_frontend_id
        FROM Blocks
        WHERE page_id = NEW.page_id
    )
    UPDATE Blocks
    SET frontend_id = ordered_blocks.new_frontend_id
    FROM ordered_blocks
    WHERE Blocks.id = ordered_blocks.id;

    RETURN NULL;
END;
$$ LANGUAGE plpgsql;


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
