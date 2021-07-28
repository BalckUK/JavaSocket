package application;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	ExecutorService executorService;
	ServerSocket serverSocket;
	List<Client> connections = new Vector<>();

	void startServer() {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
		} catch (Exception e) {
			if (!serverSocket.isClosed()) {
				stopServer();
			}
			return;
		}

		Runnable runable = new Runnable() {

			@Override
			public void run() {
				javafx.application.Platform.runLater(() -> {
					displayText("서버 시작");
					btnStartStop.setText("stop");
				});

				while (true) {
					try {
						Socket socket = serverSocket.accept();
						String message = "연결수락 : " + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName();
						javafx.application.Platform.runLater(() -> {
							displayText(message);
						});

						Client client = new Client(socket);
						connections.add(client);
						javafx.application.Platform.runLater(() -> {
							displayText("연결 개수"+ connections.size(););
						});

					} catch (Exception e) {
						if(!serverSocket.isClosed()) {
							stopServer();
						}
						break;
					}
				}
			}
		};
		executorService.submit(runable);
	}

	void stopServer() {

	}

	class Client {
		Client(Socket socket) {

		}
	}

	void displayText(String text) {
		txtDisplay.appendText(text + "\n");
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub

	}
}
