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

/**
 *
 * @author adekola
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private int clientNumber;

    public ClientHandler(Socket socket, int clientNumber) {
        this.socket = socket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        //have  timer kinda thing so that scoket is closed and client disconnected after a period of inactivity

        try {

            String result = "";
                // Decorate the streams so we can send characters
            // and not just bytes.  Ensure output is flushed
            // after every newline.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a welcome message to the client.
            out.println("Hello, you are client #" + clientNumber + ".");

                
            String input = in.readLine();
            System.out.println(input);
                
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
                out.println(result);

        } catch (IOException e) {
            log("Error handling client# " + clientNumber + ": " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log("Couldn't close a socket, what's going on?");
            }
            log("Connection with client# " + clientNumber + " closed");
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
