INSERT INTO role
    (id, descricao)
SELECT 1, 'Comum'
WHERE
    NOT EXISTS (
        SELECT id FROM role WHERE id = 1
    );