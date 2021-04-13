package main.java.model;

public class Address {
	
	public int id;
	public String town;
	public String street;
	public Integer number;
	public String houseNumber;

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", town='" + town + '\'' +
				", street='" + street + '\'' +
				", number='" + number + '\'' +
				", houseNumber='" + houseNumber + '\'' +
				'}';
	}

}
