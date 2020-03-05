/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Daniel Florez
 */
public class Cliente {
  {
 int port=2050;
 try
 {
 Socket clientSocket = new Socket("192.168.1.37",port);
 
 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
 BufferedReader in = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));
 
 System.out.println(""); 
 out.println("");
 System.out.println("" + in.readLine());
 
 out.close();
 in.close();
 clientSocket.close();
 }
 catch (UnknownHostException e){
 System.out.println(e);
 }
 catch (IOException e) {
 System.out.println(e);
 }
 }
}
