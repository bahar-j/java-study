package chatRoom;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9000);
			while(true) {
				socket = serverSocket.accept();
				System.out.print("닉네임을 입력하세요: ");
				Thread serverThread = new Server(socket);
				serverThread.start();
			}
		} catch (Exception e){
			e.printStackTrace();
		}

	}
}
