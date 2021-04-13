CREATE INDEX _User_index1
on _User (name, last_name);

CREATE INDEX Book_index1
on Book (title);

CREATE INDEX Hire_index1
on Hire (user_id);

CREATE INDEX Hire_index2
on Hire (bookcopy_id);

CREATE INDEX Hire_index3
on Hire (delivery_date desc);

CREATE INDEX Reservation_index1
on Reservation (user_id);

CREATE INDEX Reservation_index2
on Reservation (bookcopy_id);
