package org.articulo.modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	public  Connection connection;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	

	public void connectdatabaseOracle() throws IOException, SQLException, ClassNotFoundException {
		
			Class.forName(driver);
			connection = DriverManager.getConnection(URL, "system", "farj1433");
			System.out.println("Conexion Exitosa: Oracle11g");
		
			
		
	}

}
