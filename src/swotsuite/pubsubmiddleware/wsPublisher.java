package swotsuite.pubsubmiddleware;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import swotsuite.framework.m3Struct;

public class wsPublisher {
	private Socket socket = null;
	

	public static void senData(m3Struct newValue) {
		 Gson gson = new Gson();
		wsPublisher client = new wsPublisher();
		// socket tcp connection
		String ip = "192.168.0.101";
		int port = 6969;
		try {
			client.socketConnect(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// writes and receives the message
		String data = gson.toJson(newValue);
		//String message = "Hello from Java Client";
		System.out.println("Sending msg to node server" + data);
		client.echo(data);
	}

	// make the connection with the socket
	private void socketConnect(String ip, int port)
			throws UnknownHostException, IOException {

		// System.out.println("[Connecting to socket...]");
		this.socket = new Socket(ip, port);
	}

	// writes and receives the full message int the socket (String)

	public void echo(String message) {
		try { // out & in
			PrintWriter out = new PrintWriter(getSocket().getOutputStream(),
					true);

			out.println(message);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	// get the socket instance
	private Socket getSocket() {
		return socket;
	}

}
