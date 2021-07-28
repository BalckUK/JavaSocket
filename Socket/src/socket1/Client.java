package socket1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		Socket socket = null;

		try {
			socket = new Socket();
			System.out.println("연결 요청");
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("연결 성공");
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (!socket.isClosed()) {
			try {
				System.out.println("연결 종료");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
