package kosta.cafe;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private static List<Customer> customers = new ArrayList<Customer>();

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

				String action = reader.readLine();
//				System.out.println(action);

				if(action.equals("save")) {
					String received = reader.readLine();
					if (received != null) save(received);
				} else if(action.equals("load")) {
					String send = load();
					writer.println(send);
					writer.flush();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void save(String json) {
//		System.out.println("save: "+json);
		customers = Util.toList(json);
		Util.saveAsObject(customers);
	}

	public static String load() {
		customers = Util.loadObject();
		String json = Util.toJSON(customers);
//		System.out.println("load: "+json);
		return json;
	}

}
