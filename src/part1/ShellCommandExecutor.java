/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellCommandExecutor {

    /*
	public static void main(String[] args) {

		ShellCommandExecutor obj = new ShellCommandExecutor();

		String domainName = "google.com";
		
		//in mac oxs
		String command = "ls /media";
		
		//in windows
		//String command = "ping -n 3 " + domainName;
		
		String output = obj.executeCommand(command);

		System.out.println(output);

	}

    */
	public String executeCommand(String[] command) {

            
		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}

}
