import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnect {

	//DB related info
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/world";
	static final String DB_USERNAME = "root";
	static final String DB_PASSWORD = "Solukhumbu1%";
	
	public static void main(String[] args) {
		
		System.out.println("--Testing Connection--");
		//Step1 : Loading the DB driver
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("where is the driver");
			e.printStackTrace();
			return;
		}
		
		System.out.println("The driver is registered");
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("connection failed");
			e.printStackTrace();
			return;
		}
		
		if (conn != null) {
			System.out.println("Successful. You are now connected");
		} else {
			System.out.println("Failed to make a connection");
		}
	}
}