package GestionStock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClassConnexion {

	String urlbase = "jdbc:mysql://localhost:3306/BaseRestaurent";
	String urlpilote="com.mysql.jdbc.Driver";
	Connection conn;
	String username = "ndong";
	String password = "seigneur";

	public ClassConnexion() {

		try {
			
			Class.forName(urlpilote);
			conn = DriverManager.getConnection(urlbase, username, password);
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();} 
		catch (SQLException ex) {
			throw new IllegalStateException("Cannot connect the database!", ex);}

	}

	public Connection getConnexion() {
		return conn;
	}

}
