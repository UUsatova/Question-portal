create table answers(
    id uuid primary key default uuid_generate_v4(),
    content text,
    answer_type text,
    question_id uuid references questions(id)
);