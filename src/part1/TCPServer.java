package part1;

//Requires a single command line arg - the port number
import java.net.*;	// need this for InetAddress, Socket, ServerSocket 

import java.io.*;	// need this for I/O stuff
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServer {

    // define a constant used as size of buffer 
    static final int BUFSIZE = 1024;

    // main starts things rolling
    static public void main(String args[]) throws Exception {
        int clientNumber = 0;
        try ( // socket;
                ServerSocket listener = new ServerSocket(9090)) {
            while (true) {
                System.out.println("Ready to receive connections on port 9090");
                new ClientHandler(listener.accept(), clientNumber++).start();
            }
        }
    }
    
}
                