create table exercise_muscle_group (
    id uuid primary key default gen_random_uuid(),
    muscle_group_id uuid not null,
    exercise_id uuid not null,
    constraint fk_muscle_group_id foreign key (muscle_group_id) references muscle_group(id),
    constraint fk_exercise_id foreign key (exercise_id) references exercise(id)
);
