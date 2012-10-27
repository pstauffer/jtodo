import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnector {

	public Connection getConnection() {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// config for database
			String dbname = "jtodo";
			String dbhost = "brocode.ch";
			String dbuser = "dbajtodo";
			String dbpw = "blubb";

			// db connection
			Connection dbconnection = DriverManager.getConnection(
					"jdbc:mysql://" + dbhost + "/" + dbname + "", "" + dbuser
							+ "", "" + dbpw + "");

			return dbconnection;

		} catch (Exception e) {
			System.out.println("Fehler!" + e);
			return null;
		}
	}
}