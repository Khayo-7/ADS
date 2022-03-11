/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.WebService.SOAPWebService;

import edu.astu.k7m.Storage.XMLResponse;
import edu.astu.k7m.student.Student;
import edu.astu.k7m.student.StudentDBServer;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ever
 */
@XmlRootElement
@WebService(serviceName = "StudentSOAPWebService")

public class StudentSOAPWebService {

    /**
     * This is a sample web service operation
     */
   
    private static StudentDBServer studentDBServer;
   
    public StudentSOAPWebService() throws ClassNotFoundException {
        
       studentDBServer = new StudentDBServer();
    }  
     
    @WebMethod(operationName = "createStudent")
    
    public XMLResponse createStu(Student stu){  
        
        XMLResponse response = new XMLResponse(501, "Server Error", null);
        
        try { 
            if(studentDBServer.createStudent(stu)){ 
                response.setMessage("Student Successfully CREATED");
                response.setStatus(200);
                response.setStudent(stu);
            }
        } 
        catch (SQLException e) {
            response.setMessage(e.getMessage());
            response.setStatus(e.getErrorCode());
        }

        return response;
    }

    @WebMethod(operationName = "updateStudent")
    
    public XMLResponse updateStu(@WebParam(name = "id") String id, Student stu) {
        
        XMLResponse response = new XMLResponse(404, "Student Not Found", null);
        
        try {            
            if(studentDBServer.updateStudent(id, stu)){ 
                response.setMessage("Student Successfully UPDATED");
                response.setStatus(200);
                response.setStudent(stu);
            }
                
        } 
        catch (SQLException e) {
            response.setMessage(e.getMessage());
            response.setStatus(e.getErrorCode());
        }        
        return response;
    }

    @WebMethod(operationName = "deleteStudent")
    
     public XMLResponse deleteStu(@WebParam(name = "id") String id) {
        
        XMLResponse response = new XMLResponse(404, "User to be deleted Doesn't Exist", null); 
        try {    
            Student student = studentDBServer.getStudent(id);                                    
            if(studentDBServer.deleteStudent(id)){ 
                response.setMessage("Student Successfully DELETED");
                response.setStatus(200);
                response.setStudent(student);
            }
        } 
        catch (SQLException e) {            
            response.setStatus(e.getErrorCode());
            response.setMessage(e.getMessage());
        }      
        
        return response;
    }
    
    @WebMethod(operationName = "deleteStudentQuery")
    
    public XMLResponse deleteStuQuery(@QueryParam("id")  String id) {
        
        XMLResponse response = new XMLResponse(404, "User to be deleted Doesn't Exist", null); 
        try {    
            Student student = studentDBServer.getStudent(id);                                    
            if(studentDBServer.deleteStudent(id)){ 
                response.setMessage("Student Successfully DELETED");
                response.setStatus(200);
                response.setStudent(student);
            }
        } 
        catch (SQLException e) {            
            response.setStatus(e.getErrorCode());
            response.setMessage(e.getMessage());
        }      
        
        return response;
    }

    @WebMethod(operationName = "getStudent")
    
    public XMLResponse getStu(@WebParam(name = "id") String id){
                
        XMLResponse response = new XMLResponse(404, "Student not FOUND", null);
        try{
            Student student = studentDBServer.getStudent(id);
            if(student != null){
                response.setMessage("Student is FOUND using Web Param");
                response.setStatus(200);
                response.setStudent(student);
            }    
        }
        catch (SQLException e) {
            response.setMessage(e.getMessage());
            response.setStatus(e.getErrorCode());
        }
        return response;  
    }  
    
    @WebMethod(operationName = "getStudentQuery")
    
    public XMLResponse getStuQuery(@QueryParam("id") String id) throws SQLException {
                 
        XMLResponse response = new XMLResponse(404, "Student not FOUND", null);
        try{
            Student student = studentDBServer.getStudent(id);
            if(student != null){
                response.setMessage("Student is FOUND using Web Param");
                response.setStatus(200);
                response.setStudent(student);
            }    
        }
        catch (SQLException e) {
            response.setMessage(e.getMessage());
            response.setStatus(e.getErrorCode());
        }
        return response;   
    }
       
    @WebMethod(operationName = "getAllStudents")
    
    public ArrayList<Student> getAllStus() {
        ArrayList<Student> stuList = new ArrayList<>();

        try {
            stuList = studentDBServer.getAllStudents();             
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return stuList;
        }
        return stuList;
    }
}
