CREATE TABLE demo_table (
    -- 数値型
    integer_col INTEGER,
    bigint_col BIGINT,
    decimal_col DECIMAL(10, 2),
    numeric_col NUMERIC(10, 2),

    -- 文字列型
    char_col CHAR(10),
    varchar_col VARCHAR(50),
    text_col TEXT,

    -- 日時型
    date_col DATE,
    timestamp_col TIMESTAMP,

    -- ブール型
    boolean_col BOOLEAN
);

INSERT INTO demo_table (integer_col, bigint_col, decimal_col, numeric_col, char_col, varchar_col, text_col, date_col, timestamp_col, boolean_col)
VALUES
(1, 123456789012345, 1234.56, 7890.12, 'char1', 'varchar1', 'This is a text column', '2023-01-01', '2023-01-01 12:34:56', TRUE),
(2, 223456789012345, 2234.56, 8890.12, 'char2', 'varchar2', 'Another text column', '2023-02-01', '2023-02-01 12:34:56', FALSE),
(3, 323456789012345, 3234.56, 9890.12, 'char3', 'varchar3', 'More text data', '2023-03-01', '2023-03-01 12:34:56', TRUE),
(4, 423456789012345, 4234.56, 10890.12, 'char4', 'varchar4', 'Even more text data', '2023-04-01', '2023-04-01 12:34:56', FALSE),
(5, 523456789012345, 5234.56, 11890.12, 'char5', 'varchar5', 'Sample text for testing', '2023-05-01', '2023-05-01 12:34:56', TRUE)
;
