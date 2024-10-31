package rental_system.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/carrental_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Cyb3r@234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}


/*import java.sql.*;

public class DbConnection {
    public static void main(String args[]){  

    //String query = "INSERT INTO  customers (customer_id, cust_name, city, grade, salesman_id)"
    //+ " VALUES (3010, 'Franklin', 'Manhattan', 200, '5007');"; 
    
    //String query = "update customers set grade = 300 where customer_id = 3001;"; 
    
    //String query = "delete from customers where customer_id = 3010;";
    
    try{  
        
        //step2 create  the connection object  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","Cyb3r@234");  
        
        //step3 create the statement object  

        Statement stmt = con.createStatement();  
        ResultSet rs=stmt.executeQuery("select * from customers");  

        //insert query
        //int num = stmt.executeUpdate(query);
        //System.out.println("\nNumber of rows affected is " + num + "\n");

        //step4 execute query  
        while(rs.next())  
        System.out.println(rs.getString("customer_id")+" "+rs.getString("cust_name")); 
        
        //step5 close the connection object  
        con.close();  
        
    } catch(Exception e){ 
        System.out.println(e);
        e.printStackTrace();
    }    
}  
}*/



