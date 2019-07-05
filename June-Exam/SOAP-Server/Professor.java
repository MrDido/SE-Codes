/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.soapjune;

/**
 *
 * @author biar
 */
public class Professor {
    String name;
    String surname;
    final String id;

    public Professor(String name, String surname, String id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    
    @Override
    public String toString() {
        return "Professor{" + "name=" + name + ", surname=" + surname + ", id=" + id + '}';
    }
    
    
    
}
