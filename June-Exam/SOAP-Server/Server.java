/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapjune;

import javax.xml.ws.Endpoint;


/**
 *
 * @author biar
 */
public class Server {
    public static void main(String[] args) {
        String address = "http://localhost:9000/professor";
        ProfessorManagementImpl implementor = new ProfessorManagementImpl();
        Endpoint.publish(address, implementor);
        System.out.println("Server ProfessorManagement ready..");
        
    }
    
    
}
