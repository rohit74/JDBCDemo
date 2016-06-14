
/**
 * Importing necessary packages 
 */
import java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class PreparedStatementDemo {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/emp";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	public static void main(String args[]) {
		/*Creating a reference of coonection interface*/
		Connection connection= null;
		Employee empl= new Employee();
		try{

			/*Loading the database driver*/
			Class.forName("com.mysql.jdbc.Driver");

			/*Establishing connection using the
			 * getConnection() method of the driver manager
			 */
			connection = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Connection established");

			/*To execute SQL statements, we need to instantiate 
			 * a PreparedStatement object from the connection 
			 * object by using the prepareStatement () method.
			 * */

			PreparedStatement preparedStatement = connection.prepareStatement("select * from Employee where id=?");

			preparedStatement.setInt(1,4);

			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				empl.setFirst(resultSet.getString("first_name"));
				empl.setSalary(resultSet.getDouble("salary"));
				System.out.print("Name: "+empl.getFirst());
				System.out.println("Salary: "+empl.getSalary());
			}
			/*it is considered good practice to close 
			 * resultset
			 */
			resultSet.close(); 

			/*Updating using preparedstatement
			 * Though stattement interface can also be used for
			 * DML operations , it is considered a better approach 
			 * to use preparedstatement interface*/
			preparedStatement=connection.prepareStatement("Update Employee set salary=? where id=?");
			preparedStatement.setDouble(1, 40000);
			preparedStatement.setInt(2, 4);
			int affectedRows= preparedStatement.executeUpdate();
			System.out.println("No of rows updated is "+affectedRows);
		}

		catch(Exception exception) {
			System.out.println(exception);
		}
		/*It is preferred to close connection in finally block
		 * within a Try-Catch since .close() can throw an exception*/

		finally	
		{
			if(connection!=null)
			{
				try
				{
					connection.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
	}
}
