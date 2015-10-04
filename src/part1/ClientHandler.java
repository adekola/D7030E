/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.FileHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adekola
 */
public class ClientHandler extends Thread {

    static final Logger logger = Logger.getLogger(ClientHandler.class.getName());
    static FileHandler handler;

    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber, Level logLevel) {
        logger.setLevel(logLevel);
        init();
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    static void init() {
        try {
            handler = new FileHandler("ClientHandlerLogs.log", true);
            logger.addHandler(handler);
        } catch (IOException e) {
                
        }

    }

    public void run() {

        try {

            String result = "";
            // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a welcome message to the client, and log that client nnumber [x] has been accepted
            logger.log(Level.INFO, "Welcoming client no: {0}", clientNumber);
            out.println("Hello, you are client #" + clientNumber + ".");

            String input;
            while ((input = in.readLine()) != null) {
                //simply informational
                logger.log(Level.INFO, "Client sent in this: {0}", input);
                //done reading the input stream, now do some useful stuff

                if (tryParseInt(input)) {
                    //do the loopy thingy
                    Integer count = Integer.parseInt(input);
                    Loopy lp = new Loopy();
                    Integer intOut = lp.Loop(count);
                    result = intOut.toString();
                    //write to outputstream

                } else {
                    String[] cmd = {
                        "/bin/sh",
                        "-c",
                        input
                    };

                    // execute the command
                    ShellCommandExecutor ex = new ShellCommandExecutor(cmd);
                    result = ex.executeCommand();
                }

                //done with generating a result..now tlak back to the client, common
                logger.log(Level.INFO, "Sending this back to client: {0} \n", result);
                out.println(result);
            }
        } catch (IOException e) {
            //An error has occured, a severe level of reporting is appropriate and then...
            logger.log(Level.SEVERE, "Error handling client# {0}", clientNumber);
            //...debug information to understand what happened
            logger.log(Level.FINER, e.toString());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                //An error has occured, a severe level of reporting is appropriate and then...
                logger.log(Level.SEVERE, "Couldn't close a socket, what's going on?");
                //...debug information to understand what happened
                logger.log(Level.FINER, e.toString());
            }
            logger.log(Level.INFO, "Connection with client {0} has been closed", clientNumber);
        }
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Logs a simple message. In this case we just write the message to the
     * server applications standard output.
     */
    private void log(String message) {
        System.out.println(message);
    }

}
