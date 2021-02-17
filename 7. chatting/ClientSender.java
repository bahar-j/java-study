package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSender extends Thread {
	Socket socket;
	
	public ClientSender() {}
	
	public ClientSender(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 콘솔 입력으로부터 읽어서 소켓에 써줌
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			
			while(true) {
				String userInput = reader.readLine();
				if(userInput.equals("end")) break;
				writer.println(userInput);
				writer.flush();
			}
			
			System.out.println("퇴장 완료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
