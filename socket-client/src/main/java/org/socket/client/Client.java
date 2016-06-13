package org.socket.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class Client {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Socket socket = null;
		DataOutputStream outputStream = null;
		DataInputStream inputStream = null;

		try {
			socket = new Socket("localhost", 9999);
			outputStream = new DataOutputStream(socket.getOutputStream());
			inputStream = new DataInputStream(socket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to");
		}

		if (socket != null && outputStream != null && inputStream != null) {
			try {
				outputStream.writeBytes("Sending message...");

				String responseLine;
				while ((responseLine = inputStream.readLine()) != null) {
					System.out.println("Server: " + responseLine);
					if (responseLine.indexOf("Ok") != -1) {
						break;
					}
				}
				outputStream.close();
				inputStream.close();
				socket.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
			}
		}
	}
}
