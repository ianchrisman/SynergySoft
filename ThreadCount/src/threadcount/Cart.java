package threadcount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {

	protected int customerId;
	protected List<Item> items = new ArrayList<Item>();
	protected Map<Item, Integer> cartMap = new HashMap<Item, Integer>();
	protected double total = 0.0;
	
	protected Cart(int customerId) {
		this.customerId = customerId;
	}
	
	protected void addItem(Item item, int quantity) {
		items.add(item);
		cartMap.put(item, quantity);
	}
	
	protected void chaChing() {
		
	}

}
	

