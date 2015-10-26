
package com.dotnetbg.server;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public final class ConnectDB {
		
	public  final Connection getConnection() throws SQLException {
		Connection connection= null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("driver found");
			 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/uniProject","kk247", "Kostadin1994");
				System.out.println("connected test");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		
	}

}
