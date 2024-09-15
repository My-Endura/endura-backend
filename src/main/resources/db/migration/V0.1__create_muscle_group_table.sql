create table muscle_group (
    id uuid primary key default gen_random_uuid(),
    name varchar not null
);
