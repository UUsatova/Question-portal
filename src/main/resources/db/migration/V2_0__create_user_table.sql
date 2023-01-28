create table users (
    id         uuid primary key default uuid_generate_v4(),
    first_name text NOT NULL,
    last_name  text NOT NULL,
    password   text NOT NULL CHECK (length(password) >= 8),
    email      text
        CONSTRAINT proper_email CHECK (email ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$'),
    number     text NOT NULL

);
