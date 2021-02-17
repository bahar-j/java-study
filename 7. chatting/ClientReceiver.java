package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiver extends Thread {
	
	Socket socket;
	
	public ClientReceiver() {}
	
	public ClientReceiver(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 소켓으로부터 읽어서 화면에 출력
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while(true) {
				String msg = br.readLine();
				if (msg == null) break;
				System.out.println(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
