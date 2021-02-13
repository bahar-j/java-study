package chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server extends Thread {

	Socket socket;
	// 모든 server thread가 공유 -> static
	PrintWriter writer = null;
	static List<PrintWriter> streamPerClient = Collections.synchronizedList(new ArrayList<PrintWriter>());

	public Server() {}

	public Server(Socket socket) {
		super();
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
			streamPerClient.add(writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessageToAll(String msg) {
		for(PrintWriter stream: streamPerClient) {
			stream.println(msg);
			stream.flush();
		}
	}

	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String message = br.readLine();
				if(message == null) break;
				sendMessageToAll(message);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			sendMessageToAll("### 퇴장하셨습니다 ###");
			streamPerClient.remove(writer);
			try {
				socket.close();
			} catch (Exception e2){
				e2.printStackTrace();
			}
		}
	}
}
