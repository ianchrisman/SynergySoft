/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threaddb_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class ThreadDB_Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Statement statement = null;
        Connection connect = null;
        // URL to the database. Note on the end it has the port and DB name.
        //mine uses port 3306, that's MySQLs default port.  database name is ThreadCountsDB
        String MYSQL_URL = "jdbc:mysql://threadcountsdb.clddhnwsp529.us-east-1.rds.amazonaws.com:3306/ThreadCountsDB";
        //Next line activates the driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB, with user "Thread" and password "CountsDB"
        connect = DriverManager.getConnection(MYSQL_URL,"Thread","CountsDB");
        statement = connect.createStatement();
        // inside the quotes is the SQL statement to get what you want from the database
        ResultSet rs = statement.executeQuery("SELECT * FROM Untitled");
        //prints out some column headings
        System.out.printf("%15s%15s%15s%25s%25s\n", "Customer ID", "NameL", "NameF", "Address", "Email");
        // prints out the ResulSet
        while (rs.next()) {
            System.out.printf("%15s%15s%15s%25s%25s\n",rs.getInt(1) + "", rs.getString(2), rs.getString(3) + "", rs.getString(4), rs.getString(5));
                    }
        //close the connection and result set
        rs.close();
        connect.close();

    }//end main
    
}//end class
