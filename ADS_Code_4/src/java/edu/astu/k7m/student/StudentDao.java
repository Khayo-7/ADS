/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.student;

import edu.astu.k7m.Storage.StudentStorage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ever
 */
public class StudentDao implements StudentServiceInterface{ 
        
    private HashMap<String, Student> studentStr = (HashMap<String, Student>) StudentStorage.getStudentStorage();
    
    private Student stu0= new Student("0000","fasika", "abreham", 2);
    private Student stu1= new Student("1111","yemisrach", "embibel", 2);
    private Student stu2= new Student("2222","kemeriya", "major", 2); 
    private Student stu3= new Student("3333","kibru", "demeke", 2);
    private Student stu4= new Student("4444","leulseged", "tilahun", 2); 
   
    public StudentDao(){
        studentStr.put(stu0.getId(), stu0);
        studentStr.put(stu1.getId(), stu1);
        studentStr.put(stu2.getId(), stu2);
        studentStr.put(stu3.getId(), stu3);
        studentStr.put(stu4.getId(), stu4);
    }  
    
    @Override
    public boolean createStudent(Student stu){ 
        
        if(!studentStr.containsKey(stu.getId())){
            studentStr.put(stu.getId(), stu);
            return true;
        }         
        return false;
    }
    
    @Override
    public boolean updateStudent(String id, Student stu){
               
        if(studentStr.containsKey(id)){
            studentStr.remove(id);
            studentStr.put(stu.getId(), stu);
            return true;
        }         
        return false;
    }
    
    @Override
    public boolean deleteStudent(String id){
         
        Student stu = studentStr.get(id);          
        
        if(studentStr.containsKey(id)){
             studentStr.remove(id);
            return true;
        }         
        return false;
    }
          
    @Override
    public Student getStudent(String id){        
        if(studentStr.containsKey(id))
            return studentStr.get(id);
        else
            return null;
    }
    
    @Override
    public ArrayList<Student> getAllStudents(){
        return new ArrayList<> (studentStr.values());
    }
    
}