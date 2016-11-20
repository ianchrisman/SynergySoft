package inventory;

import java.util.List;

//ThreadCount Class
//Contains Main Method
//This class creates the GUI and all tabbed panes
//Final Programming Project
////////////////////////////////////////////////////////////////////////////////
//
//
//15 Nov 2016
//GUI 1.0 is complete.  We may refine it a little bit.  Not sure how to 
//handle deletes yet.  I may need to add a JTextBox to enter the Customer
//Index or Catalog index, then hit delete.  Not sure if we can actually select 
//the item from a JScrollPane.
//
//
////////////////////////////////////////////////////////////////////////////////


class ThreadCount {// Begin class ThreadCount

    public static void main(String[] args) {//Main Method
    	
    	// Justin's Controller Tests
    	Controller c = new Controller();
    	
    	c.addCustomer("Farnsworfth", "Professor", "123", "dfdf", "dfdfdfdff");
    	
    	Item i = new Item("Socks", "Black", "Medium", 7, 3.44, 7.99, 7776677);
    	c.addItem(i);
    	
    	List<Customer> customers = c.getAllCustomers();
    	for (Customer customer : customers) {
    		System.out.println(customer.toString());
    	}
    	
    	String cSearchString = "Farnsworth";
    	List<Customer> foundCustomers = c.searchCustomerNameAny(cSearchString);
    	System.out.println("\nCustomer search rersults for: \"" + cSearchString + "\"");
    	for (Customer cust : foundCustomers) {
    		System.out.println(cust.toString());
    	}
    	
    	String iSearchString = "r";
    	List<Item> foundItems = c.searchItemStyle(iSearchString);
    	System.out.println("\nAll Items searched for by \"" + iSearchString + "\"");
    	for (Item item : foundItems) {
    		System.out.println(item.toString());
    	}
    	
    	List<Item> allItems = c.getAllItems();
    	System.out.println("\nAll Items:");
    	for (Item item : allItems) {
    		System.out.println(item.toString());
    	}
    	
    	c.closeConnection();
    	// End Justin's Controller tests
    	
        ThreadGUI frame = new ThreadGUI();
        System.out.println("got Here main");
        ThreadGUI.createGUI();
    }//End Main Method
}// End Class ThreadCount