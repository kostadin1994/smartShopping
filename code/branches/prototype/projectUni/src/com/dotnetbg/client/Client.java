package com.dotnetbg.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


import com.dotnetbg.server.ClientRequest;
import com.dotnetbg.server.ServerResponse;

public class Client {  
	
	static Socket s;  
	static OutputStream os;  
	static InputStream is;
	static ObjectOutputStream oos;  
    static ObjectInputStream ois;
	
	

	public void connectToServer(){
		try{  
			s = new Socket("localhost",2014);  
			is = s.getInputStream();
			os = s.getOutputStream();  
			oos = new ObjectOutputStream(os);  
		    ois = new ObjectInputStream(is);
		    System.out.println("Connected to the Server ");
		}catch(Exception e){
			System.out.println("from Client.connectToServer() " + e);
		}  
	}

	public void disconnectFromServer(){
		try{  
			ois.close();
			oos.close();  
			os.close();  
			s.close();  
		}catch(Exception e){
			System.out.println("from disconnectFromSerevr " + e);
		}  
	}

	
	

	public boolean checkUser(String username, String password){
		
	    ClientRequest to = new ClientRequest("checkUser");
	    to.setUsername(username);
	    to.setPassword(password);
		try {
			oos.writeObject(to);
			ServerResponse from = (ServerResponse) ois.readObject();
			return from.getUserOK();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
public boolean exists(String username){
		
	    ClientRequest to = new ClientRequest("userExists");
	    to.setUsername(username);
		try {
			oos.writeObject(to);
			ServerResponse from = (ServerResponse) ois.readObject();
			return from.getUserOK();
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	public void createUser(User user){
	ClientRequest to= new ClientRequest ("createUser");
	// sends the user object to the server 
	to.setUser(user);
//	System.out.println("Username is: " + user.getUserName());
	try {
		oos.writeObject(to);
		System.out.println("sent");
		ServerResponse from = (ServerResponse) ois.readObject();
		from.setUserOK(true);
		System.out.println("....");
	} catch (Exception e) {
		System.out.println(e);
	}

}
	public String test (String Test){
		System.out.println(Test);
		return Test;
		
		
	}
}  
