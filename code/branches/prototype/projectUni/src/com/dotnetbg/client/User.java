// creates a new User object
package com.dotnetbg.client;

import java.io.Serializable;

public class User implements Serializable {
	public String userName,password,Email,firstName,Surname;
	public User(){	
	}
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String name) {
		this.userName = name;
	}

	public String getEmail(){
		return Email;
	}
	
	public void setEmail(String email) {
		this.Email = email;
	}

	public void setPassword(String pass){
		this.password=pass;
}
	public String getPassword(){
		return password;
	}
	public void setFirstName(String fName){
		this.firstName=fName;
	}
	public void setSurname(String lName){
		this.Surname=lName;
	}
	public String getSurname(){
		return Surname;
	}
	public String getFirstName(){
		return firstName;
	}
}