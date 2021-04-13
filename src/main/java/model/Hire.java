package main.java.model;

public class Hire {
	
	public int id;
	public String returnDate;
	public String hireDate;
	public String deliveryDate;
	public int userId;
	public int bookCopyId;

	@Override
	public String toString() {
		return "Hire{" +
				"id=" + id +
				", returnDate='" + returnDate + '\'' +
				", hireDate='" + hireDate + '\'' +
				", deliveryDate='" + deliveryDate + '\'' +
				", userId=" + userId +
				", bookCopyId=" + bookCopyId +
				'}';
	}
}
