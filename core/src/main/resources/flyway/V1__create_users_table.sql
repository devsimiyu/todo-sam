create table users (
    id serial primary key,
    username varchar (255) not null unique,
    email varchar (255) not null unique,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp,
    deleted_at timestamp
);
