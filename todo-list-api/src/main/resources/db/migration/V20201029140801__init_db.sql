CREATE TABLE TODO
(
    id              bigint          NOT NULL PRIMARY KEY auto_increment,
    todo_item       VARCHAR(30)     NOT NULL,
    is_done         BOOLEAN         NOT NULL
);