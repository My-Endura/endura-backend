create table workout_template(
    id uuid primary key default gen_random_uuid(),
    name varchar not null unique,
    description varchar,
    last_completed timestamp
);

create table workout_template_exercise(
    id uuid primary key default gen_random_uuid(),
    workout_template_id uuid not null,
    exercise_id uuid not null,
    constraint fk_workout_template_id foreign key (workout_template_id) references workout_template(id),
    constraint fk_exercise_id foreign key (exercise_id) references exercise(id),
    constraint ux_workout_template_exercise unique (workout_template_id, exercise_id)
);
