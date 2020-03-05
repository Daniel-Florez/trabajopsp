/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Daniel Florez
 */
public class Servidor {
      public static void main(String[] args)
 {
 int port=2050;
 try
 {
 ServerSocket serverSocket = new ServerSocket(port);
 
 Socket clientSocket = serverSocket.accept();
 
 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
 BufferedReader in = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));
 
 String inputLine = in.readLine();
 System.out.println("" + inputLine); 
 String outputLine = inputLine.toUpperCase(); 
 System.out.println("" + outputLine); 
 out.println(outputLine); 
 
 out.close(); 
 in.close(); 
 
 clientSocket.close(); 
 serverSocket.close();
 }
 catch (UnknownHostException e){
 System.out.println(e);
 } catch (IOException e) {
 System.out.println(e);
 }
 }
}
