/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esamesettembreclient;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class Client {
    public static void main(String[] args) throws JMSException, NamingException, SQLException {
        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session =  null;
        Destination destination = null;
        String destinationName = "dynamicTopics/Flights";
        
        Properties props = new Properties();
        
        props.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL,"tcp://192.168.49.81:61616");
        
        jndiContext = new InitialContext(props);
        
        connectionFactory = (ConnectionFactory)jndiContext.lookup("ConnectionFactory");
        destination = (Destination)jndiContext.lookup(destinationName);
        
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        java.sql.Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/biar/flights-database");
        Statement stat = conn.createStatement();
        
        MessageConsumer consumer = session.createConsumer(destination);
        connection.start();
        String flight = null;
        Boolean status = false;
        while(true){
            Message m = consumer.receive();
            if(m!=null){
                if (m instanceof TextMessage){
                TextMessage message = (TextMessage) m;
                System.out.println("Reading message: " + message.getText());
                flight = m.getStringProperty("id");
                status = m.getBooleanProperty("status");
                PreparedStatement prep = conn.prepareStatement("insert into flight values (?, ?);");
                prep.setString(1, flight);
                prep.setBoolean(2, status);
                prep.addBatch();
                conn.setAutoCommit(false);
                prep.executeBatch();
                conn.setAutoCommit(true);
            }
            
           }else{
                break;
            }
        }
        
    }
    
}