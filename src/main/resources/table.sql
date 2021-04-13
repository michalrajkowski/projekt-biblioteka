CREATE TABLE Address(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT, 
town varchar(58) NOT NULL, 
street varchar(58), 
_number varchar(58) NOT NULL, 
house_number varchar(4),
PRIMARY KEY (id)
);

CREATE TABLE Role(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT,
role_name varchar(32) NOT NULL UNIQUE
);

CREATE TABLE Contact(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT,
email varchar(345), 
phone varchar(15),
PRIMARY KEY (id)
);

CREATE TABLE UserAuthentication(
    id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
    nick varchar(64) NOT NULL UNIQUE,
    login varchar(64) NOT NULL UNIQUE,
    password varchar(64) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Publisher (
	id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
	name varchar(256) NOT NULL UNIQUE
	);
		
CREATE TABLE BookType(
    id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
    type varchar(256) NOT NULL UNIQUE,
    PRIMARY KEY(id)
);

CREATE TABLE Author(
    id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
    name varchar(81) NOT NULL,
    last_name varchar(36) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE State (
	id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
	rate varchar(20) NOT NULL UNIQUE
	);
	
CREATE TABLE Book (
	id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
	title varchar(5820) NOT NULL,
	description varchar(10000),
	release_date date,
	PRIMARY KEY (id)
	);

CREATE TABLE Book_Author( 
    book_id int(10) NOT NULL,
    author_id int(10) NOT NULL,
    FOREIGN KEY(book_id) REFERENCES Book(id),
    FOREIGN KEY(author_id) REFERENCES Author(id)
);

CREATE TABLE Book_BookType(
    book_id int(10) NOT NULL,
    booktype_id int(10) NOT NULL,
    FOREIGN KEY(book_id) REFERENCES Book(id),
    FOREIGN KEY(booktype_id) REFERENCES BookType(id)
);

CREATE TABLE PublisherBook(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT,
book_id integer(10) NOT NULL, 
publisher_id integer(10) NOT NULL, 
img varchar(10000),
pages integer(10),
isbn decimal(13),
PRIMARY KEY (id),
FOREIGN KEY(book_id) REFERENCES Book(id),
FOREIGN KEY(publisher_id) REFERENCES Publisher(id)
);

CREATE TABLE BookCopy(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT,
publisherbook_id integer(10) NOT NULL, 
state_id integer(10) NOT NULL,
description varchar(10000), 
signature decimal(10),
PRIMARY KEY (id),
FOREIGN KEY(publisherbook_id) REFERENCES PublisherBook(id),
FOREIGN KEY(state_id) REFERENCES State(id)
);

CREATE TABLE _User(
	id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
	name varchar(81) NOT NULL,
	last_name varchar(36) NOT NULL,
	birth_date date NOT NULL,
	auth_id int(10) NOT NULL UNIQUE ,
	contact_id int(10) NOT NULL UNIQUE ,
	address_id int(10) NOT NULL,
	role_id int(10) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(auth_id) REFERENCES UserAuthentication(id),
	FOREIGN KEY(contact_id) REFERENCES Contact(id),
	FOREIGN KEY(address_id) REFERENCES Address(id),
	FOREIGN KEY(role_id) REFERENCES Role(id)
	);
	
CREATE TABLE Message(
    id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
    date date NOT NULL,
    text varchar(10000) NOT NULL,
    sender_id int(10) NOT NULL,
    receiver_id int(10) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(sender_id) REFERENCES _User(id),
    FOREIGN KEY(receiver_id) REFERENCES _User(id)
);

CREATE TABLE Reservation (
	id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
	_date date NOT NULL,
	user_id int(10) NOT NULL,
	bookcopy_id int(10) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY(user_id) REFERENCES _User(id),
	FOREIGN KEY(bookcopy_id) REFERENCES BookCopy(id)
	);
	
CREATE TABLE Hire(
id integer(10) NOT NULL UNIQUE AUTO_INCREMENT,
return_date date NOT NULL,
hire_date date NOT NULL,
delivery_date date,
user_id integer(10) NOT NULL, 
bookcopy_id integer(10) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY(user_id) REFERENCES _User(id),
FOREIGN KEY(bookcopy_id) REFERENCES BookCopy(id)
);

CREATE TABLE Opinion(
    id int(10) NOT NULL UNIQUE AUTO_INCREMENT,
    rate int(1) NOT NULL,
    text varchar(256),
    user_id int(10) NOT NULL,
    book_id int(10) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(user_id) REFERENCES _User(id),
    FOREIGN KEY(book_id) REFERENCES Book(id)
);