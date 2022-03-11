/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.student;

import java.util.ArrayList;

/**
 *
 * @author Ever
 */
public interface StudentServiceInterface {
    
    public boolean createStudent(Student e);
        
    public boolean updateStudent(String id, Student e);

    public boolean deleteStudent(String id);

    public Student getStudent(String id);

    public ArrayList<Student> getAllStudents();
}
