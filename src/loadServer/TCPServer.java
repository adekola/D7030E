package loadServer;

//Requires a single command line arg - the port number
import java.net.*;	// need this for InetAddress, Socket, ServerSocket 

import java.io.*;	// need this for I/O stuff
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    static final Logger logger = Logger.getLogger(TCPServer.class.getName());
    // define a constant used as size of buffer 
    static final int BUFSIZE = 1024;

    // main starts things rolling
    static public void main(String args[]) throws Exception {
        int clientNumber = 0;
        try ( // socket;

                ServerSocket listener = new ServerSocket(9090)) {
            while (true) {
                //information log, just to notify of the readiness of the server to accept connections
                logger.log(Level.INFO, "Ready to receive connections on port 9090");
                new ClientHandler(listener.accept(), clientNumber++).start();
            }
        }
    }

}
