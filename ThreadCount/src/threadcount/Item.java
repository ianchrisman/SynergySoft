package threadcount;

public class Item {

	protected int id;
	protected String style;
	protected String color;
	protected String size;
	protected int quantity;
	protected double unitCost;
	protected double price;
	protected String imgUri;
	protected long sku;
        
        Item() {
        
        }

	public Item(String style, String color, String size, int quantity, double unitCost, double price, long sku) {
		this.style = style;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.price = price;
		this.sku = sku;
	}
	
	public Item(int id, String style, String color, String size, int quantity, double unitCost, double price, long sku) {
		this.id = id;
		this.style = style;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.price = price;
		this.sku = sku;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Item Style:  " + style + "  Color:  " + color + "  Size:  "
                        + size + "  Quantity:  " + quantity + "  Cost:  $" + unitCost 
                        + "  Price:  $" + price + "  SKU:  " + sku);
		return sb.toString();
	}
	public String toStringSearch() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\nItem Style:  " + style + "\nColor:  " + color + "\nSize:  "
                        + size + "\nQuantity:  " + quantity + "\nCost:  $" + 
                        unitCost + "\nPrice:  $" + price + "\nSKU:  " + sku);
		return sb.toString();
	}
}