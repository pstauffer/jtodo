import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class MysqlInsert extends MysqlConnector {

	public static void main(String[] args) {
		try {

			Connection dbconnection = (Connection) new MysqlConnector()
					.getConnection();

			// create our java jdbc statement
			Statement statement = dbconnection.createStatement();

			// insert into
			statement
					.executeUpdate("INSERT INTO foo (team) VALUES ('Simpson')");
			System.out.println("insert ok!");

			// close everything
			statement.close();
			dbconnection.close();

		} catch (Exception e) {
			System.out.println("Fehler!" + e);
		}
	}
}
