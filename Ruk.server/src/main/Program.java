package main;

import srv.Server;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Launching server");
		
		Server server = new Server();
		server.start();
		
		System.out.println("Server stopped");
	}

}
