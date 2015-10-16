package se.ltu.netprog.javaprog.sockets;


import java.net.*;// need this for InetAddress, Socket, ServerSocket 
import java.io.*;// need this for I/O stuff

public class UDPEchoServer { 
	static final int BUFSIZE=1024;
	
	static public void main(String args[]) throws SocketException 
	{ 
		
		if (args.length != 1) {
			throw new IllegalArgumentException("Must specify a port!"); 
						
		}
		
		int port = Integer.parseInt(args[0]);
		DatagramSocket s = new DatagramSocket(port);
		DatagramPacket dp = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
		
		try { 
			while (true) {
				s.receive(dp);
				// print out client's address 
				System.out.println("Message from " + dp.getAddress().getHostAddress());
				
				// Send it right back 
                                byte[] meName = "Adekola".getBytes();
                                dp.setData(meName);
                                /*
                                byte[] result = new byte[BUFSIZE+ "Adekola".getBytes().length];
                                System.arraycopy(dp.getData(), 0, result.length, 0, dp.getData().length);
                                System.arraycopy(meName, 0, result.length, dp.getData().length, meName.length);
				*/
                                s.send(dp); 
				dp.setLength(BUFSIZE);// avoid shrinking the packet buffer
				
			} 
		} catch (IOException e) {
			System.out.println("Fatal I/O Error !"); 
			System.exit(0);
			
		} 

	}
}