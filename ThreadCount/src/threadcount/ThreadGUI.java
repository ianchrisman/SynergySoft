package threadcount;
//Ian Chrisman

import java.awt.*;
import static java.awt.Component.BOTTOM_ALIGNMENT;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

class ThreadGUI extends JPanel {
    public ThreadGUI(){
        super(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel customerRow = new JPanel();        
        customerRow.add(createButtonRow(false));
        customerRow.add(createButtonRow(true));
        tabbedPane.addTab("  Customer  ", customerRow);
        
        JPanel catalogRow = new JPanel();
        catalogRow.add(createLabelAndComponent(false));
        catalogRow.add(createLabelAndComponent(true));
        tabbedPane.addTab("  Catalog  ", catalogRow);
        
        JPanel buttonAndComponent = new JPanel();
        buttonAndComponent.add(createYAlignmentExample(false));
        buttonAndComponent.add(createYAlignmentExample(true));
        tabbedPane.addTab("  Sales  ", buttonAndComponent);
        
        JPanel reportRow = new JPanel();
        reportRow.add(createReportWindow(true));
        tabbedPane.addTab("  Reports  ", reportRow);
        
        JPanel inventoryRow = new JPanel();
        inventoryRow.add(createReportWindow(true));
        tabbedPane.addTab("  Inventory  ", inventoryRow);
        
        
        //Add tabbedPane to this panel.
        add(tabbedPane, BorderLayout.CENTER);
        
        
    }
    protected JPanel createReportWindow(boolean doItRight) {
        JPanel pane = new JPanel();

        JComponent component = new JPanel();
        Dimension size = new Dimension(150,100);
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        TitledBorder border = new TitledBorder(
                                  new LineBorder(Color.black),
                                  "A JPanel",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        component.setBorder(border);

        JLabel label = new JLabel("This is a JLabel");
        String title;
        if (doItRight) {
            title = "Matched";
            label.setAlignmentX(CENTER_ALIGNMENT);
        } else {
            title = "Mismatched";
        }

        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(label);
        pane.add(component);
        return pane;
    }
    protected JPanel createYAlignmentExample(boolean doItRight) {
        JPanel pane = new JPanel();
        String title;

        JComponent component1 = new JPanel();
        Dimension size = new Dimension(100, 50);
        component1.setMaximumSize(size);
        component1.setPreferredSize(size);
        component1.setMinimumSize(size);
        TitledBorder border = new TitledBorder(
                                  new LineBorder(Color.black),
                                  "A JPanel",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        component1.setBorder(border);

        JComponent component2 = new JPanel();
        size = new Dimension(100, 50);
        component2.setMaximumSize(size);
        component2.setPreferredSize(size);
        component2.setMinimumSize(size);
        border = new TitledBorder(new LineBorder(Color.black),
                                  "A JPanel",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        component2.setBorder(border);

        if (doItRight) {
            title = "Matched";
        } else {
            component1.setAlignmentY(TOP_ALIGNMENT);
            title = "Mismatched";
        }

        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(component1);
        pane.add(component2);
        return pane;
    }
    
    protected JPanel createLabelAndComponent(boolean doItRight) {
        JPanel pane = new JPanel();

        JComponent component = new JPanel();
        Dimension size = new Dimension(300,500);
        component.setMaximumSize(size);
        component.setPreferredSize(size);
        component.setMinimumSize(size);
        TitledBorder border = new TitledBorder(
                                  new LineBorder(Color.black),
                                  "Results",
                                  TitledBorder.CENTER,
                                  TitledBorder.BELOW_TOP);
        border.setTitleColor(Color.black);
        component.setBorder(border);

        JLabel label = new JLabel("I can add Text boxes, drop downs, whatever");
        String title;
        if (doItRight) {
            title = "New Customer";
            label.setAlignmentX(CENTER_ALIGNMENT);
        } else {
            title = "Search";
            label.setAlignmentX(CENTER_ALIGNMENT);
        }

        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.add(label);
        pane.add(component);
        return pane;
    }
    
    
    
    
    
    
    
    
    
    
    
        protected JPanel createButtonRow(boolean changeAlignment) {
        JButton button1 = new JButton("A JButton");
        button1.setVerticalTextPosition(AbstractButton.BOTTOM);
        button1.setHorizontalTextPosition(AbstractButton.CENTER);

        JButton button2 = new JButton("Another JButton");
        button2.setVerticalTextPosition(AbstractButton.BOTTOM);
        button2.setHorizontalTextPosition(AbstractButton.CENTER);

        String title;
        if (changeAlignment) {
            title = "Desired";
            button1.setAlignmentY(BOTTOM_ALIGNMENT);
            button2.setAlignmentY(BOTTOM_ALIGNMENT);
        } else {
            title = "Default";
        }

        JPanel pane = new JPanel();
        pane.setBorder(BorderFactory.createTitledBorder(title));
        pane.setLayout(new BoxLayout(pane, BoxLayout.X_AXIS));
        pane.add(button1);
        pane.add(button2);
        return pane;
    }
    public static void ThreadGUI() {

    }
    public static void createGUI(){
    //Window Set up
    JFrame frame = new JFrame("ThreadCounts");    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Content Pane
    ThreadGUI threadPane = new ThreadGUI();
    threadPane.setOpaque(true);
    frame.setContentPane(threadPane);
    frame.pack();
    frame.setVisible(true);
    System.out.println("got here creategui");
    }
}