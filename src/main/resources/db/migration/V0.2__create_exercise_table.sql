create table exercise (
    id uuid primary key default gen_random_uuid(),
    name varchar not null unique,
    description varchar,
    instructions varchar
);
