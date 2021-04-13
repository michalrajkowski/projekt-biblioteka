package main.java.model;

public class UserAuthentication {
	
	public int id;
	public String nick;
	public String login;
	public String password;

	@Override
	public String toString() {
		return "UserAuthentication{" +
				"id=" + id +
				", nick='" + nick + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
