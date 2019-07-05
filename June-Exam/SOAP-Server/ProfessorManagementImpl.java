/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapjune;

import java.util.ArrayList;
import javax.jws.WebService;

/**
 *
 * @author biar
 */
@WebService(endpointInterface = "com.mycompany.soapjune.ProfessorManagement")
public class ProfessorManagementImpl implements ProfessorManagement{
    
    
    @Override
    public Professor getDetails(String id) {
        ArrayList<Professor> data = data();
        for(Professor p : data){
            if(p.getId().equals(id))return p;
        }
        System.out.println("Non esiste professore con quell' ID");
        return null;
    }
    
    private ArrayList<Professor> data() {
        ArrayList<Professor> professors = new ArrayList<>();
        
            professors.add(new Professor("Massimo", "Mecella", "1"));
            professors.add(new Professor("Fabrizio", "D'Amore", "2"));
            professors.add(new Professor("Roberto", "Baldoni", "3"));
            professors.add(new Professor("Maurizio", "Lenzerini", "4"));
            professors.add(new Professor("Giuseppe", "De Giacomo", "5"));
            professors.add(new Professor("Marco", "Schaerf", "6"));
            professors.add(new Professor("Riccardo", "Rosati", "7"));
            professors.add(new Professor("Dido", "Il Migliore", "8"));
            
            
        return professors;
    }
    
}
