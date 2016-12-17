package threadcount;

import java.util.List;

//ThreadCount Class
//Contains Main Method
//This class creates the GUI and all tabbed panes
//Final Programming Project
////////////////////////////////////////////////////////////////////////////////
//
//
//15 Nov 2016
//Not sure how to 
//handle deletes yet.  I may need to add a JTextBox to enter the Customer
//Index or Catalog index, then hit delete.  Not sure if we can actually select 
//the item from a JScrollPane.
//
//20 Nov 2016
//Implemented Justin's database code. Adjusted the GUI for a more consistent look
//and removed some of the duplicate functionality.  I believe all the basic 
//requirements are covered.
//
//25 Nov 2016
//Implemented Justin's code into the the Customer and Catalog Search fields, along
//with the Customer and Inventory Reports.  You can now add, search, and display
//reports for Customers and Catalog
//
////////////////////////////////////////////////////////////////////////////////


class ThreadCount {// Begin class ThreadCount

    public static void main(String[] args) {//Main Method
    	
    	/*
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
    	
    	------
    	    	Item i = c.searchItemMulti("UMUC Hoodie", "Large", "Blue");
    	System.out.println("JCT: Item found?:: " + i.toStringSearch());
    	c.addToCart(99, i, 1);
    	Item i2 = c.searchItemMulti("Skirt", "L", "Black");
    	System.out.println("JCT: Item found?:: " + i2.toStringSearch());
    	c.addToCart(99, i2, 2);
    	System.out.println("Cart contents: " + c.showCart(99));
    	c.completeSale(99);
    	// End Justin's Controller tests
    	*/
    	Controller c = new Controller(); // See notes in ThreadGUI's constructor -JJ
    	
    	//
    	
        ThreadGUI frame = new ThreadGUI(c);
        frame.createGUI();
        //ThreadGUI.createGUI(); // Commented this out, threw the above line in -JJ

    }//End Main Method
}// End Class ThreadCount