import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

public class MysqlSelect extends MysqlConnector {

	public static void main(String[] args) {
		try {

			Connection dbconnection = (Connection) new MysqlConnector()
					.getConnection();

			// only useful for selects
			dbconnection.setReadOnly(true);

			Statement statement = dbconnection.createStatement();

			// db query
			ResultSet result = statement.executeQuery("Select * from foo");

			// loop for the query's
			while (result.next()) {
				System.out.println(result.getString(1) + " "
						+ result.getString(2));
			}

			// close everything
			result.close();
			statement.close();
			dbconnection.close();

		} catch (Exception e) {
			System.out.println("Fehler!" + e);
		}
	}
}
