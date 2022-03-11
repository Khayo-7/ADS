/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.student;

/**
 *
 * @author Ever
 */
public class Student {
    
    private String firstName;
    private String lastName;
    private String id;
    private float cgpa;

    public Student() {        
        this.id = "-"; 
        this.firstName = "Unknown";
        this.lastName = "Unknown";
        this.cgpa = 0;         
    }

    public Student(String id, String firstName, String lastName, float cgpa) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cgpa = cgpa;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    @Override
    public String toString() {
        return "Student{" + "firstName=" + firstName + ", lastName=" + lastName + ", id=" + id + ", cgpa=" + cgpa + '}';
    }   
}
