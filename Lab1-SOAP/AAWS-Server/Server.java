/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aawsserver;

import com.mycompany.aawsserver.AAWSImpl;
import javax.xml.ws.Endpoint;

/**
 *
 * @author workl
 */
public class Server {
    
    public static void main(String[] args) {
        AAWSImpl impl = new AAWSImpl();
        String url = "http://localhost:8080/aaaws";
        
        Endpoint.publish(url,impl);
        
        System.out.println("Server AAAWS ready..");
    }
    
    
}
