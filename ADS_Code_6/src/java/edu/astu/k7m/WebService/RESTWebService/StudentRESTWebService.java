/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.WebService.RESTWebService;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import edu.astu.k7m.Storage.XMLResponse;
import edu.astu.k7m.student.StudentDBServer;
import edu.astu.k7m.student.Student;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ever
 */

@XmlRootElement
@Path("student")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})

public class StudentRESTWebService {
        private static StudentDBServer studentDBServer;
    /**
     * This is a sample web service operation
     */
    public StudentRESTWebService() throws ClassNotFoundException {
       studentDBServer = new StudentDBServer();
    }  
     
    @POST
    @Path("/createStudent")
    
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

    @PUT
    @Path("/updateStudent/{id}")
    
    public XMLResponse updateStu(@PathParam("id") String id, Student stu) {
        
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
    
    @DELETE
    @Path("/deleteStudent/{id}")
    
    public XMLResponse deleteStu(@PathParam("id") String id) {
       
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
     
    @DELETE
    @Path("/deleteStudent/")
    
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

    @GET
    @Path("/getStudent/{id}")
       
    
    
     public XMLResponse getStu(@PathParam("id") String id){
                
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
    
    
    @GET
    @Path("/getStudent")
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
    @GET
    @Path("/getAllStudents")
    
    public ArrayList<Student> getAllStus() {
        System.out.print("1");
        ArrayList<Student> stuList = new ArrayList<>();
        try {System.out.print("2");
            stuList = studentDBServer.getAllStudents();             
        } 
        catch (SQLException e) {
            e.printStackTrace();
            return stuList;
        }
        return stuList;
    }
}
