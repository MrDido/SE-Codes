/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sejuneclient;

import com.mycompany.soapjune.Professor;
import com.mycompany.soapjune.ProfessorManagement;
import com.mycompany.soapjune.ProfessorManagementImplService;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author biar
 */
public class MainClient {

    public static void main(String[] args) throws NamingException, JMSException {
        ProfessorManagement port = new ProfessorManagementImplService().getProfessorManagementImplPort();

        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        String destinationName = "dynamicTopics/professors";

        Properties props = new Properties();

        props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

        jndiContext = new InitialContext(props);

        connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
        destination = (Destination) jndiContext.lookup(destinationName);

        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageConsumer consumer = session.createConsumer(destination);
        connection.start();
        while (true) {
            Message m = consumer.receive();
            if (m != null) {
                if (m instanceof TextMessage) {
                    TextMessage message = (TextMessage) m;
                    String id = message.getStringProperty("Nome");
                    //System.out.println("Reading message: " + message.getStringProperty("Nome"));
                    Professor p = port.getDetails(id);
                    float ranking = message.getFloatProperty("ranking");
                    System.out.println("Ricevuto id : " + id + " con ranking "+ ranking + " .... bravo " + p.getName() + " " + p.getSurname());
                }

            }
        }

    }
}
