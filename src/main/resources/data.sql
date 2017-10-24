INSERT INTO book (id, title, author) VALUES (1, 'title', 'Kent Beck')
INSERT INTO book (id, title, author) VALUES (2, 'title2', 'Charlie')

INSERT INTO user (email, name, suspended, password, role) VALUES ('john@doe.hu', 'Van nevem', false, '$2a$10$QlGMmTuUwLAKZDvLbcxQ3e6e0082Bmy7pKEyGNokbFUZclJmdt1ki', 'ROLE_LIBRARIAN')

insert into subscription (user_email, book_id) values ('john@doe.hu', 1)

insert into borrow (till, user_email, book_id) values (DATE '2017-11-10', 'john@doe.hu', 1)