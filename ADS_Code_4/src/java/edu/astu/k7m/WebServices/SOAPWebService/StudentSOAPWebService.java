/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.WebServices.SOAPWebService;

import edu.astu.k7m.Storage.XMLResponse;
import edu.astu.k7m.student.StudentDao;
import edu.astu.k7m.student.Student;
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
@WebService(serviceName = "StudentSOAPWebService1")

public class StudentSOAPWebService {

    /**
     * This is a sample web service operation
     */
    
    private StudentDao studentDao = new StudentDao();
        
    @WebMethod(operationName = "createStudent")    

    public XMLResponse createStu(Student stu){  
        
        XMLResponse response = new XMLResponse(404, "Duplicated ID entry: Student not CREATED", new Student());
        if(studentDao.createStudent(stu)){
            response.setMessage("Student Successfully CREATED");
            response.setStatus(200);
            response.setStudent(stu);
         }
        return response;
    }    
    
    @WebMethod(operationName = "updateStudent")
        
    public XMLResponse updateStu(@WebParam(name = "id") String id, Student stu){
       
        XMLResponse response = new XMLResponse(404, "Student not FOUND", new Student());
        
        if(studentDao.updateStudent(id, stu)){
            response.setMessage("Student Successfully UPDATED");
            response.setStatus(200);
            response.setStudent(stu);
         }
        return response;  
      
    }
    
    @WebMethod(operationName = "deleteStudent")
    
    public XMLResponse deleteStu(@WebParam(name = "id") String id){
         
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(studentDao.deleteStudent(id)){
            response.setMessage("Student is DELETED using Web Param");
            response.setStatus(200);
         }
        return response;    
    }
    
    @WebMethod(operationName = "deleteStudentQuery")
    
    public XMLResponse deleteStuQuery(@QueryParam("id") String id){
            
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(studentDao.deleteStudent(id)){
            response.setMessage("Student is DELETED using Query Param");
            response.setStatus(200);
         }
        return response;
    }
    
            
    @WebMethod(operationName = "getStudent")

    public XMLResponse getStu(@WebParam(name = "id") String id){    
        
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(student != null){
            response.setMessage("Student is FOUND using Web Param");
            response.setStatus(200);
         }
        return response;        
    }       
    
    @WebMethod(operationName = "getStudentQuery")

    public XMLResponse getStuQuery(@QueryParam("id") String id){   
        
        Student student = studentDao.getStudent(id);
        XMLResponse response = new XMLResponse(404, "Student not FOUND", student);
        
        if(student != null){
            response.setMessage("Student is FOUND using Query Param");
            response.setStatus(200);
         }
        return response;      
    }    
  
    @WebMethod(operationName = "getAllStudents") 
    
    public ArrayList<Student> getAllStu(){
        
        return studentDao.getAllStudents();
    }
}