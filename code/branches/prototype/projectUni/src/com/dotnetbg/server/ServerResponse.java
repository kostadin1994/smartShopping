package com.dotnetbg.server;

import java.io.Serializable;




public class ServerResponse implements Serializable{
	
	private int id;
	
	private boolean userOK = false;

	

	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	

	public void setUserOK(boolean userOK) {
		this.userOK = userOK;
	}

	public boolean getUserOK() {
		return userOK;
	}

}
