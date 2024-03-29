/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restserverzip;

import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author biar
 */

@XmlRootElement(name="risorsa")
public class Risorsa {
    
    //dati risorsa
    private String id;
    private String name;
    
    //Costruttore
    public Risorsa(){
        this.id = "";
        this.name = "";
    }
    
    //getters

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //setters
    @XmlElement(name="id")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="name")
    public void setName(String name) {
        this.name = name;
    }
    
    //override metodi base

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Risorsa other = (Risorsa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Risorsa{" + "id=" + id + ", name=" + name + '}';
    }
    
}
