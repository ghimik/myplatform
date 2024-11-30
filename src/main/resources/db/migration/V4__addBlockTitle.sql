-- Добавляем новое поле title в таблицу Blocks
ALTER TABLE Blocks ADD COLUMN title VARCHAR(255);

-- Если необходимо, добавляем значение по умолчанию
UPDATE Blocks SET title = 'Untitled Block';

-- Делаем поле title обязательным (nullable = false)
ALTER TABLE Blocks ALTER COLUMN title SET NOT NULL;
