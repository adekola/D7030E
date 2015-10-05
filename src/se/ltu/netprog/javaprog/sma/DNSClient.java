/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.InetAddress;
import se.ltu.netprog.javaprog.sma.DNSServiceClass;
import se.ltu.netprog.javaprog.sma.Message;
import se.ltu.netprog.javaprog.sma.MessageClient;

/**
 *
 * @author ameerah
 */
public class DNSClient {
    
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: DNS Clientt host port");
		}
		String host = args[0];
		int port =0;
                String ip;
                
		try {
			port = Integer.parseInt(args[1]);
                        //ip=args[3];
                        
		} catch(Exception e) {
			//port = 2005;//DNSServiceClass.DNS_SERVICE_PORT;
		}
		MessageClient conn;
		try {
                    System.out.println("Before going to catch block in DNS client");
			conn = new MessageClient(host,port);
                        System.out.println(conn);
		} catch(Exception e) {
                    System.out.println("Message client connection not successful");
			System.err.println(e);
			return;
		}
		Message m = new Message();
		m.setType(DNSServiceClass.DNS_SERVICE_MESSAGE);
		m.setParam("person","neerah");
		m = conn.call(m);
		System.out.println("DNS " + m.getParam("address"));
		m.setType(75);
		m = conn.call(m);
		System.out.println("Bad reply " + m);
		conn.disconnect();
	}
        
 
    
}
