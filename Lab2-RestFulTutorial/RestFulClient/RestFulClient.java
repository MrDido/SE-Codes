/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.restfulclienttutorial;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.bind.JAXB;
import org.apache.cxf.helpers.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author biar
 */
public class RestFulClient {

    static final String BASE_URL = "http://localhost:8080/dido/courses/";

    public static void createClient(CloseableHttpClient client) {
        client = HttpClients.createDefault();
    }

    public static void closeClient(CloseableHttpClient client) throws IOException {
        client.close();
    }

    //GET REQUEST
    private static Course getCourse(int courseOrder) throws IOException {
        URL url = new URL(BASE_URL + courseOrder);
        InputStream input = url.openStream();
        Course course = JAXB.unmarshal(new InputStreamReader(input), Course.class);
        return course;
    }

    //GET REQUEST
    private static Student getStudent(int courseOrder, int studentOrder)
            throws IOException {
        URL url = new URL(BASE_URL + courseOrder + "/students/" + studentOrder);
        InputStream input = url.openStream();
        Student student
                = JAXB.unmarshal(new InputStreamReader(input), Student.class);
        return student;
    }

    public static void main(String[] args) throws IOException {

        CloseableHttpClient client = HttpClients.createDefault();;
        HttpResponse response = null;
        System.out.println("LA SITUAZIONE INIZIALE PREVEDE DUE CORSI: 1-REST with Spring e 2-Learn Spring Security...solo il corso 1 ha due studenti con id-1 e id-2");

        System.out.println("\n*********** TEST PUT 1 ***********");
        System.out.println("Let’s start with an invalid update request, where the Course object being updated does not exist. Here is content of the instance used to replace a non-existent Course object in the web service resource:");
        HttpPut httpPut = new HttpPut(BASE_URL + "3");
        InputStream resourceStream = RestFulClient.class.getResourceAsStream("non_existent_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");
        response = client.execute(httpPut);
        System.out.println(response.toString());
        System.out.println("Since we intentionally sent an invalid request to update a non-existent object, a Not Found (404) response is expected to be received. The response is validated.");
        
        System.out.println("\n*********** TEST PUT 2 ***********");
        System.out.println("In the second test case for PUT requests, we submit a Course object with the same field values. Since nothing is changed in this case, we expect that a response with Not Modified (304) status is returned. ");
        httpPut = new HttpPut(BASE_URL + "1");
        resourceStream = RestFulClient.class.getResourceAsStream("unchanged_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");
        response = client.execute(httpPut);
        System.out.println(response.toString());

        System.out.println("\n*********** TEST PUT 3 ***********");
        System.out.println("In the last demonstration of PUT requests, we execute a valid update. The following is content of the changed_course.xml file whose content is used to update a Course instance in the web service resource");
        httpPut = new HttpPut(BASE_URL + "2");
        resourceStream = RestFulClient.class.getResourceAsStream("changed_course.xml");
        httpPut.setEntity(new InputStreamEntity(resourceStream));
        httpPut.setHeader("Content-Type", "text/xml");
        response = client.execute(httpPut);
        System.out.println(response.toString());

        System.out.println("\nLet’s verify the new states of the web service resource: WE CALL GETCOURSE(2) AND MUST BE ID = 2 AND NAME: Apache CXF Support for RESTful");
        Course course = getCourse(2);
        System.out.println(course.getId());
        System.out.println(course.getName());

        System.out.println("\n*********** TEST POST 1 ***********");
        System.out.println("In the first test, we use a Student object unmarshaled from the conflict_student.xml file.  Since the uploaded Student object is already existent in the first Course instance, we expect that the creation fails and a response with Conflict (409) status is returned.");
        HttpPost httpPost = new HttpPost(BASE_URL + "1/students");
        resourceStream = RestFulClient.class.getResourceAsStream("conflict_student.xml");
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        httpPost.setHeader("Content-Type", "text/xml");
        response = client.execute(httpPost);
        System.out.println(response.toString());

        System.out.println("\n*********** TEST POST 2 ***********");
        System.out.println("We extract the body of an HTTP request from a file named created_student.xml. We add a new student to course 2");
        httpPost = new HttpPost(BASE_URL + "2/students");
        resourceStream = RestFulClient.class.getResourceAsStream("created_student.xml");
        httpPost.setEntity(new InputStreamEntity(resourceStream));
        httpPost.setHeader("Content-Type", "text/xml");
        response = client.execute(httpPost);
        System.out.println(response.toString());

        System.out.println("\n*********** TEST GET 1 ***********");
        HttpGet httpGet = new HttpGet(BASE_URL + "1");
        response = client.execute(httpGet);
        Course c = JAXB.unmarshal(response.getEntity().getContent(), Course.class);
        System.out.println(c.toString());
        System.out.println("POTEVO PURE CHIAMARE LA FUNZIONE getCourse definita all'inizio..");
        System.out.println("Per i GET su Students valgono gli stessi principi");
        
        
        System.out.println("\n*********** TEST DELETE 1 ***********");
        System.out.println("First, let’s try to delete a non-existent Student instance. The operation should fail and a corresponding response with Not Found (404) status is expected");
        HttpDelete httpDelete = new HttpDelete(BASE_URL + "1/students/3");
        response = client.execute(httpDelete);
        System.out.println(response.toString());
        
        System.out.println("\n*********** TEST DELETE 2 ***********");
        System.out.println("In the second test case for DELETE requests, we create, execute and verify a request. We expect response with 200 because there exists a student 1 in course 1");
        httpDelete = new HttpDelete(BASE_URL + "1/students/1");
        response = client.execute(httpDelete);
        System.out.println(response.toString());
        System.out.println("Let's verify:");
        Course course1 = getCourse(1);
        
        System.out.println("Size of course must be 1-----" + "Result: " + course1.getStudents().size());
        System.out.println("L'id del primo studente ora dovrebbe essere 2----" + "Result: " + course1.getStudents().get(0).getId());
        System.out.println("Il nome del primo studente dovrebbe essere Student B---- " + "Result:" + course1.getStudents().get(0).getName());
       
        
        
    }

}
