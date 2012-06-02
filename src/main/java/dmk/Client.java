package dmk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class Client {
	Socket socket = null;
	OutputStream os = null;
	private BufferedOutputStream br = null;
	private InputStream is = null;
	private BufferedInputStream inBr = null;

	public Client(final int port) throws IOException {
		System.out.println("Starting client connecting to port " + port);
		socket = new Socket("localhost", port);
		os = socket.getOutputStream();
		br = new BufferedOutputStream(os);
		is = socket.getInputStream();
		inBr = new BufferedInputStream(is);
	}

	public static void main(final String[] args) {
		if (args.length < 1) {
			System.out
					.println("Usage: java -cp ./<jar_file_name> dmk.Client <port>");
			System.exit(0);
		}
		final int port = Integer.parseInt(args[0]);
		try {
			final Client c = new Client(port);
			c.client();
			c.endClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void client() throws IOException {
		System.out.println(Thread.currentThread().getName() + " client initiating a hello.");
		String hello = "hello";
		byte[] word = hello.getBytes("ASCII");
		sendHeader((byte)0, (byte)TypeField.TEXT.getType(), (byte)word.length);
		br.write(word);
		br.flush();

		System.out.println(Thread.currentThread().getName() + " client reading the response.");		
		byte[] inputBuf = new byte[1024];
		int inputLen = inBr.read(inputBuf);
		System.out.println(Thread.currentThread().getName() + " recieved " + new String(inputBuf));		
//				while(inputLen > 0){
//			System.out.println(Thread.currentThread().getName() + " recieved " + new String(inputBuf));		
//			inputLen = inBr.read(inputBuf);
//			System.out.println(Thread.currentThread().getName() + " next buffer size = " + inputLen);
//		}
//		System.out.println(Thread.currentThread().getName() + " done recieving.");
		
		InputStream in = System.in;
		BufferedReader bufIn = new BufferedReader(new InputStreamReader(in));
		System.out.print("enter number 1>");
		String num1 = bufIn.readLine();
		int firstInt = Integer.parseInt(num1);
		System.out.println(Thread.currentThread().getName() + " sending 1st int=" + firstInt);
		//send 2
		sendHeader((byte)2, (byte)TypeField.INT.getType(), (byte)4);
		br.write(firstInt);
		br.flush();
		
		System.out.print("enter number 2>");		
		String num2 = bufIn.readLine();
		int secondInt = Integer.parseInt(num2);
		System.out.println(Thread.currentThread().getName() + " sending secondInt=" + secondInt);
		//send 3
		sendHeader((byte)3, (byte)TypeField.INT.getType(), (byte)4);
		br.write(secondInt);
		br.flush();
		
		
		//send 4 ask for result
		System.out.println(Thread.currentThread().getName() + " asking for result.");
		sendHeader((byte)4, (byte)TypeField.UNK.getType(), (byte)4);
		br.flush();
		Arrays.fill(inputBuf, (byte)0x0);
		inputLen = inBr.read(inputBuf);
		System.out.println(Thread.currentThread().getName() + " recieved " + inputLen + " byte[0] value=" + inputBuf[0]);		


	}
	
	public void sendHeader(byte state, byte type, byte len) throws IOException{
		br.write(state);
		br.write(type);
		br.write(len);
	}

	public void endClient() throws IOException{
		try {
			System.out.println("shutting down client...");
			//any other clean up..
		} finally {
			if (os != null)
				os.close();
			if (is != null)
				is.close();
			if (socket != null)
				socket.close();
		}
	}

}