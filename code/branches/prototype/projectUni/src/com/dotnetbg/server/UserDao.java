package com.dotnetbg.server;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.dotnetbg.client.User;
import com.dotnetbg.server.ConnectDB;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class UserDao implements Serializable{
	static Connection connection;
	public  final Connection getConnection() throws SQLException {
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("driver found");
			 connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/uniProject","root", "innovate");
				System.out.println("connected test");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return connection;
		
	}
   
	public boolean checkUser(String username, String password){
	
		try{
			connection=getConnection();
		    String query = "SELECT * FROM users";
		    	
		      Statement st = (Statement) connection.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      while (rs.next()){
		    	  if (rs.getString("username").equals(username) && rs.getString("pass").equals(password)) {
		    		  System.out.println("username to check is "+ username);
		    		  return true;
		    		  
		    	  }
		      }
		      
		      st.close();
		    }
		    catch (Exception e)
		    {
		      System.err.println(e.getMessage());
		    }

		return false;
	}
	public void createUser(String username,String password,String fname,String lname){
		try {
			connection=getConnection();
			// create the statement 
			 Statement st = (Statement) connection.createStatement();
			int balance = 0;
			// insert the data
			st.executeUpdate("INSERT INTO `users`(username,pass,fname,lname,balance) VALUE ('"+username+"','"+password+"','"+fname+"','"+lname+"','"+balance+"')");
			System.out.println("added");
		 } catch (Exception e) { 
	            System.err.println("Got an exception! "); 
	            e.printStackTrace();
		}
	}
public static boolean userExists(String username){
		try{
		    String query = "SELECT * FROM users";
		      Statement st = (Statement) connection.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      while (rs.next()){
		    	  if (rs.getString("username").equals(username)) {
		    		  return true;
		    	  }
		      }
		      
		      st.close();
		    }
		
		    catch (Exception e)
		    {
		      System.err.println(e.getMessage());
		    }
	
		return false;	

	}

}