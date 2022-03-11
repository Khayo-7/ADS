/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.student;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ever
 */
public interface StudentServiceInterface {
    
    public boolean createStudent(Student e) throws SQLException;
    
    public boolean updateStudent(String id, Student e) throws SQLException;

    public boolean deleteStudent(String id) throws SQLException;

    public Student getStudent(String id) throws SQLException;

    public ArrayList<Student> getAllStudents() throws SQLException;
}
