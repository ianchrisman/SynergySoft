package inventory;

public class Customer {
	protected int id;
	protected String lastName;
	protected String firstName;
	protected String address;
	protected String email;
	protected String phoneNumber;
	
	protected Customer() {
		
	}
	
	protected Customer(String lastName, String firstName, String address, String email, String phoneNumber) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	protected Customer(int id, String lastName, String firstName, String address, String email, String phoneNumber) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Customer: " + firstName + " " + lastName + " Address: " + address + " Email: " + email + " Phone: " + phoneNumber);
		return sb.toString();
	}
}
