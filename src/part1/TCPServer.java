package part1;

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
        //check the log level to use
        String sLogLevel;
        Level logLevel;
        if (args.length == 0) {
            sLogLevel = "info";
        } else {
            sLogLevel = args[0];
        }

        switch (sLogLevel) {
            case "warning":
                logLevel = Level.WARNING;
                break;
            case "debug":
                logLevel = Level.FINER;
                break;
            case "error":
                logLevel = Level.SEVERE;
                break;
            case "info":
            default:
                logLevel = Level.INFO; //defaults to info
        }
        logger.setLevel(logLevel);
       
        try ( // socket;

                ServerSocket listener = new ServerSocket(9090)) {
            while (true) {
                //information log, just to notify of the readiness of the server to accept connections
                logger.log(Level.INFO, "Ready to receive connections on port 9090");
                new ClientHandler(listener.accept(), clientNumber++, logLevel).start();
            }
        }
    }

}
