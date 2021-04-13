package main.java.model;

public class Message {
	
	public int id;
	public String date;
	public String text;
	public int senderId;
	public int receiverId;

	@Override
	public String toString() {
		return "Message{" +
				"id=" + id +
				", date='" + date + '\'' +
				", text='" + text + '\'' +
				", senderId=" + senderId +
				", receiverId=" + receiverId +
				'}';
	}
}
