package dmk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

	public Server(final int port) {
		try {
			System.out.println("Starting server on port " + port);
			ServerSocket server = new ServerSocket(port);
			BlockingQueue queue = new LinkedBlockingQueue();
			ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 60,
					TimeUnit.SECONDS, queue);

			while (true) {
				Socket s = server.accept();
				pool.execute(new ClientHandler(s));
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(final String[] args) {
		if(args.length < 1){
			System.out.println("Usage: java -cp ./<jar_file_name> dmk.Server <port>");
			System.exit(0);
		}
		final int port = Integer.parseInt(args[0]);
		new Server(port);
	}

}
