package com.dotnetbg.server;

import java.io.Serializable;
import java.util.ArrayList;




import com.dotnetbg.client.User;
import com.mysql.jdbc.PreparedStatement;

public class ClientRequest implements Serializable {
	
	String requestName = "";

	private User user;

	private String username;
	private String password;

	
	public ClientRequest(String requestName){
		
		this.requestName = requestName;
	}
	
	public String getRequestName () {
		
		return requestName;
	}
	
	public void setRequestName(String requestName) {
		
		this.requestName = requestName;
	}
	
	

	public String getUsername() {
		
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}

	public String getPassword() {
		
		return password;
	}
	
	public void setPassword(String password) {
		
		this.password = password;
	}
	public void setUser(User user){
		this.user=user;
	}
		public User	getUser(){
			return user;
		}
}
