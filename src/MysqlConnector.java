import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MysqlConnector {

	public static void main(String[] args) {
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://brocode.ch/jtodo", "dbajtodo", "blubb");

			con.setReadOnly(true);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("Select * from foo");

			while (rs.next()) {

				System.out.println(rs.getString(1) + " " + rs.getString(2));

			}

			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			System.out.println("Fehler!" + e);
		}
	}
}
