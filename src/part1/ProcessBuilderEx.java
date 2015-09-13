/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package part1;


import java.io.IOException;
import java.util.*;
/**
 *
 * @author adekola
 */
public class ProcessBuilderEx {
  
  
  /* public static void main(String[] args) throws Exception
  {
    new ProcessBuilderEx(new ArrayList<String>());
  } */

  // can run basic ls or ps commands
  // can run command pipelines
  // can run sudo command if you know the password is correct
  public ProcessBuilderEx(List<String> cmds) throws IOException, InterruptedException
  {
          
    // build the system command we want to run
    List<String> commands = new ArrayList<String>();
    commands.add("/bin/sh");
    commands.add("-c");
    commands.add("ls -l /var/");
   
      if (cmds.size() == 0){
          cmds = commands;
      }
      else
          throw new IllegalArgumentException();
      
     

    // execute the command
    SysCommandExecutor commandExecutor = new SysCommandExecutor(commands);
    int result = commandExecutor.executeCommand();

    // get the stdout and stderr from the command that was run
    StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
    StringBuilder stderr = commandExecutor.getStandardErrorFromCommand();
    
    // print the stdout and stderr
    System.out.println("The numeric result of the command was: " + result);
    System.out.println("STDOUT:");
    System.out.println(stdout);
    System.out.println("STDERR:");
    System.out.println(stderr);
  }
}
