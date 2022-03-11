/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstulate file, choose Tools | Tstulates
 * and open the tstulate in the editor.
 */
package edu.astu.k7m.WebService.RESTWebService;

import edu.astu.k7m.Storage.XMLResponse;
import edu.astu.k7m.student.Student;
import edu.astu.k7m.student.StudentDao;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * REST Web Service
 *
 * @author Ever
 */
@XmlRootElement
@Path("student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class StudentRESTWebService {
        
    private final StudentDao studentDao = new StudentDao();
    
    @Context
    private UriInfo context;
       
    @POST
    @Path("createStudent") 
     
    
    public XMLResponse createStu(Student stu){  
        
        XMLResponse response = new XMLResponse(404, "Duplicated ID entry: Student not CREATED", new Student());
        if(studentDao.createStudent(stu)){
            response.setMessage("Student Successfully CREATED");
            response.setStatus(200);
            response.setStudent(stu);
         }
        return response;
    }
    
    @PUT
    @Path("/updateStudent/{id}")      
        
    public XMLResponse updateStu(@PathParam("id") String id, Student stu){
       
        XMLResponse response = new XMLResponse(404, "Student not FOUND", new Student());
        
        if(studentDao.updateStudent(id, stu)){
            response.setMessage("Student Successfully UPDATED");
            response.setStatus(200);
            response.setStudent(stu);
         }
        
        return response;        
    }    
    
    @DELETE
    @Path("/deleteStudent/{id}")    
    
    public XMLResponse deleteStu(@PathParam("id") String id){
         
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(studentDao.deleteStudent(id)){
            response.setMessage("Student is DELETED using Path Param");
            response.setStatus(200);
         }
        return response;    
    } 
    
    @DELETE
    @Path("/deleteStudent")
    public XMLResponse deleteStuQuery(@QueryParam("id") String id){
            
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(studentDao.deleteStudent(id)){
            response.setMessage("Student is DELETED using Query Param");
            response.setStatus(200);
         }
        return response;
    }
    
     
    @GET
    @Path("/getStudent/{id}")

    public XMLResponse getStu(@PathParam("id")  String id){    
        
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(student != null){
            response.setMessage("Student is FOUND using Path Param");
            response.setStatus(200);
         }
        return response;        
    }  
    @GET
    @Path("/getStudent")

    public XMLResponse getStuQuery(@QueryParam("id")  String id){  
        
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(student != null){
            response.setMessage("Student is FOUND using Query Param");
            response.setStatus(200);
         }
        return response;      
    } 
    
    @GET
    @Path("getAllStudents")

    public ArrayList<Student> getAllStu(){
        return studentDao.getAllStudents();
    }
}