/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapbankserver;

import javax.xml.ws.Endpoint;

/**
 *
 * @author workl
 */
    public class Server {
    public static void main(String args[]) throws InterruptedException {
        BankImpl implementor = new BankImpl();
        String address = "http://localhost:9000/bank";
        Endpoint.publish(address, implementor);
        System.out.println("Server BANK ready..");
        /*Thread.sleep(360 * 1000);
        System.out.println("Server exiting");
        System.exit(0);*/
    }
}

