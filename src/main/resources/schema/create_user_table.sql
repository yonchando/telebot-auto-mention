create table if not exists users
(
    user_id    bigint       not null,
    chat_id    bigint       not null,
    message_id int,
    first_name varchar(255) not null,
    last_name  varchar(255),
    username   varchar(255),
    ignore_me  boolean default false,
    chat_title varchar(255)
);

INSERT INTO users (user_id, chat_id, message_id, first_name, last_name, username, ignore_me, chat_title)
VALUES (554723221, -1001809386163, 235, 'Ratsuto', null, 'ratsuto_senpai', false, 'Learn Bot');
INSERT INTO users (user_id, chat_id, message_id, first_name, last_name, username, ignore_me, chat_title)
VALUES (449084408, -1001809386163, 213, 'Yon', 'Chando Bronx', 'yonchando', false, 'Learn Bot');
INSERT INTO users (user_id, chat_id, message_id, first_name, last_name, username, ignore_me, chat_title)
VALUES (449084408, 449084408, 109, 'Yon', 'Chando Bronx', 'yonchando', false, null);
