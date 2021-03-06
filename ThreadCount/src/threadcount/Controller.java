package threadcount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Controller {

	private Config conf = new Config();
	private Connection c = null;
	private Map<Integer, Cart> carts = new HashMap<Integer, Cart>();
	
	public Controller() {
		// Constructor, sets up the DB connection
		conf.setDefaultCloseOperation(conf.EXIT_ON_CLOSE);
		// Try to use Config's default values. If not, bring up Config's window.
		boolean success = tryConnect();
		while (!success) {
			conf.setVisible(true);
			while (conf.isVisible()) {
				// While loop is unhappy if it's not doing something..
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			success = tryConnect();
		}
	}
	
	protected boolean tryConnect() {
		// Method to try to connect to the database
		try {
			String connStr = "jdbc:mysql://" + conf.dbHost + ":" + conf.dbPort + "/" + conf.dbName;
			c = DriverManager.getConnection(connStr, conf.dbUser, conf.dbPass);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conf.ok = false;
			return false;
		}
	}
	
	protected void closeConnection() {
		// Close the DB connection.. don't forget to do this!
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        
	protected List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT id, last_name, first_name, address, email, phone_number FROM customer ORDER BY last_name ASC, first_name ASC");
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("id"), rs.getString("last_name"), rs.getString("first_Name"), rs.getString("address"), rs.getString("email"), rs.getString("phone_number"));
				customers.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customers;
	}
	
	
	protected List<Customer> searchCustomerNameAny(String searchTerm) {
		// Return a list of Customers whose first and/or last names match the search string
		List<Customer> matches = new ArrayList<Customer>();
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, last_name, first_name, address, email, phone_number FROM customer where last_name like ? or first_name like ? ORDER BY last_name ASC, first_name ASC");
			ps.setString(1,  "%" + searchTerm + "%");
			ps.setString(2,  "%" + searchTerm + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("id"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("address"), rs.getString("email"), rs.getString("phone_number"));
				matches.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	protected List<Customer> searchCustomerId(int id) {
		// Return a list of Customers whose id matches the id being searched for
		List<Customer> matches = new ArrayList<Customer>();
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, last_name, first_name, address, email, phone_number FROM customer where id=?");
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("id"), rs.getString("last_name"), rs.getString("first_name"), rs.getString("address"), rs.getString("email"), rs.getString("phone_number"));
				matches.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	protected void addCustomer(String lastName, String firstName, String address, String email, String phoneNumber) {
		// Adds a new customer to the database, if a customer by that exact first and last name don't already exist
		
		Customer customer = new Customer(lastName, firstName, address, email, phoneNumber);

		try {
			Statement s = c.createStatement(); 
			ResultSet rs = s.executeQuery("SELECT * FROM customer WHERE last_name = \'" + customer.lastName + "\' AND first_name = \'" + customer.firstName + "\'");
			
			if (!rs.next()) {
				PreparedStatement ps = c.prepareStatement("INSERT INTO customer (last_name, first_name, address, email, phone_number) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, customer.lastName);
				ps.setString(2, customer.firstName);
				ps.setString(3, customer.address);
				ps.setString(4, customer.email);
				ps.setString(5, customer.phoneNumber);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer added to Database");
				System.out.println("okay added stuff");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	protected void addCustomer(Customer customer) {
		// Adds a new customer to the database, if a customer by that exact first and last name don't already exist

		try {
			Statement s = c.createStatement(); 
			ResultSet rs = s.executeQuery("SELECT * FROM customer WHERE last_name = \'" + customer.lastName + "\' AND first_name = \'" + customer.firstName + "\'");
			
			if (!rs.next()) {
				PreparedStatement ps = c.prepareStatement("INSERT INTO customer (last_name, first_name, address, email, phone_number) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, customer.lastName);
				ps.setString(2, customer.firstName);
				ps.setString(3, customer.address);
				ps.setString(4, customer.email);
				ps.setString(5, customer.phoneNumber);
				
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "Customer added to Database");
				//System.out.println("okay added stuff");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
        
        protected void completeSale(int customerId) {
		Cart cart = carts.get(customerId);
		try {
			PreparedStatement ps1 = c.prepareStatement("INSERT INTO sales (customer_id, item_id, quantity, sale_price) values (?, ?, ?, ?)");
			for (Item i : cart.items) {
				System.out.println(cart.cartMap.get(i));
				ps1.setInt(1, cart.customerId);
				ps1.setInt(2, i.id);
				ps1.setInt(3, cart.cartMap.get(i));
				ps1.setDouble(4, i.price);
				ps1.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected String showCart(int customerId) {
		StringBuilder sb = new StringBuilder();
		for (Item i : carts.get(customerId).items) {
			sb.append(i.toStringSearch());
		}
		return sb.toString();
	}
	
	protected double getCartTotal(int customerId) {
		double total = 0.0;
		total = carts.get(customerId).total;
		return total;
	}
	
	protected boolean addToCart(int customerId, Item i, int quantity) {
		Cart cart;
		if (carts.containsKey(customerId)) {
			cart = carts.get(customerId);
		} else {
			cart = new Cart(customerId);
			carts.put(customerId, cart);
		}
		try {
			Statement s1 = c.createStatement();
			
			s1.executeQuery("SELECT quantity FROM catalog WHERE id = " + i.id);
			ResultSet r1 = s1.getResultSet();
			while (r1.next()) {	
				if (r1.getInt(1) > 0) {
					Statement s2 = c.createStatement();
					s2.executeUpdate("UPDATE catalog SET quantity = quantity - " + quantity + " WHERE id = " + i.id);
					cart.addItem(i, quantity);
					cart.total += i.price * quantity;
					carts.put(customerId, cart);
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
        
        
	protected void cancelCart(int customerId) {
		Cart cart = carts.get(customerId);
		try {
			PreparedStatement ps = c.prepareStatement("UPDATE catalog SET quantity = quantity + ? WHERE id = ?");
			for (Item i : cart.items) {
				int quantity = cart.cartMap.get(i);
				ps.setInt(1, quantity);
				ps.setInt(2, i.id);
				ps.executeUpdate();
			}
			cart.items.clear();
			cart.cartMap.clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}     
	
	protected Item searchItemMulti(String style, String size, String color) {
		try {
			Statement s = c.createStatement();
			s.executeQuery("SELECT id, style, color, size, quantity, unit_cost, price, sku FROM catalog where style = \'" + style + "\' AND size = \'" + size + "\' AND color = \'" + color + "\'");
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("style"), rs.getString("color"), rs.getString("size"), rs.getInt("quantity"), rs.getDouble("unit_cost"), rs.getDouble("price"), rs.getLong("sku"));
				return item;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	protected void addItem(Item i) {
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM catalog WHERE style = \'" + i.style + "\' AND color = \'" + i.color + "\' AND size = \'" + i.size + "\' AND quantity = \'" + i.quantity + "\' AND unit_cost = \'" + i.unitCost + "\' AND price = \'" + i.price + "\' AND sku = \'" + i.sku + "\'");
			
			if (!rs.next()) {
				System.out.println("I can create the item");
				PreparedStatement ps = c.prepareStatement("INSERT INTO catalog (style, color, size, quantity, unit_cost, price, sku) VALUES (?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, i.style);
				ps.setString(2, i.color);
				ps.setString(3, i.size);
				ps.setInt(4, i.quantity);
				ps.setDouble(5, i.unitCost);
				ps.setDouble(6, i.price);
				ps.setLong(7, i.sku);
				
				ps.executeUpdate();
				//System.out.println("Added item to DB");
                                ThreadGUI.setAddedtoDBTrue();
                                
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void addItem(Item i, long skuToCheck) {
            List<Item> matches = searchItemSku(skuToCheck);
            if (matches.size() < 1){
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM catalog WHERE style = \'" + i.style + "\' AND color = \'" + i.color + "\' AND size = \'" + i.size + "\' AND quantity = \'" + i.quantity + "\' AND unit_cost = \'" + i.unitCost + "\' AND price = \'" + i.price + "\' AND sku = \'" + i.sku + "\'");
			
			if (!rs.next()) {
				System.out.println("I can create the item");
				PreparedStatement ps = c.prepareStatement("INSERT INTO catalog (style, color, size, quantity, unit_cost, price, sku) VALUES (?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, i.style);
				ps.setString(2, i.color);
				ps.setString(3, i.size);
				ps.setInt(4, i.quantity);
				ps.setDouble(5, i.unitCost);
				ps.setDouble(6, i.price);
				ps.setLong(7, i.sku);
				
				ps.executeUpdate();
				//System.out.println("Added item to DB");
                                ThreadGUI.setAddedtoDBTrue();
                                
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            } else{
                JOptionPane.showMessageDialog(null, "SKU " + skuToCheck + " already exists. Not Added");
            }
	}

        
	protected List<Item> searchItemStyle(String searchTerm) {
		// Return a list of Items by Style
		List<Item> matches = new ArrayList<Item>();
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, style, color, size, quantity, unit_cost, price, sku FROM catalog where style like ?");
			ps.setString(1,  "%" + searchTerm + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("style"), rs.getString("color"), rs.getString("size"), rs.getInt("quantity"), rs.getDouble("unit_cost"), rs.getDouble("price"), rs.getLong("sku"));
				matches.add(item);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	protected List<Item> searchItemSku(long searchTerm) {
		// Return a list of Items by Style
		List<Item> matches = new ArrayList<Item>();
		
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, style, color, size, quantity, unit_cost, price, sku FROM catalog where sku = ?");
			ps.setLong(1, searchTerm);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Item item = new Item(rs.getInt("id"), rs.getString("style"), rs.getString("color"), rs.getString("size"), rs.getInt("quantity"), rs.getDouble("unit_cost"), rs.getDouble("price"), rs.getLong("sku"));
				matches.add(item);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matches;
	}
	
	
	protected List<Item> getAllItems() {
		List<Item> items = new ArrayList<Item>();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT id, style, color, size, quantity, unit_cost, price, sku FROM catalog ORDER BY style ASC, size ASC, color ASC");
                        
                                while (rs.next()) {
                                        Item i = new Item(rs.getInt("id"), rs.getString("style"), rs.getString("color"), rs.getString("size"), rs.getInt("quantity"), rs.getDouble("unit_cost"), rs.getDouble("price"), rs.getLong("sku"));
                                        items.add(i);
                                }

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	}

	
	protected void deleteItem(long skuToDelete) {
		List<Item> matches = searchItemSku(skuToDelete);
		if (matches.size() == 1) {
			System.out.println("OK i can do it");
			int id = matches.get(0).id;
			System.out.println("ID: " + id);
			try {
				Statement s = c.createStatement();
				s.executeUpdate("DELETE FROM catalog WHERE id=" + id);
                                JOptionPane.showMessageDialog(null, "Item Deleted");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
                        JOptionPane.showMessageDialog(null, "SKU " + skuToDelete +" Not Found");
                }
	}
	
	protected void deleteCustomer(int id) {
		List<Customer> matches = searchCustomerId(id);
		if (matches.size() == 1) {
			System.out.println("OK i can do it");
			int foundid = matches.get(0).id;
			System.out.println("ID: " + foundid);
			try {
				Statement s = c.createStatement();
				s.executeUpdate("DELETE FROM customer WHERE id=" + id);
                                JOptionPane.showMessageDialog(null, "Customer Deleted");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
                        JOptionPane.showMessageDialog(null, "Customer ID not Found");
                }
		
	}
	
	protected List<String> getUniqueStyleNames() {
		List<String> styles = new ArrayList<String>();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT DISTINCT style FROM catalog");
			while (rs.next()) {
				styles.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return styles;
	}
	
	protected List<String> getColorsForSelectedItemAndSize(String style, String size) {
		List<String> colors = new ArrayList();
		try {
			Statement s = c.createStatement();
			s.executeQuery("SELECT DISTINCT color FROM catalog WHERE style = \'" + style + "\' AND size = \'" + size + "\'");
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				colors.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return colors;
	}
	
	protected List<String> getSizesForStyle(String style) {
		List<String> sizes = new ArrayList();
		try {
			Statement s = c.createStatement();
			s.executeQuery("SELECT DISTINCT size FROM catalog WHERE style = \'" + style + "\'");
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				sizes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sizes;
	}
        
        
	protected void changeItemQuantity(long sku, int quantity) {
                List<Item> matches = searchItemSku(sku);
                if (matches.size() == 1) {
                        try {
                                Statement s = c.createStatement();
                                s.executeUpdate("UPDATE catalog SET quantity = " + quantity + " WHERE sku = " + sku);
                                JOptionPane.showMessageDialog(null, "Quantity Updated");
                        } catch (SQLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                } else {
                        JOptionPane.showMessageDialog(null, "SKU " + sku +" Not Found");
                }
	}
	
	protected String displayAllSales() {
		StringBuffer sb = new StringBuffer();
		try {
			Statement s = c.createStatement();
			PreparedStatement ps = c.prepareStatement("SELECT c.last_name, c.first_name, i.style, i.color, i.size FROM customer c, catalog i WHERE c.id = ? AND i.id = ?");
			ResultSet rs = s.executeQuery("SELECT id, customer_id, item_id, quantity, sale_price, created FROM sales");
			while (rs.next()) {
				int salesId = rs.getInt(1);
				int customerId = rs.getInt(2);
				int itemId = rs.getInt(3);
				int quantity = rs.getInt(4);
				double salePrice = rs.getDouble(5);
				Timestamp ts = rs.getTimestamp(6);
				ps.setInt(1, customerId);
				ps.setInt(2, itemId);
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					String lName = rs2.getString(1);
					String fName = rs2.getString(2);
					String style = rs2.getString(3);
					String color = rs2.getString(4);
					String size = rs2.getString(5);
					sb.append("Customer: " + lName + ", " + fName + " Item: " + color + " " + style + ", " + size + " Quantity: " + quantity + " Price: " + salePrice + "\n");
					//sb.append("Customer: " + rs2.getString(1) + ", " + rs2.getString(2) + "Item: " + rs2.getString(3) + ts.toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	protected String csvAllSales() {
		StringBuffer sb = new StringBuffer();
		try {
			Statement s = c.createStatement();
			PreparedStatement ps = c.prepareStatement("SELECT c.last_name, c.first_name, i.style, i.color, i.size FROM customer c, catalog i WHERE c.id = ? AND i.id = ?");
			ResultSet rs = s.executeQuery("SELECT id, customer_id, item_id, quantity, sale_price, created FROM sales");
			while (rs.next()) {
				int salesId = rs.getInt(1);
				int customerId = rs.getInt(2);
				int itemId = rs.getInt(3);
				int quantity = rs.getInt(4);
				double salePrice = rs.getDouble(5);
				Timestamp ts = rs.getTimestamp(6);
				ps.setInt(1, customerId);
				ps.setInt(2, itemId);
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					String lName = rs2.getString(1);
					String fName = rs2.getString(2);
					String style = rs2.getString(3);
					String color = rs2.getString(4);
					String size = rs2.getString(5);
					sb.append(lName + ":" + fName + ":" + color + ":" + style + ":" + size + ":" + quantity + ":" + salePrice + "\n");
					//sb.append("Customer: " + rs2.getString(1) + ", " + rs2.getString(2) + "Item: " + rs2.getString(3) + ts.toString());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}