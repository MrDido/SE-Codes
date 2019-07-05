/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restserverzip;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author biar
 */

@Path("risorse")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class Repository {
    
    //dati
    private List<Risorsa> data = new LinkedList<>();
    
    //costruttore //Creo database server
    {
        Risorsa r = new Risorsa();
        r.setId("uno");  
        r.setName("soldi");
        
        Risorsa r1 = new Risorsa();
        r1.setId("due");  
        r1.setName("fortuna");
        
        Risorsa r2 = new Risorsa();
        r2.setId("tre");  
        r2.setName("donne");

    }
    
    //trova risorsa
    @GET
    @Path("{rid}")
    public Risorsa getRisorsa(@PathParam("rid") String id){
        for(Risorsa r: this.data){
            if(r.getId().equals(id) ){
                System.out.print("return the result");
                 return r;
            }           
        }
        //se non trova risorsa la crea
        System.out.print("returning null.\nId passed:\t"+id);
        return new Risorsa();
    }
    
    //aggiunge risorsa; bisogna usare il metodo "response" della classe ws
    @POST
    @Path("")
    public Response addRisorsa(Risorsa r){
        for(Risorsa res: this.data){
            if(res.getId().equals( r.getId() ) ){ 
                return Response.status(Response.Status.CONFLICT).build(); 
            }          
        }
        this.data.add(r);
        return Response.ok().build();
    }
    
    //update di una risorsa; bisogna usare sempre "response"
    @PUT
    @Path("{rid}")
    public Response updateRisorsa(@PathParam("rid") String id, Risorsa newRes){
        for(Risorsa res: this.data){
            if(res.getId().equals(id)  ){
                if(res.equals(newRes) ){
                    return Response.status(Response.Status.NOT_MODIFIED).build(); 
                }
                res.setId(newRes.getId());
                res.setName(newRes.getName());
                return Response.ok().build(); 
            }          
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    //Cancella risorsa; usare sempre metodo response
    @DELETE
    @Path("{rid}")
    public Response deleteRisorsa(@PathParam("rid") String id){
        for(Risorsa res: this.data){
            if(res.getId().equals(id)  ){
                this.data.remove(res);
                return Response.ok().build(); 
            }          
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    
        
    
    
}
