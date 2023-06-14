package vtiger.Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;

import com.mysql.jdbc.Driver;


public class JDBCPracticeFetchingData {

	public static void main(String[] args) throws Throwable  
	{
		//step1: Register driver
        Driver driverRef=new Driver();
        DriverManager.registerDriver(driverRef);

        //step2: connection to database

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weekendbatch","root","root");
        
        //step3: Create sql statement
        Statement stat = conn.createStatement();
        String query = "select*from students";
        //step4: execute query
       ResultSet result = stat.executeQuery(query);
       
       while(result.next())
       {
    	   System.out.println(result.getString(1)+"\t"+result.getInt(2)+"\t"+result.getString(3));
       }
       
       //step5: close database connection
       conn.close();
}}
