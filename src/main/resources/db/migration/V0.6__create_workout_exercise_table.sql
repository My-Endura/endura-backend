create table workout_exercise (
    id uuid primary key default gen_random_uuid(),
    workout_id uuid not null,
    exercise_id uuid not null,
    constraint fk_workout_id foreign key (workout_id) references workout(id),
    constraint fk_exercise_id foreign key (exercise_id) references exercise(id)
);
