/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.aawsserver;

import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author workl
 */
@WebService ( endpointInterface = "com.mycompany.aawsserver.AAWSInterface")
public class AAWSImpl implements AAWSInterface {
    
    private static final int FILED_IDUSER = 0;
    private static final int FILED_NAMEUSER = 1;
    private static final int FILED_JOBUSER = 2;

    @Override
    public String[] getClients() {
        ArrayList<String> result = new ArrayList<>();
        
        ArrayList<String[]> datas = data();
        
        for(String[]vector : datas) {
            result.add(vector[FILED_IDUSER]+","+ vector[FILED_NAMEUSER]);
        }
        
        return result.toArray(new String[result.size()]);
    }

    private ArrayList<String[]> data() {
        ArrayList<String[]> users = new ArrayList<>();
        
            users.add(new String[]{"1","Luca Maggio","Computer Scientist"});
            users.add(new String[]{"2","Mario Rossi","INPS Employee"});
            users.add(new String[]{"3","Pino Bianchi","Medic"});
            users.add(new String[]{"4","Vincenzo Verdi","'SAPIENZA' Lecturer"});
        
        return users;
    }
    
    
}
