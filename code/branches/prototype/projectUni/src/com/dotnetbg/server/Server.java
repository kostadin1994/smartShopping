package com.dotnetbg.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String args[]) {  
		int port = 2014;  
		boolean listening = true;
		System.out.println("Starting Server...");
		try {  
			ServerSocket ss = new ServerSocket(port);  
			while (listening) {
				Socket s = ss.accept();  
				//new ThreadServer(s).start();
				System.out.println("Thread started");
			}	
		}catch(Exception e){
			System.out.println(e);}  
		}  
}
