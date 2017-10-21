INSERT INTO book (id, title, author) VALUES (1, 'title', 'Kent Beck')
INSERT INTO book (id, title, author) VALUES (2, 'title2', 'Charlie')

INSERT INTO user (email, name, suspended, password, role) VALUES ('john@doe.hu', 'Van nevem', false, 'pass', 'ROLE_LIBRARIAN')

insert into subscription (user_email, book_id) values ('john@doe.hu', 1)