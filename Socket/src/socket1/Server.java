package socket1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;

		try {
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 5001));
			while (true) {
				System.out.println("클라이언트와 연결 기다림...");
				Socket socket = server.accept();
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("연결 수락함 : " + isa.getHostName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!server.isClosed()) {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
