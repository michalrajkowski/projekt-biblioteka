package main.java.model;

public class Role {
	
	public int id;
	public String roleName;

	@Override
	public String toString() {
		return "Role{" +
				"id=" + id +
				", roleName='" + roleName + '\'' +
				'}';
	}
}
