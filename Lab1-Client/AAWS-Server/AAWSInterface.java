/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aawsserver;

import javax.jws.WebService;

/**
 *
 * @author 1456606@maggio
 */

@WebService
public interface AAWSInterface {
    
     /*
    **  - getClients ():
    **
    **  returns all the IDs and names of clients 
    **  stored in the security sub-system.
    */
    
    java.lang.String[] getClients();
}
