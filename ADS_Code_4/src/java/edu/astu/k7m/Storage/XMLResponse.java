/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.Storage;

import edu.astu.k7m.student.Student;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ever
 */
    

@XmlRootElement(name = "response")
public class XMLResponse {
    private int status;
    private String message;
    private Student student;

    public XMLResponse(int status, String message, Student student) {
        this.status = status;
        this.message = message;
        this.student = student;
    }

    public XMLResponse() {
        this.status = 0;
        this.message = "NO Message";
        this.student = new Student();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "XMLResponse{" + "status=" + status + ", message=" + message + ", student=" + student + '}';
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}

