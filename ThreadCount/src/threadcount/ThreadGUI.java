package threadcount;
//ThreadGUI Class
//This class creates the GUI and all tabbed panes
//Final Programming Project


import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

class ThreadGUI extends JPanel { // Begin ThreadGUI Class
    
    private Controller c = new Controller();
    JLabel blankLabel = new JLabel();
    JButton inventoryButton, customerButton, customerSearchButton, catalogSearchButton;
    JTextArea customerLog, catalogLog, salesLog, reportLog, inventoryLog;
    JTextField searchCustomerText, searchCatalogText;
    
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
        
        JPanel inventoryPanel = new JPanel(); // Calls Inventory Method
            inventoryPanel.add(createInventoryPanel(true));
            inventoryPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Inventory  ", inventoryPanel);

        JPanel reportPanel = new JPanel(); // Calls Report Method
            reportPanel.add(createReportPanel(true));
            reportPanel.setBackground(new Color(253,226,190));
            tabbedPane.addTab("  Reports  ", reportPanel);

        //Add tabbedPane to this panel.
        add(tabbedPane, BorderLayout.CENTER);
            tabbedPane.setBackground(new Color(255,178,102));
            
        //////Action Listeners/////
        customerSearchButton.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e){displayCustSearch ();}}); 
        
        catalogSearchButton.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e){displayCatSearch ();}}); 
        
        inventoryButton.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e){displayAllInvenReport ();}}); 
        
        customerButton.addActionListener(new ActionListener(){ 
        @Override
        public void actionPerformed(ActionEvent e){displayAllCustReport ();}}); 
            
            
    } // end ThreadGUI constructor
    
    public static void ThreadGUI() {
    }    
    
    public static void createGUI(){ //begin CreateGui method, called from Main Method
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
    }    
    
    protected JPanel createCustomerPanel(boolean makePanel){ 
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
            JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent centerCatalogComponent = new JPanel();
                JComponent bottomComponent = new JPanel();
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
        JLabel customerIDLabel = new JLabel(" Enter Customer ID to Delete");
        JTextField lastNameText = new JTextField(6);
        JTextField firstNameText = new JTextField(6);
        JTextField addressText = new JTextField(6);
        JTextField emailText = new JTextField(6);
        JTextField phoneText = new JTextField(6);
        JTextField customerIDText = new JTextField(8);
        searchCustomerText = new JTextField(10);
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
        customerSearchButton = new JButton(" Search ");
        customerLog = new JTextArea(20,40);
        customerLog.setMargin(new Insets(5,5,5,5));
        customerLog.setEditable(false); 
        JScrollPane shoppingCart = new JScrollPane(customerLog);
        //dimensions of containers
        Dimension size = new Dimension(800,300);
        Dimension size2 = new Dimension (500,300);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                centerCatalogComponent.setLayout(new GridLayout(6, 2));
                bottomComponent.setLayout(new FlowLayout());
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
                centerComponent.add(centerCatalogComponent, BorderLayout.CENTER);
                centerComponent.add(bottomComponent, BorderLayout.PAGE_END);
                    bottomComponent.add(addCustomerButton);
                    bottomComponent.setBackground(new Color(255,245,230));
                centerComponent.add(addCustomerLabel, BorderLayout.PAGE_START);
                centerComponent.setBackground(new Color(255,245,230));
                    centerCatalogComponent.setBackground(new Color(255,245,230));
                    centerCatalogComponent.add(lastNameLabel);
                    centerCatalogComponent.add(lastNameText);
                    centerCatalogComponent.add(firstNameLabel);
                    centerCatalogComponent.add(firstNameText);
                    centerCatalogComponent.add(addressLabel);
                    centerCatalogComponent.add(addressText);
                    centerCatalogComponent.add(emailLabel);
                    centerCatalogComponent.add(emailText);
                    centerCatalogComponent.add(phoneLabel);
                    centerCatalogComponent.add(phoneText);
                    centerCatalogComponent.add(blankLabel);
                    centerCatalogComponent.add(blankLabel);
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
                        rightFlowComponent.add(customerIDLabel);
                        rightFlowComponent.add(customerIDText);
                        rightFlowComponent.add(deleteCustomerButton);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    }// End Customer Panel Method
    
    protected JPanel createCatalogPanel(boolean makePanel){ //Begin Catalog Method
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent centerCustomerComponent = new JPanel();
                JComponent centerBottomComponent = new JPanel();
            JComponent rightComponent = new JPanel();
                JComponent rightCartComponent = new JPanel();
                    JComponent rightGridComponent = new JPanel();
                    JComponent rightFlowComponent = new JPanel();
        //initializing GUI features
        JLabel styleLabel = new JLabel("Style:");
        JLabel colorLabel = new JLabel("Color:");
        JLabel sizeLabel = new JLabel("Size:");
        JLabel skuLabel = new JLabel("SKU:");
        JLabel costLabel = new JLabel("Wholesale Price:");
        JLabel priceLabel = new JLabel("Retail Price:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel addClothingLabel = new JLabel("       Adding Catalog Item");
        JLabel searchClothingLabel = new JLabel("       Item Search ");
        JLabel catalogIDLabel = new JLabel(" Enter SKU to Delete");
        JTextField styleText = new JTextField(6);
        JTextField colorNameText = new JTextField(6);
        JTextField sizeText = new JTextField(6);
        JTextField skuText = new JTextField(6);
        JTextField costText = new JTextField(6);
        JTextField priceText = new JTextField(6);
        JTextField quantityText = new JTextField(6);
        searchCatalogText = new JTextField(10);
        JTextField catalogIDText = new JTextField(8);
        JButton addClothingButton = new JButton("Add");
        addClothingButton.addActionListener(
        		al -> {
        			Item i = new Item(styleText.getText(), colorNameText.getText(), "Medium", Integer.parseInt(quantityText.getText()), 9.99, 14.99, 798798732);
        			c.addItem(i);
        		}
        		);
        JButton deleteClothingButton = new JButton("Delete");
        catalogSearchButton = new JButton(" Search ");
        catalogLog = new JTextArea(20,40);
        catalogLog.setMargin(new Insets(5,5,5,5));
        catalogLog.setEditable(false); 
        JButton loadInventoryButton = new JButton ("Load from File");
        JScrollPane shoppingCart = new JScrollPane(catalogLog);
        //dimensions of containers
        Dimension size = new Dimension(800,375);
        Dimension size2 = new Dimension (500,375);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                centerCustomerComponent.setLayout(new GridLayout(8, 2));
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
                centerComponent.add(centerCustomerComponent, BorderLayout.CENTER);
                centerComponent.add(centerBottomComponent, BorderLayout.PAGE_END);
                centerComponent.setBackground(new Color(255,245,230));
                    centerBottomComponent.add(addClothingButton);
                    centerBottomComponent.add(loadInventoryButton);
                    centerBottomComponent.setBackground(new Color(255,245,230));
                centerComponent.add(addClothingLabel, BorderLayout.PAGE_START);
                    centerCustomerComponent.add(skuLabel);
                    centerCustomerComponent.add(skuText);
                    centerCustomerComponent.add(styleLabel);
                    centerCustomerComponent.add(styleText);
                    centerCustomerComponent.add(colorLabel);
                    centerCustomerComponent.add(colorNameText);
                    centerCustomerComponent.add(sizeLabel);
                    centerCustomerComponent.add(sizeText);
                    centerCustomerComponent.add(priceLabel);
                    centerCustomerComponent.add(priceText);
                    centerCustomerComponent.add(costLabel);
                    centerCustomerComponent.add(costText);
                    centerCustomerComponent.add(quantityLabel);
                    centerCustomerComponent.add(quantityText);
                    centerCustomerComponent.add(blankLabel);
                    centerCustomerComponent.add(blankLabel);
                    centerCustomerComponent.setBackground(new Color(255,245,230));
            component.add(rightComponent, BorderLayout.LINE_END);
                rightComponent.add(searchClothingLabel, BorderLayout.PAGE_START);
                rightComponent.add(rightCartComponent, BorderLayout.CENTER);
                rightComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(shoppingCart, BorderLayout.CENTER);
                    rightCartComponent.add(rightGridComponent, BorderLayout.PAGE_START);
                    rightCartComponent.setBackground(new Color(255,245,230));
                        rightGridComponent.add(searchCatalogText);
                        rightGridComponent.add(catalogSearchButton);
                        rightGridComponent.setBackground(new Color(255,245,230));
                    rightCartComponent.add(rightFlowComponent, BorderLayout.PAGE_END);
                        rightFlowComponent.add(catalogIDLabel);
                        rightFlowComponent.add(catalogIDText);
                        rightFlowComponent.add(deleteClothingButton);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    } // End of Clothing Database
    
    protected JPanel createSalesPanel(boolean makePanel){ //Begin Sales Method
        //Creating Panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent centerComponent = new JPanel();
                JComponent bottomComponent = new JPanel();
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
        JLabel totalLabel= new JLabel(" Total:  ");
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
        Dimension size = new Dimension(800,325);
        Dimension size2 = new Dimension (500, 225);
        component.setPreferredSize(size);
        //Adjusting Layouts of components
        component.setLayout(new BorderLayout());//layout of main container
            centerComponent.setLayout(new BorderLayout());// Center sub container
                bottomComponent.setLayout(new FlowLayout());
                centerSalesComponent.setLayout(new GridLayout(7, 2));
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
                centerComponent.add(bottomComponent, BorderLayout.PAGE_END);
                    bottomComponent.add(addSaleButton);
                    bottomComponent.setBackground(new Color(255,245,230));
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
                    centerSalesComponent.add(blankLabel);
                    centerSalesComponent.add(blankLabel);
                    centerSalesComponent.add(blankLabel);
                    centerSalesComponent.add(blankLabel);
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
                        rightFlowComponent.add(totalLabel);
                        rightFlowComponent.add(totalText);
                        rightFlowComponent.add(completeSaleButton);
                        rightFlowComponent.setBackground(new Color(255,245,230));
        return pane;
    }// end sales panel method

    protected JPanel createInventoryPanel(boolean makePanel){ //Begin Inventory Method
        //Creating Panels
        JPanel pane = new JPanel();//Creates main panel on tab
            JComponent component = new JPanel();
                JComponent topComponent = new JPanel();
                JComponent bottomComponent = new JPanel();
        //initializing GUI features
        JLabel quantityLabel = new JLabel("New Quantity:");
        JLabel skuLabel = new JLabel ("Enter SKU:");
        JTextField searchText = new JTextField(12);
        JTextField quantityText = new JTextField(4);
        JTextField skuText = new JTextField(6);
        JButton searchButton = new JButton("Search");
        JButton changeButton = new JButton("Change Quantity");
        JButton displayAllButton = new JButton("Display all Inventory");
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
                topComponent.add(displayAllButton);
                topComponent.add(searchText);
                topComponent.add(searchButton);
                topComponent.setBackground(new Color(255,245,230));
            component.add(bottomComponent, BorderLayout.PAGE_END);
                bottomComponent.add(skuLabel);
                bottomComponent.add(skuText);
                bottomComponent.add(quantityLabel);
                bottomComponent.add(quantityText);
                bottomComponent.add(changeButton);
                bottomComponent.setBackground(new Color(255,245,230));
        return pane;
    }// end Inventory Method
    
    public JPanel createReportPanel(boolean makePanel){ // begin report panel method
        //creating panels
        JPanel pane = new JPanel();//Main Panel, its like a tree.  Sub  panel to sub panel
        JComponent component = new JPanel();
            JComponent topComponent = new JPanel();
            JComponent bottomComponent = new JPanel();
        //initializing GUI features
        JLabel displayLabel = new JLabel("Display Reports");
        JLabel reportLabel = new JLabel("Export Reports");
        inventoryButton = new JButton("Inventory");
        customerButton = new JButton("Customers");
        JButton SalesMonthButton = new JButton("Sales/Month");
        JButton SalesAllButton = new JButton("All Sales");
        JButton bestsellersButton = new JButton("Bestsellers");
        JButton inventoryReportButton = new JButton("Inventory");
        JButton salesReportButton = new JButton("Sales");
        JButton customerReportButton = new JButton("Customers");
        reportLog = new JTextArea(20,40);
        reportLog.setMargin(new Insets(5,5,5,5));
        reportLog.setEditable(false); 
        JScrollPane reportPane = new JScrollPane(reportLog);
        //dimension of main component container
        Dimension size = new Dimension(800,500);
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

    void displayCustSearch (){
        String cSearchString = searchCustomerText.getText();
    	List<Customer> foundCustomers = c.searchCustomerNameAny(cSearchString);
        System.out.println(cSearchString);
    	customerLog.setText("Customer search rersults for: " + cSearchString + " \n");
    	for (Customer cust : foundCustomers) {
    		System.out.println(cust.toString());
                customerLog.append(cust.toString());
        }
    } 
    void displayCatSearch (){
        String iSearchString = searchCatalogText.getText();
    	List<Item> foundItems = c.searchItemStyle(iSearchString);
        System.out.println(iSearchString);
    	catalogLog.setText("Catalog search rersults for: " + iSearchString + " \n");
    	for (Item item : foundItems) {
    		System.out.println(item.toString());
                catalogLog.append(item.toString());
        }
    } 
    void displayAllInvenReport ()
    {
        List<Item> allItems = c.getAllItems();
    	reportLog.setText("\nAll Items:\n");
    	for (Item item : allItems) {
    		reportLog.append(item.toString() + "\n");
        }
    }
    void displayAllCustReport ()
    {
    	List<Customer> customers = c.getAllCustomers();
        reportLog.setText("\nAll Customers:\n");
    	for (Customer customer : customers) {
    		reportLog.append(customer.toString() + "\n");
    	}
    }
    
}//end class ThreadGUI
