create table workout (
    id uuid primary key default gen_random_uuid(),
    name varchar not null,
    started_at timestamp not null default current_timestamp,
    completed_at timestamp,
    duration integer,
    total_weight integer,
    workout_template_id uuid,
    constraint fk_workout_template_id foreign key (workout_template_id) references workout_template(id)
);
