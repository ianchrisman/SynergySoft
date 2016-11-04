package inventory;

import java.util.ArrayList;
import java.util.List;

public class Shipment {

	protected int id;
	protected Supplier supplier;
	protected long shipDate;
	protected long recvDate;
	protected List<Item> itemsReceived = new ArrayList<Item>();
	protected double invoicePrice;
	protected boolean paid;
	
}
