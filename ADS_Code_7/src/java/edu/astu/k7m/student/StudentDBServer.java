/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.student;

import edu.astu.k7m.Storage.StudentDBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ever
 */
public class StudentDBServer  implements StudentServiceInterface{
            
    private Statement statement;
    private PreparedStatement preStmt;
    private Connection connection;
    
    public StudentDBServer() throws ClassNotFoundException{
        try {
            connection = StudentDBManager.getConnection();
            this.statement = connection.createStatement();
        }
        catch (SQLException ex) {
            Logger.getLogger(StudentDBServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(ClassNotFoundException ex){
        
        }
    } 
    
    @Override
    public boolean createStudent(Student stu) throws SQLException{ 
        
        preStmt = connection.prepareStatement("insert into students (id, firstName, lastName, cgpa) values (?,?,?,?) ");

        preStmt.setString(1, stu.getId());
        preStmt.setString(2, stu.getFirstName());
        preStmt.setString(3, stu.getLastName());
        preStmt.setFloat(4, (float)stu.getCgpa());

        int res = preStmt.executeUpdate();
        System.out.println(res);

        if (res > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(String id, Student stu) throws SQLException {
       
        preStmt = connection.prepareStatement("update students set id=?,firstName=?, lastName=?, cgpa=? where id=?");            
        preStmt.setString(1, stu.getId());
        preStmt.setString(2, stu.getFirstName());
        preStmt.setString(3, stu.getLastName());
        preStmt.setFloat(4, (float)stu.getCgpa());
        preStmt.setString(5, id);

        int res = preStmt.executeUpdate();

        if (res > 0) {
            return true;
        }
        return false;      
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException {
        
        int res = statement.executeUpdate("delete from studentS where id = " + String.valueOf(id));
            
        if (res > 0) 
            return true;
        
        return false;
    }
    
    @Override
    public Student getStudent(String id) throws SQLException {
        Student stu = new Student();

        ResultSet resultSet = statement.executeQuery("select id, firstName,lastName, cgpa from students where id=" + id);
        if (resultSet.next()) 
            stu = new Student(resultSet.getString("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getFloat("cgpa"));
        
        return stu;        
    }

    @Override
    public ArrayList<Student> getAllStudents() throws SQLException {
        
        ArrayList<Student> stuList = new ArrayList<>();
        Student stu;
       
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, firstName, lastName, cgpa from students");

        while (resultSet.next()) {
            stu = new Student(resultSet.getString("id"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getFloat("cgpa"));
            stuList.add(stu);
        }       

        return stuList;
    }

 }
  
   