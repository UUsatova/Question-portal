create table questions
(
    id      uuid primary key default uuid_generate_v4(),
    content text,
    question_recipient_id uuid references users(id),
    question_sender_id uuid references users(id),
    answer_type text
);
