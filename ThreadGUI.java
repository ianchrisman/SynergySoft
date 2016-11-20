package inventory;
//ThreadGUI Class
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

import java.awt.*;
import java.util.List;
import javax.swing.*;

class ThreadGUI extends JPanel { // Begin ThreadGUI Class
	
	private Controller c = new Controller();
	
    public ThreadGUI(){ //begin constructor
        super(new BorderLayout());
        
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel customerPanel = new JPanel();// Calls Customer Method    
            customerPanel.add(createCustomerPanel(true));
            customerPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Customer  ", customerPanel);
        
        JPanel catalogPanel = new JPanel();// Calls Catalog Method
            catalogPanel.add(createCatalogPanel(true));
            catalogPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Catalog  ", catalogPanel);
        
        JPanel salesPanel = new JPanel(); // Calls Sales Method
            salesPanel.add(createSalesPanel(true));
            salesPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Sales  ", salesPanel);
        
        JPanel reportPanel = new JPanel(); // Calls Report Method
            reportPanel.add(createReportPanel(true));
            reportPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Reports  ", reportPanel);
        
        JPanel inventoryPanel = new JPanel(); // Calls Inventory Method
            inventoryPanel.add(createInventoryPanel(true));
            inventoryPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Inventory  ", inventoryPanel);
        //Add tabbedPane to this panel.
        add(tabbedPane, BorderLayout.CENTER);
            tabbedPane.setBackground(new Color(255,178,102));
    } // end ThreadGUI constructor
    
    public static void ThreadGUI() {
    }    
    
    public static void createGUI(){ //begin CreateGui method
        //Window Set up
        JFrame frame = new JFrame("ThreadCounts");    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Content Pane
        ThreadGUI threadPane = new ThreadGUI();
        threadPane.setBackground(new Color(255,190,120));
        threadPane.setOpaque(true);
        frame.setContentPane(threadPane);
        frame.pack();
        frame.setVisible(true);
        System.out.println("got here creategui");
    }    
    
    protected JPanel createCustomerPanel(boolean makePanel){ 
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
            JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent centerSalesComponent = new JPanel();
            JComponent rightComponent = new JPanel();
                JComponent rightCartComponent = new JPanel();
                    JComponent rightGridComponent = new JPanel();
                    JComponent rightFlowComponent = new JPanel();
        //initializing GUI features
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel firstNameLabel = new JLabel("First Name:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel addCustomerLabel = new JLabel("       Add New Customer");
        JLabel searchCustomerLabel = new JLabel("       Search ");
        JTextField lastNameText = new JTextField(6);
        JTextField firstNameText = new JTextField(6);
        JTextField addressText = new JTextField(6);
        JTextField emailText = new JTextField(6);
        JTextField phoneText = new JTextField(6);
        JTextField searchCustomerText = new JTextField(10);
        JButton addCustomerButton = new JButton("Add");
        addCustomerButton.addActionListener(
        		al -> {
        			Customer cust = new Customer();
        			cust.lastName = lastNameText.getText();
        			cust.firstName = firstNameText.getText();
        			cust.address = addressText.getText();
        			cust.email = emailText.getText();
        			cust.phoneNumber = phoneText.getText();
        			c.addCustomer(cust);
        		}
        		);
        JButton deleteCustomerButton = new JButton("Delete");
        JButton customerSearchButton = new JButton(" Search ");
        customerSearchButton.addActionListener(
        		al -> {
        			List<Customer> matches = c.searchCustomerNameAny(searchCustomerText.getText());
        			for (Customer customer : matches) {
        				// Show it in the pane to the right
        			}
        		}
        		);
        JScrollPane shoppingCart = new JScrollPane();
        //dimensions of containers
        Dimension size = new Dimension(600,225);
        Dimension size2 = new Dimension (300, 225);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                centerSalesComponent.setLayout(new GridLayout(5, 2));
            rightComponent.setPreferredSize(size2);
            rightComponent.setLayout(new BorderLayout());
                rightCartComponent.setLayout(new BorderLayout());
                    rightGridComponent.setLayout(new FlowLayout());     
                    rightFlowComponent.setLayout(new FlowLayout());
        //boolean that is called from constructor
        String title;
        if (makePanel) {
            title = "Customer Database";
        } else {
            title = "";
        }
        //placing components
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setBackground(new Color(255,234,206));
        pane.add(component);
            component.add(centerComponent, BorderLayout.CENTER);
                centerComponent.add(centerSalesComponent, BorderLayout.CENTER);
                centerComponent.add(addCustomerButton, BorderLayout.PAGE_END);
                centerComponent.add(addCustomerLabel, BorderLayout.PAGE_START);
                centerComponent.setBackground(new Color(255,245,230));
                    centerSalesComponent.setBackground(new Color(255,245,230));
                    centerSalesComponent.add(lastNameLabel);
                    centerSalesComponent.add(lastNameText);
                    centerSalesComponent.add(firstNameLabel);
                    centerSalesComponent.add(firstNameText);
                    centerSalesComponent.add(addressLabel);
                    centerSalesComponent.add(addressText);
                    centerSalesComponent.add(emailLabel);
                    centerSalesComponent.add(emailText);
                    centerSalesComponent.add(phoneLabel);
                    centerSalesComponent.add(phoneText);
            component.add(rightComponent, BorderLayout.LINE_END);
                rightComponent.add(searchCustomerLabel, BorderLayout.PAGE_START);
                rightComponent.add(rightCartComponent, BorderLayout.CENTER);
                rightComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(shoppingCart, BorderLayout.CENTER);
                    rightCartComponent.add(rightGridComponent, BorderLayout.PAGE_START);
                        rightGridComponent.setBackground(new Color(255,245,230));
                        rightGridComponent.add(searchCustomerText);
                        rightGridComponent.add(customerSearchButton);
                    rightCartComponent.add(rightFlowComponent, BorderLayout.PAGE_END);
                        rightFlowComponent.add(deleteCustomerButton);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    }// End Customer Panel Method
    
    protected JPanel createCatalogPanel(boolean makePanel){ //Begin Catalog Method
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent centerSalesComponent = new JPanel();
                JComponent centerBottomComponent = new JPanel();
            JComponent rightComponent = new JPanel();
                JComponent rightCartComponent = new JPanel();
                    JComponent rightGridComponent = new JPanel();
                    JComponent rightFlowComponent = new JPanel();
        //initializing GUI features
        JLabel styleLabel = new JLabel("Style:");
        JLabel colorLabel = new JLabel("Color:");
        JLabel sizeLabel = new JLabel("Size:");
        JLabel collectionLabel = new JLabel("Collection:");
        JLabel QuantityLabel = new JLabel("Quantity:");
        JLabel addClothingLabel = new JLabel("       Add New Item");
        JLabel searchClothingLabel = new JLabel("       Item Search ");
        JTextField styleText = new JTextField(6);
        JTextField colorNameText = new JTextField(6);
        JTextField collectionText = new JTextField(6);
        JTextField quantityText = new JTextField(6);
        JTextField searchClothingText = new JTextField(10);
        JComboBox sizeBox = new JComboBox();
        JButton addClothingButton = new JButton("Add");
        addClothingButton.addActionListener(
        		al -> {
        			Item i = new Item(styleText.getText(), colorNameText.getText(), "Medium", Integer.parseInt(quantityText.getText()), 9.99, 14.99, 798798732);
        			c.addItem(i);
        		}
        		);
        JButton deleteClothingButton = new JButton("Delete");
        JButton clothingSearchButton = new JButton(" Search ");
        JButton loadInventoryButton = new JButton ("Load");
        JScrollPane shoppingCart = new JScrollPane();
        //dimensions of containers
        Dimension size = new Dimension(600,225);
        Dimension size2 = new Dimension (300, 225);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                centerSalesComponent.setLayout(new GridLayout(5, 2));
                centerBottomComponent.setLayout(new FlowLayout());
            rightComponent.setPreferredSize(size2);
            rightComponent.setLayout(new BorderLayout());
                rightCartComponent.setLayout(new BorderLayout());
                    rightGridComponent.setLayout(new FlowLayout());     
                    rightFlowComponent.setLayout(new FlowLayout());
        //boolean that is called from constructor
        String title;
        if (makePanel) {
            title = "Clothing Database";
        } else {
            title = "";
        }
        //placing components
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setBackground(new Color(255,234,206));
        pane.add(component);
            component.add(centerComponent, BorderLayout.CENTER);
                centerComponent.add(centerSalesComponent, BorderLayout.CENTER);
                centerComponent.add(centerBottomComponent, BorderLayout.PAGE_END);
                centerComponent.setBackground(new Color(255,245,230));
                    centerBottomComponent.add(addClothingButton);
                    centerBottomComponent.add(loadInventoryButton);
                    centerBottomComponent.setBackground(new Color(255,245,230));
                centerComponent.add(addClothingLabel, BorderLayout.PAGE_START);
                    centerSalesComponent.add(styleLabel);
                    centerSalesComponent.add(styleText);
                    centerSalesComponent.add(colorLabel);
                    centerSalesComponent.add(colorNameText);
                    centerSalesComponent.add(sizeLabel);
                    centerSalesComponent.add(sizeBox);
                    centerSalesComponent.add(collectionLabel);
                    centerSalesComponent.add(collectionText);
                    centerSalesComponent.add(QuantityLabel);
                    centerSalesComponent.add(quantityText);
                    centerSalesComponent.setBackground(new Color(255,245,230));
            component.add(rightComponent, BorderLayout.LINE_END);
                rightComponent.add(searchClothingLabel, BorderLayout.PAGE_START);
                rightComponent.add(rightCartComponent, BorderLayout.CENTER);
                rightComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(shoppingCart, BorderLayout.CENTER);
                    rightCartComponent.add(rightGridComponent, BorderLayout.PAGE_START);
                    rightCartComponent.setBackground(new Color(255,245,230));
                        rightGridComponent.add(searchClothingText);
                        rightGridComponent.add(clothingSearchButton);
                        rightGridComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(rightFlowComponent, BorderLayout.PAGE_END);
                        rightFlowComponent.add(deleteClothingButton);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    } // End of Clothing Database
    
    protected JPanel createSalesPanel(boolean makePanel){ //Begin Sales Method
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent centerSalesComponent = new JPanel();
            JComponent rightComponent = new JPanel();
                JComponent rightCartComponent = new JPanel();
                    JComponent rightGridComponent = new JPanel();
                    JComponent rightFlowComponent = new JPanel();
        //initializing GUI features
        JLabel customerLabel = new JLabel("Customer:");
        JLabel itemLabel = new JLabel("Item:");
        JLabel sizeLabel = new JLabel("Size:");
        JLabel costLabel = new JLabel("Cost:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel addSaleLabel = new JLabel("  Add Sale Item  ");
        JLabel addCartLabel = new JLabel("  Cart  ");
        JLabel sizeLabel1 = new JLabel("Size:");
        JLabel itemLabel1 = new JLabel("Item:");
        JLabel costLabel1 = new JLabel("Cost:");
        JLabel quantityLabel1 = new JLabel("Quantity:");
        JTextField customerText = new JTextField(6);
        JComboBox itemBox = new JComboBox();
        JComboBox sizeBox = new JComboBox();
        JTextField costText = new JTextField(6);
        JTextField quantityText = new JTextField(6);
        JTextField totalText = new JTextField (6);
        JButton addSaleButton = new JButton("Add to Cart");
        JButton completeSaleButton = new JButton ("Complete Sale");
        JScrollPane shoppingCart = new JScrollPane();
        //dimension of main component container
        Dimension size = new Dimension(600,225);
        Dimension size2 = new Dimension (300, 225);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                centerSalesComponent.setLayout(new GridLayout(5, 2));
            rightComponent.setPreferredSize(size2);
            rightComponent.setLayout(new BorderLayout());
                rightCartComponent.setLayout(new BorderLayout());
                    rightGridComponent.setLayout(new GridLayout(1, 3));     
                    rightFlowComponent.setLayout(new FlowLayout());
        //boolean that is called from constructor
        String title;
        if (makePanel) {
            title = "Sales";
        } else {
            title = "";
        }
        //placing components
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setBackground(new Color(255,234,206));
        pane.add(component);
            component.add(centerComponent, BorderLayout.CENTER);
                centerComponent.add(centerSalesComponent, BorderLayout.CENTER);
                centerComponent.add(addSaleButton, BorderLayout.PAGE_END);
                centerComponent.add(addSaleLabel, BorderLayout.PAGE_START);
                centerComponent.setBackground(new Color(255,245,230));
                    centerSalesComponent.add(customerLabel);
                    centerSalesComponent.add(customerText);
                    centerSalesComponent.add(itemLabel);
                    centerSalesComponent.add(itemBox);
                    centerSalesComponent.add(sizeLabel);
                    centerSalesComponent.add(sizeBox);
                    centerSalesComponent.add(costLabel);
                    centerSalesComponent.add(costText);
                    centerSalesComponent.add(quantityLabel);
                    centerSalesComponent.add(quantityText);
                    centerSalesComponent.setBackground(new Color(255,245,230));
            component.add(rightComponent, BorderLayout.LINE_END);
                rightComponent.add(addCartLabel, BorderLayout.PAGE_START);
                rightComponent.add(rightCartComponent, BorderLayout.CENTER);
                rightComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(shoppingCart, BorderLayout.CENTER);
                    rightCartComponent.add(rightGridComponent, BorderLayout.PAGE_START);
                    rightCartComponent.setBackground(new Color(255,245,230));
                        rightGridComponent.add(quantityLabel1);
                        rightGridComponent.add(itemLabel1);
                        rightGridComponent.add(sizeLabel1);
                        rightGridComponent.add(costLabel1);
                        rightGridComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(rightFlowComponent, BorderLayout.PAGE_END);
                        rightFlowComponent.add(completeSaleButton);
                        rightFlowComponent.add(totalText);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    }// end sales panel method
    
    protected JPanel createReportPanel(boolean makePanel){ // begin report panel method
        //creating panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent topComponent = new JPanel();
            JComponent bottomComponent = new JPanel();
        //initializing GUI features
        JLabel displayLabel = new JLabel("Display Reports");
        JLabel reportLabel = new JLabel("Export Reports");
        JButton inventoryButton = new JButton("Inventory");
        JButton customerButton = new JButton("Customers");
        JButton SalesMonthButton = new JButton("Sales/Month");
        JButton SalesAllButton = new JButton("All Sales");
        JButton bestsellersButton = new JButton("Bestsellers");
        JButton inventoryReportButton = new JButton("Inventory");
        JButton salesReportButton = new JButton("Sales");
        JButton customerReportButton = new JButton("Customers");
        JScrollPane reportPane = new JScrollPane();
        //dimension of main component container
        Dimension size = new Dimension(600,500);
        Dimension sizeMax = new Dimension (1000, 700);
        component.setMaximumSize(sizeMax);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            topComponent.setLayout(new FlowLayout());//layout of subcontainer
            bottomComponent.setLayout(new FlowLayout());
        //Boolean called from constructor
        String title;
        if (makePanel) {
            title = "Reports";
        } else {
            title = "Mismatched";
        }
        //placing components
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setBackground(new Color(255,234,206));
        pane.add(component);
            component.add(reportPane, BorderLayout.CENTER);
            component.add(topComponent, BorderLayout.PAGE_START);
                topComponent.add(displayLabel);
                topComponent.add(inventoryButton);
                topComponent.add(customerButton);
                topComponent.add(SalesMonthButton);
                topComponent.add(SalesAllButton);
                topComponent.add(bestsellersButton);
                topComponent.setBackground(new Color(255,245,230));
            component.add(bottomComponent, BorderLayout.PAGE_END);
                bottomComponent.add(reportLabel);
                bottomComponent.add(inventoryReportButton);
                bottomComponent.add(salesReportButton);
                bottomComponent.add(customerReportButton);
                bottomComponent.setBackground(new Color(255,245,230));
        return pane;
    } // End Report Method
    protected JPanel createInventoryPanel(boolean makePanel){ //Begin Inventory Method
        //Creating Panels
        JPanel pane = new JPanel();//Creates main panel on tab
            JComponent component = new JPanel();
                JComponent topComponent = new JPanel();
                JComponent bottomComponent = new JPanel();
        //initializing GUI features
        JLabel quantityLabel = new JLabel("Enter new Quantity");
        JTextField searchText = new JTextField(12);
        JTextField quantityText = new JTextField(4);
        JButton searchButton = new JButton("Search");
        JButton changeButton = new JButton("Change Quantity");
        JButton displayAllButton = new JButton("Display all Inventory");
        JButton loadButton = new JButton("Load From File");
        JScrollPane inventoryPane = new JScrollPane();
        //dimension of main component container
        Dimension size = new Dimension(600,500);
        Dimension sizeMax = new Dimension (1000, 700);
        component.setMaximumSize(sizeMax);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        //adjusting layouts
        component.setLayout(new BorderLayout());//layout of main container
        topComponent.setLayout(new FlowLayout());//layout of subcontainer
        bottomComponent.setLayout(new FlowLayout());//layout of subcontainer
        //initializes containers boolean
        String title;
        if (makePanel) {
            title = "Search Inventory";
        } else {
            title = "Mismatched";
        }
        //placing components
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setBackground(new Color(255,234,206));
        pane.add(component);
            component.add(inventoryPane, BorderLayout.CENTER);
            component.add(topComponent, BorderLayout.PAGE_START);
                topComponent.add(searchText);
                topComponent.add(searchButton);
                topComponent.add(displayAllButton);
                topComponent.setBackground(new Color(255,245,230));
            component.add(bottomComponent, BorderLayout.PAGE_END);
                bottomComponent.add(quantityLabel);
                bottomComponent.add(quantityText);
                bottomComponent.add(changeButton);
                bottomComponent.add(loadButton);
                bottomComponent.setBackground(new Color(255,245,230));
        return pane;
    }// end Inventory Method
}