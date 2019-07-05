/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapbankserver;

import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author workl
 */

@WebService(endpointInterface = "com.mycompany.soapbankserver.BankInterface")
public class BankImpl implements BankInterface {
    
    private static final int FIELD_IDOPERATION = 0;
    private static final int FIELD_IDCUSTOMER = 1;
    private static final int FIELD_DATE = 2;
    private static final int FIELD_AMOUNT = 3;
    private static final int FIELD_DESCRIPTION = 4;

    @Override
    public String[] getOperationsByClientID(int ClientID) {
        ArrayList<String[]> datas;
        ArrayList<String> result = new ArrayList<>();
        
        datas = chargeOperations();
        
        for(String[]vector : datas)
            if(Integer.parseInt(vector[FIELD_IDCUSTOMER]) == ClientID){
                result.add(vector[FIELD_IDOPERATION]);
            }
        
        
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String[] getOperationDetailsByID(int opID) {
        ArrayList<String[]> datas ;
        ArrayList<String> result = new ArrayList<>();
        
        datas = chargeOperations();
        
        for(String[]vector : datas)
            if( Integer.parseInt(vector[FIELD_IDOPERATION]) == opID) {
                return new String[]{
                        vector[FIELD_IDOPERATION]+","+
                        
                        vector[FIELD_IDCUSTOMER]+","+
                        
                        vector[FIELD_DATE]+","+
                        
                        vector[FIELD_AMOUNT]+","+
                        
                        vector[FIELD_DESCRIPTION]
            };
        }
                
        return result.toArray(new String[result.size()]);
    }

    private ArrayList<String[]> chargeOperations() {
        ArrayList<String[]> result = new ArrayList<>();
        
        result.add(new String[]{"1", "1", "2019-06-01", "100", "Cena al ristorante"});
        result.add(new String[]{"2", "2", "2019-06-02", "10", "Rifornimento Carburante Tamoil"});
        result.add(new String[]{"3", "3", "2019-05-30", "350", "Tributi"});
        result.add(new String[]{"4", "1", "2019-05-24", "200", "Compleanno al pub"});
        result.add(new String[]{"5", "3", "2019-05-30", "50", "Rifornimento Carburante Shell"});
        result.add(new String[]{"6", "2", "2019-05-23", "50", "Rifornimento Carburante Agip"});
        result.add(new String[]{"7", "4", "2019-06-04", "25", "Ricarica Vodafone"});
        result.add(new String[]{"8", "2", "2019-06-05", "75", "Cena al ristorante"});
        result.add(new String[]{"9", "1", "2019-06-06", "75", "Benzina Autostrada"});
        result.add(new String[]{"10", "4", "2019-06-02", "75", "Benzina Autostrada"});
        
         return result;
    }
    
}
