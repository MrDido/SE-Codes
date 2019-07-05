/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapbankserver;

import javax.jws.WebService;

/**
 *
 * @author workl
 */
@WebService
public interface BankInterface {
   
    /*
    **  - getOperationsByClientID (int):
    **
    **  given a ClientID (integer), which is the code for a client, returns the IDs of all bank
    **  operations performed in the last days by that client.
    */
    
    java.lang.String[] getOperationsByClientID (int ClientID);
    
    /*
    **  - getOperationsDetailsByID (int):
    **
    **  given an opID (integer), returns the full details of the given bank operation, 
    **  as a unique String in the format: '[ID,ID of the performing client, date,amount,description]'
    */
    
    java.lang.String[] getOperationDetailsByID (int opID);
}
