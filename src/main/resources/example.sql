START TRANSACTION;

INSERT INTO Address( id, town, street, _number, house_number)
VALUES (1, 'Warszawa', 'Lipowa', 24, '16');
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (2, 'Gdynia', 'Azaliowa', 10, NULL);
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (3, 'Warszawa', 'Lipowa', 5, NULL);
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (4, 'Warszawa', 'Smutna', 18, '22');
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (5, 'Gdynia', 'Radosna', 99, NULL);
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (6, 'Bydgoszcz', 'Ziemniaczana', 1, NULL);
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (7, 'Legnica', 'Targowicka', 4, '3');
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (8, 'Opole', 'Orla', 3, NULL);
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (9, 'Tychy', 'Lisia', 12, '2');
INSERT INTO Address( id, town, street, _number, house_number)
VALUES (10, 'Sosnowiec', 'Ptasia', 5, NULL);

INSERT INTO Role( id, role_name)
VALUES (1, 'pracownik');
INSERT INTO Role( id, role_name)
VALUES (2, 'klient');

INSERT INTO Contact(id, email, phone)
VALUES ( 1, 'malyjasio@wp.pl' ,48605554856 );
INSERT INTO Contact(id, email, phone)
VALUES ( 2,NULL ,NULL );
INSERT INTO Contact(id, email, phone)
VALUES ( 3, 'turboczytelnik@gmail.com' ,NULL );
INSERT INTO Contact(id, email, phone)
VALUES ( 4,NULL ,48505554900 );
INSERT INTO Contact(id, email, phone)
VALUES ( 5, 'barbara001@gmail.com' ,48455552789 );
INSERT INTO Contact(id, email, phone)
VALUES ( 6, 'kuba@gmail.com' ,48455552744 );

INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (1, 'Zbychu', 'Zbychu', '1234');
INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (2, 'kwiatuszek01', 'amelia01', 'Hc6ZD8663lOp');
INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (3, 'Zielone wody', 'bartek007', 'Kotekliżemleczko');
INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (4, 'aeopjasdwah', 'vilette444', 'Mojehaslo321');
INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (5, 'brokencandle', 'cancersandcrabs', 'LOLOLO');
INSERT INTO UserAuthentication(id, nick, login, password)
VALUES (6, 'kubcio', 'kubcio', '123');

INSERT INTO Publisher( id, name)
VALUES (1, 'Greg' );
INSERT INTO Publisher( id, name)
VALUES (2, 'Albatros' );
INSERT INTO Publisher( id, name)
VALUES (3, 'Drzewo Babel' );
INSERT INTO Publisher( id, name)
VALUES (4, 'ELAY' );
INSERT INTO Publisher( id, name)
VALUES (5, 'Fabryka Słów' );

INSERT INTO BookType( id, type)
VALUES (1, 'literatura obyczajowa');
INSERT INTO BookType( id, type)
VALUES (2, 'romans');
INSERT INTO BookType( id, type)
VALUES (3, 'kryminał');
INSERT INTO BookType( id, type)
VALUES (4, 'sensacja');
INSERT INTO BookType( id, type)
VALUES (5, 'thriller');
INSERT INTO BookType( id, type)
VALUES (6, 'fantastyka');
INSERT INTO BookType( id, type)
VALUES (7, 'science fiction');
INSERT INTO BookType( id, type)
VALUES (8, 'literatura fatku');
INSERT INTO BookType( id, type)
VALUES (9, 'horror');
INSERT INTO BookType( id, type)
VALUES (10, 'literatura młodzieżowa');

INSERT INTO Author VALUES (1,'Adam','Mickiewicz');
INSERT INTO Author VALUES (2,'Jan','Brzechwa');
INSERT INTO Author VALUES (3,'Witold','Gombrowicz');
INSERT INTO Author VALUES (4,'Henryk','Sienkiewicz');
INSERT INTO Author VALUES (5,'Bolesław','Prus');
INSERT INTO Author VALUES (6,'Rhoshandiatellyneshiaunneveshenk','Williams');

INSERT INTO State VALUES (1,'zły');
INSERT INTO State VALUES (2,'średni');
INSERT INTO State VALUES (3,'dobry');
INSERT INTO State VALUES (4,'bardzo dobry');
INSERT INTO State VALUES (5,'nowy');

INSERT INTO Book VALUES (1,'Pan Tadeusz','fajna książka','1000-01-01');
INSERT INTO Book VALUES (2,'Pan Pomidor','zrobil sie czerwony','1000-01-01');
INSERT INTO Book VALUES (3,'Ferdydurke','Gombrowicz to geniusz','1001-01-01');
INSERT INTO Book VALUES (4,'Krzyżacy','fajna książka','1001-01-10');
INSERT INTO Book VALUES (5,'Lalka','fajna książka','1001-01-11');
INSERT INTO Book VALUES (6,'Dzieci z bullerbyn','ksiazka dla dzieci','1001-01-11');
INSERT INTO Book VALUES (7,'Wojna','wojna','1001-01-11');

INSERT INTO Book_Author VALUES (1,1);
INSERT INTO Book_Author VALUES (2,2);
INSERT INTO Book_Author VALUES (3,3);
INSERT INTO Book_Author VALUES (4,4);
INSERT INTO Book_Author VALUES (5,5);
INSERT INTO Book_Author VALUES (6,6);

INSERT INTO Book_BookType VALUES (1,1);
INSERT INTO Book_BookType VALUES (2,2);
INSERT INTO Book_BookType VALUES (3,3);
INSERT INTO Book_BookType VALUES (4,4);
INSERT INTO Book_BookType VALUES (5,5);

INSERT INTO PublisherBook VALUES (1,1,1,'images/pan_tadeusz.jpg',10,1122222222222);
INSERT INTO PublisherBook VALUES (2,2,2,'images/pan_pomidor.jpg',NULL,10);
INSERT INTO PublisherBook VALUES (3,3,3,'images/ferdydurke.jpg',10,NULL);
INSERT INTO PublisherBook VALUES (4,4,4,'images/krzyzacy.jpg',10,100);
INSERT INTO PublisherBook VALUES (5,5,5,'images/lalka.jpg',1000,10);
INSERT INTO PublisherBook VALUES (6,6,5,'images/dzieci_z_bullerbyn.jpg',1000,10);

INSERT INTO BookCopy(id, publisherbook_id, state_id, description, signature) VALUES(1, 1, 1, 'opis1', 1111111111);
INSERT INTO BookCopy(id, publisherbook_id, state_id, description, signature) VALUES(2, 2, 2, 'opis2', 1231231231);
INSERT INTO BookCopy(id, publisherbook_id, state_id, description, signature) VALUES(3, 3, 3, 'opis3', 1231231232);
INSERT INTO BookCopy(id, publisherbook_id, state_id, description, signature) VALUES(4, 4, 4, 'opis4', 1111111112);
INSERT INTO BookCopy(id, publisherbook_id, state_id, description, signature) VALUES(5, 5, 5, 'opis5', 1111111113);

INSERT INTO _User VALUES(1, 'Michał', 'Miller', '1900-07-19', 1, 1, 1, 1);
INSERT INTO _User VALUES(2, 'Maciej', 'Fuks', '1200-02-19', 2, 2, 2, 2);
INSERT INTO _User VALUES(3, 'Anna', 'Lepioł', '1300-03-19', 3, 3, 3, 2);
INSERT INTO _User VALUES(4, 'Marcin', 'Korzeniowski', '2002-04-20', 4, 4, 4, 2);
INSERT INTO _User VALUES(5, 'Agnieszka', 'Dygant', '2009-10-20', 5, 5, 5, 2);

INSERT INTO Message(id, date, text, sender_id, receiver_id) VALUES(1, '1999-01-19', 'text1', 1, 1);
INSERT INTO Message(id, date, text, sender_id, receiver_id) VALUES(2, '1999-02-19', 'text2', 2, 2);
INSERT INTO Message(id, date, text, sender_id, receiver_id) VALUES(3, '1999-03-19', 'text3', 3, 3);
INSERT INTO Message(id, date, text, sender_id, receiver_id) VALUES(4, '1999-04-19', 'text4', 4, 4);
INSERT INTO Message(id, date, text, sender_id, receiver_id) VALUES(5, '2000-05-19', 'text5', 5, 5);

INSERT INTO Reservation(id, _date, user_id, bookcopy_id) VALUES(1, '1987-01-19', 1, 1);
INSERT INTO Reservation(id, _date, user_id, bookcopy_id) VALUES(2, '1234-02-19', 2, 2);
INSERT INTO Reservation(id, _date, user_id, bookcopy_id) VALUES(3, '1342-03-19', 3, 3);
INSERT INTO Reservation(id, _date, user_id, bookcopy_id) VALUES(4, '1643-04-19', 4, 4);
INSERT INTO Reservation(id, _date, user_id, bookcopy_id) VALUES(5, '1743-05-19', 5, 5);

INSERT INTO Hire(id, return_date, hire_date, delivery_date, user_id, bookcopy_id) VALUES(1, '2021-05-19', '2020-01-19', '1212-11-20', 1, 1);
INSERT INTO Hire(id, return_date, hire_date, delivery_date, user_id, bookcopy_id) VALUES(2, '2029-06-19', '2020-12-19', NULL, 2, 2);
INSERT INTO Hire(id, return_date, hire_date, delivery_date, user_id, bookcopy_id) VALUES(3, '2021-07-20', '2020-11-19', '1222-09-19', 3, 3);
INSERT INTO Hire(id, return_date, hire_date, delivery_date, user_id, bookcopy_id) VALUES(4, '2021-08-20', '2020-12-19', '1333-05-20', 4, 4);
INSERT INTO Hire(id, return_date, hire_date, delivery_date, user_id, bookcopy_id) VALUES(5, '2021-09-20', '2020-01-19', NULL, 5, 5);

INSERT INTO Opinion(id, rate, text, user_id, book_id) VALUES(1, 1, 'text1', 1, 1);
INSERT INTO Opinion(id, rate, text, user_id, book_id) VALUES(2, 2, 'text2', 2, 2);
INSERT INTO Opinion(id, rate, text, user_id, book_id) VALUES(3, 3, 'text3', 3, 3);
INSERT INTO Opinion(id, rate, text, user_id, book_id) VALUES(4, 4, 'text4', 4, 4);
INSERT INTO Opinion(id, rate, text, user_id, book_id) VALUES(5, 5, 'text5', 5, 5);

COMMIT;