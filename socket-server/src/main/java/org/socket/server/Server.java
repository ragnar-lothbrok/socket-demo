package org.socket.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class Server {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ServerSocket socket = null;
		DataInputStream inputStream = null;
		PrintStream printStream = null;
		Socket clientSocket = null;

		try {
			socket = new ServerSocket(9999);

			clientSocket = socket.accept();

			String line = null;
			inputStream = new DataInputStream(clientSocket.getInputStream());
			printStream = new PrintStream(clientSocket.getOutputStream());
			// As long as we receive data, echo that data back to the client.
			while (true) {
				line = inputStream.readLine();
				System.out.println(line);
				if(line == null){
					break;
				}
				printStream.println(line);
			}

		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to");
		}

	}
}
