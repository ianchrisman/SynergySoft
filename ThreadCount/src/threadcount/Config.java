package threadcount;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Config extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/*
	protected String dbHost = "192.168.1.10";
	protected int dbPort = 3306;
	protected String dbName = "threadcounts";
	protected String dbUser = "thread";
	protected String dbPass = "countz";
	*/
	
        protected String dbHost = "threadcounts.cjxcxfnklzes.us-east-1.rds.amazonaws.com";
//	protected String dbHost = "54.226.6.195";
	protected int dbPort = 3306;
	protected String dbName = "threadcounts";
	protected String dbUser = "threadDELETEME";
	//protected String dbPass = "Thp3@dC0untz";  //old pass
	protected String dbPass = "Thp3adC0untz";  //current pass
	
	protected boolean ok = false;

	protected JPanel jp = new JPanel();
	protected JPanel jp1 = new JPanel();
	protected JPanel jp2 = new JPanel();
	protected JPanel jp3 = new JPanel();
	protected JLabel lblHost = new JLabel("Database Hostname or IP:");
	protected JLabel lblPort = new JLabel("Database Port:");
	protected JLabel lblUser = new JLabel("Database Username:");
	protected JLabel lblPass = new JLabel("Database Password:");
	protected JTextField txtHost = new JTextField(20);
	protected JTextField txtPort = new JTextField(20);
	protected JTextField txtUser = new JTextField(20);
	protected JTextField txtPass = new JTextField(20);
	protected JPasswordField passField = new JPasswordField("Thp3adC0untz");
	
	
	
	protected JButton jbOK = new JButton("OK");
	
	public Config() {
		super("Configuration settings");
		setSize(420,200);
		jp.setLayout(new BorderLayout());
		jp1.setLayout(new GridLayout(4, 1, 1, 1));
		jp2.setLayout(new GridLayout(4, 1, 1, 1));
		txtHost.setText(dbHost);
		txtPort.setText(String.valueOf(dbPort));
		txtUser.setText(dbUser);
		txtPass.setText(dbPass);
		jp1.add(lblHost);
		jp1.add(lblPort);
		jp1.add(lblUser);
		jp1.add(lblPass);
		jp2.add(txtHost);
		jp2.add(txtPort);
		jp2.add(txtUser);
		//jp2.add(txtPass);
		jp2.add(passField);
		passField.setEchoChar('*');
		
		jp.add(jp1, BorderLayout.WEST);
		jp.add(jp2, BorderLayout.EAST);
		jbOK.addActionListener(al -> {
			updateConfig();
		});
		jp.add(jbOK, BorderLayout.SOUTH);
		add(jp);
	}
	
	public String toString() {
		return dbHost + dbName + dbPort + dbUser + dbPass;
	}
	
	protected void updateConfig() {
		// Method called when 'OK' is pressed
		dbHost = txtHost.getText();
		dbPort = Integer.parseInt(txtPort.getText());
		dbUser = txtUser.getText();
		//dbPass = txtPass.getText();
		dbPass = String.valueOf(passField.getPassword());
		ok = true;
		this.setVisible(false);
	}
	
}