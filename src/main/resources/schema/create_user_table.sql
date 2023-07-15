create table if not exists users
(
    user_id    bigint       not null,
    chat_id    bigint       not null,
    message_id int,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    username   varchar(255) not null,
    ignore_me  boolean default false,
    chat_title varchar(255)
);