package main.java.model;

public class Reservation {
	
	public int id;
	public String date;
	public int userId;
	public int bookCopyId;

	@Override
	public String toString() {
		return "Reservation{" +
				"id=" + id +
				", date='" + date + '\'' +
				", userId=" + userId +
				", bookCopyId=" + bookCopyId +
				'}';
	}
}
