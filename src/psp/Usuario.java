/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Daniel Florez
 */
class Usuario {
     public String encriptado(String passwordToHash, String   salt){
      String rellenarcontras = "admin";
    try {
         MessageDigest md = MessageDigest.getInstance("SHA-512");
         md.update(salt.getBytes(StandardCharsets.UTF_8));
         byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
         StringBuilder sb = new StringBuilder();
         for(int i=0; i< bytes.length ;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         rellenarcontras = sb.toString();
        } 
       catch (NoSuchAlgorithmException e){
        e.printStackTrace();
       }
       return rellenarcontras;
}
}
