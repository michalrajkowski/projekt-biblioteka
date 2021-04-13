CREATE VIEW dostepne_ksiazki AS
SELECT Book.title,COUNT(*) FROM BookCopy
INNER JOIN PublisherBook ON BookCopy.publisherbook_id=PublisherBook.id
INNER JOIN Book ON PublisherBook.book_id=Book.id
GROUP BY Book.title;

CREATE VIEW wypozyczenia_klienta AS
SELECT _User.name, _User.last_name, Book.title FROM Hire
INNER JOIN _User ON Hire.user_id=_User.id
INNER JOIN BookCopy ON Hire.bookcopy_id=BookCopy.id
INNER JOIN PublisherBook ON BookCopy.publisherbook_id=PublisherBook.id
INNER JOIN Book ON PublisherBook.book_id=Book.id
WHERE Hire.delivery_date IS NULL;

CREATE VIEW nieoddane_książki AS SELECT name, last_name, return_date, title
FROM Hire
INNER JOIN _User ON Hire.user_id = _User.id
INNER JOIN BookCopy ON Hire.bookcopy_id = BookCopy.id
INNER JOIN PublisherBook ON BookCopy.publisherbook_id = PublisherBook.id
INNER JOIN Book ON PublisherBook.book_id = Book.id
WHERE return_date < (SELECT CURDATE()) AND
(delivery_date IS NULL);

CREATE VIEW rezerwacje AS SELECT name, last_name, title
FROM Reservation
INNER JOIN _User ON Reservation.user_id = _User.id
INNER JOIN BookCopy ON Reservation.bookcopy_id = BookCopy.id
INNER JOIN PublisherBook ON BookCopy.publisherbook_id = PublisherBook.id
INNER JOIN Book ON PublisherBook.book_id = Book.id
ORDER BY last_name;
