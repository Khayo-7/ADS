/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.Storage;

import edu.astu.k7m.student.Student;
import java.util.HashMap;

/**
 *
 * @author Ever
 */
public class StudentStorage {
      private static HashMap<String, Student> studentStorage = new HashMap<String, Student>();
  
    public static HashMap<String, Student> getStudentStorage(){
        return studentStorage;
    }
}
