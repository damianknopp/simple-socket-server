package dmk;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class ProtocolHandler2 implements Runnable {
	Socket socket;

	public ProtocolHandler2(final Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// SocketChannel channel = this.socket.getChannel();
		// ByteBuffer dstBuf = ByteBuffer.allocate(1024);
		byte[] buf = new byte[1024];
		try {
			InputStream is = this.socket.getInputStream();
			BufferedInputStream br = new BufferedInputStream(is);
			// System.out.println("channel " + channel + " dstBuffer=" +
			// dstBuf);
			// int len = channel.read(dstBuf);
			int len = br.read(buf);
			while (len > 0) {
				System.out.println("len =" + len);
				dump(buf, len);
				//switch on ID field byte[0]
				if(buf[0] == 0){
					byte[] arr = new byte[len];
					System.out.println("len =" + len);
					System.arraycopy(buf, 1, arr, 0, len);
					dump(arr, len);
					String s = new String(arr);
					System.out.println("interpret as 0 string=" + s);
					//send back ip address and port
					
				}else if(buf[0] == 1){
					System.out.println("interpret as 1 ");
				}else if(buf[0] == 2){
					System.out.println("interpret as 2 ");
				}else if(buf[0] == 3){
					System.out.println("interpret as 3 ");
				}else if(buf[0] == 4){
					System.out.println("interpret as 4 ");
				}else{
					
				}
				// len = channel.read(dstBuf);
				len = br.read(buf);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	protected void dump(final byte[] buf, int len) {
//		for (int i = 0; i < len; i++) {
//			System.out.print(buf[i]);
//		}
		System.out.println(getHex(buf, len));
		System.out.println();
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
