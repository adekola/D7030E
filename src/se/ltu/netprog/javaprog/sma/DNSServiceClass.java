package se.ltu.netprog.javaprog.sma;



import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.jar.Attributes.Name;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.Resolver;
import se.ltu.netprog.javaprog.sma.Deliverable;
import se.ltu.netprog.javaprog.sma.Message;
import se.ltu.netprog.javaprog.sma.MessageServer;
/**
 *
 * @author ameerah
 */
public class DNSServiceClass implements Deliverable {
    

	public static final int DNS_SERVICE_MESSAGE = 200;
	public static final int DNS_SERVICE_PORT = 2005;
    private Object DClass;
	public Message send(Message m) {
		//Date today = new Date();
            String address; 
            try {
                address = InetAddress.getLocalHost().getCanonicalHostName();//InetAddress.getByName("localhost");
               m.setParam("address", address);
              /* String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
System.out.println(timeStamp );*/
               
              //  System.out.println(address.getHostAddress());
            } catch (UnknownHostException ex) {
                Logger.getLogger(DNSServiceClass.class.getName()).log(Level.SEVERE, null, ex);
            }
		return m;
        }
	public static void main(String args[]) {
		DNSServiceClass ds = new DNSServiceClass();
		MessageServer ms;
               // String inputAddress =args[2];
		try {
			ms = new MessageServer(DNS_SERVICE_PORT);
		} catch(Exception e) {
			System.err.println("Could not start DNS service " + e);
			return;
		}
		Thread msThread = new Thread(ms);
		ms.subscribe(DNS_SERVICE_MESSAGE, ds);
		msThread.start();
	}
}
    

