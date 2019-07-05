/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clientlab1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author biar
 */
public class ClientMain {
    public static void main(String[] args) {
        AAWSInterface portaaws = new AAWSImplService().getAAWSImplPort();
        List<String> clients = portaaws.getClients();
        
        BankInterface portbank = new BankImplService().getBankImplPort();
        
        List<Integer> id_clients = new ArrayList<>();
        List<Integer> id_operations = new ArrayList<>();
        for(String s : clients){
            String[] temp = s.split(",");
            id_clients.add(Integer.parseInt(temp[0]));
            String[] temp_operation = portbank.getOperationsByClientID(Integer.parseInt(temp[0])).toString().replace("[", "").replace("]", "").replace(" ","").split(",");
            for(String o : temp_operation){
                id_operations.add(Integer.parseInt(o));
            }
        }
        //filtro clienti con operazione con descrizione contenente "Benzina Autosrada
        List<String> result_clients = new ArrayList<>();
        for(int i : id_operations){
            String[] temp_operation = portbank.getOperationDetailsByID(i).toString().split(",");
            //System.out.println(temp_operation.toString().split(",")[1]);
            
            if(temp_operation[4].replace("]","").equals("Benzina Autostrada")){
                
                result_clients.add(temp_operation[1]);
            }
        }
        
        /*System.out.println(id_clients.toString());
        System.out.println(id_operations.toString());
        System.out.println(result_clients.toString());*/
        System.out.println("All the clients in the AAWS Server ===> " +clients.toString());
        System.out.println("These are the clients that made operation Benzina Autostrada: ");
        for(String i : result_clients){
            for(String a : clients){
                if(a.contains(i)){
                    System.out.println(a);
                }
            }
        }
    }
    
}
