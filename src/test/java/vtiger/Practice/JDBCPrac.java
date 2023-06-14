package vtiger.Practice;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class JDBCPrac {

	public static void main(String[] args) throws Throwable {

		//register
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//connection to db
		//DriverManager.getConnection("htt", null, null)
	}

}
