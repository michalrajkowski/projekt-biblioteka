package main.java.model;

public class User {
	
	public Integer id;
	public String name;
	public String lastName;
	public String birthDate;
	public int authId;
	public int contactId;
	public int addressId;
	public int roleId;

	public User(Integer id, String name, String lastName, String birthDate, int authId, int contactId, int addressId, int roleId) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.authId = authId;
		this.contactId = contactId;
		this.addressId = addressId;
		this.roleId = roleId;
	}

	public User(){
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate='" + birthDate + '\'' +
				", authId=" + authId +
				", contactId=" + contactId +
				", addressId=" + addressId +
				", roleId=" + roleId +
				'}';
	}
}
