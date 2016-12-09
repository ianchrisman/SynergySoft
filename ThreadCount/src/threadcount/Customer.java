package threadcount;

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
		sb.append("ID#: " + id + "   Customer: " + firstName + " " + lastName + "   Address: " + address + "   Email: " + email + "   Phone: " + phoneNumber);
		return sb.toString();
	}
        public String toStringSearch() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nID#:  " + id + "\nCustomer:  " + firstName + " " + lastName + "\nAddress:  " + address + "\nEmail:  " + email + "\nPhone:  " + phoneNumber);
		return sb.toString();
	}
	public String toStringReport() {
		StringBuilder sb = new StringBuilder();
		sb.append(id +":"+ firstName +":"+ lastName +":"+ address+":"+ email +":"+ phoneNumber);
		return sb.toString();
	}
}