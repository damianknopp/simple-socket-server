package dmk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientHandler implements Runnable {
	Socket socket;
	static String hostName;
	String hostAndPort;
	private static String program = "cs730 SuperDuper Server";
	private static float version = .01f;
	private static String programVersion = program + " " + version;

	static{
		try{
		hostName = ClientHandler.hostAddr();
		}catch(UnknownHostException ue){
			ue.printStackTrace();
			hostName = "n/a";
			
		}
	}
	
	public ClientHandler(final Socket socket) {
		super();
		if(socket == null){
			throw new RuntimeException("socket was null! Cannot create client handler!");
		}
		this.socket = socket;
		this.hostAndPort = 	hostName + ":" + this.socket.getPort();

	}

	public void run(){
		// SocketChannel channel = this.socket.getChannel();
		// ByteBuffer dstBuf = ByteBuffer.allocate(1024);
		byte[] buf = new byte[1024];
		try {
			InputStream is = this.socket.getInputStream();
			BufferedInputStream br = new BufferedInputStream(is);
			OutputStream os = this.socket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			ProtoHandler handler = new ProtoHandler();
			// System.out.println("channel " + channel + " dstBuffer=" +
			// dstBuf);
			// int len = channel.read(dstBuf);
			int len = br.read(buf);
			while (len > 0) {
				System.out.println(Thread.currentThread().getName() + " recieved data from client, read " + len + " bytes.");
				dump(buf, len);
				dumpAsHex(buf, len);
				handler.stateMachine(bos, buf, len, programVersion, hostAndPort);
//				 len = channel.read(dstBuf);
				len = br.read(buf);
				System.out.println(Thread.currentThread().getName() + " read of next buffer len=" + len);
			}
			System.out.println(Thread.currentThread().getName() + " nothing left to read len=" + len);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			try{
			if(socket != null) this.socket.close();
			}catch(IOException ioe){}
		}
	}


	private static String hostAddr() throws UnknownHostException{
		InetAddress inet = InetAddress.getLocalHost();
		String hostname = inet.getHostAddress();
		return hostname;
	}

	
	protected void dump(final byte[] buf, int len) {
		System.out.print("as decimal ");
		for (int i = 0; i < len; i++) {
			System.out.print(buf[i] + " ");
		}
		System.out.println();
	}
	
	protected void dumpAsHex(final byte[] buf, int len) {
		System.out.println("as hex " + getHex(buf, len));
	}

	static final String HEXES = "0123456789ABCDEF";

	public String getHex(byte[] raw, int len) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		byte b;
		for (int i = 0; i < len; i++) {
			b = raw[i];
//			System.out.println("b = " + b + " " + (b & 0xF0) + " "
//				+ ((b & 0xF0) >> 4) + " " + (b & 0x0F));
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}
}
