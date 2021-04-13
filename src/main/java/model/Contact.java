package main.java.model;

public class Contact {
	
	public int id;
	public String email;
	public Long phone;

	@Override
	public String toString() {
		return "Contact{" +
				"id=" + id +
				", email='" + email + '\'' +
				", phone=" + phone +
				'}';
	}
}
