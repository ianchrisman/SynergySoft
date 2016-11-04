package inventory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Supplier {

	protected int id;
	protected String companyName;
	protected URL companyUrl;
	protected String pocName;
	protected String pocEmail;
	protected String phoneNumber;
	protected String faxNumber;
	protected double amountPayable;
	protected List<Shipment> pastShipments = new ArrayList<Shipment>();
	protected List<Shipment> futureShipments = new ArrayList<Shipment>();
	
}
