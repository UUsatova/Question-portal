ALTER TABLE questions
    RENAME COLUMN question_sender_id TO sender_id;
ALTER TABLE questions
    RENAME COLUMN question_recipient_id TO recipient_id;
