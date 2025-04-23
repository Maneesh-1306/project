package travel.management.system;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load driver
            c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/travelmanagementsystem", 
                "root", 
                "Maneesh@2006"
            ); // Full URL with port
            s = c.createStatement();
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
    }
}
