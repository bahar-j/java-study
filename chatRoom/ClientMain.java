package chatRoom;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {

	public static void main(String[] args) {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket("127.0.0.1", 9000);
			ClientReceiver receiver = new ClientReceiver(clientSocket);
			ClientSender sender = new ClientSender(clientSocket);
			receiver.start();
			sender.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
