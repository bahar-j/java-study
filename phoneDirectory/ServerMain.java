package kosta.phone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServerMain {
	
	static List<PhoneInfo> savedData = new LinkedList<PhoneInfo>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		try {
			serverSocket = new ServerSocket(9000);
			while(true) {
				socket = serverSocket.accept();
				
				PrintWriter writer = new PrintWriter(socket.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				String userInput = reader.readLine();
				
				if (userInput.equals("8")) {
					String data = reader.readLine();
					if (data != null) save(data);
				}
				
				if (userInput.equals("9")) {
					String loaded = load();
					writer.println(loaded);
					writer.flush();
				}
				
				if (userInput.equals("quit")) {
					break;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void save(String data) {
		List<PhoneInfo> convertedData = Util.JSONtoObject(data);
		savedData = convertedData;
	}
	
	public static String load() {
		return Util.objectToJSONString(savedData);
	}

}
