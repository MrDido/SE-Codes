/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jmsserverjune;

import java.util.Properties;
import java.util.Random;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author biar
 */
public class ProduttoreRanking {

    final String id[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

    private String scegliID() {
        int whichMsg;
        Random randomGen = new Random();

        whichMsg = randomGen.nextInt(this.id.length);
        return this.id[whichMsg];
    }
    
    private float ranking() {
		Random randomGen = new Random();
		float val = randomGen.nextFloat() * this.id.length * 10;
		return val;
	}
    
    private static final Logger LOG = LoggerFactory.getLogger(ProduttoreRanking.class);

    public void start() throws NamingException, JMSException {

        Context jndiContext = null;
        ConnectionFactory connectionFactory = null;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        String destinationName = "dynamicTopics/professors";

        /*
                * Create a JNDI API InitialContext object
         */
        try {

            Properties props = new Properties();

            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            props.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");
            jndiContext = new InitialContext(props);

        } catch (NamingException e) {
            LOG.info("ERROR in JNDI: " + e.toString());
            System.exit(1);
        }
        /*
                * Look up connection factory and destination.
         */
        try {
            connectionFactory = (ConnectionFactory) jndiContext.lookup("ConnectionFactory");
            destination = (Destination) jndiContext.lookup(destinationName);
        } catch (NamingException e) {
            LOG.info("JNDI API lookup failed: " + e);
            System.exit(1);
        }

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(destination);

            TextMessage message = null;
            String messageType = null;

            message = session.createTextMessage();

            
            //int i = 0;
            float rank;
            while (true) {
                //i++;
                messageType = scegliID();
                rank = ranking();
                message.setStringProperty("Nome", messageType);
                message.setFloatProperty("ranking", rank);
                message.setText(
                        "ID " + ": " + messageType + ", RANKING: "
                        + rank);

                LOG.info(
                        this.getClass().getName()
                        + "Invio ranking: " + message.getText());

                producer.send(message);

                try {
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (JMSException e) {
            LOG.info("Exception occurred: " + e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
        }

    }

}
