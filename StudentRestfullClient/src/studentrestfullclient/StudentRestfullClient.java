/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this tstulate file, choose Tools | Tstulates
 * and open the tstulate in the editor.
 */
package studentrestfullclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.JsonReader;
/**
 *
 * @author Ever
 */
public class StudentRestfullClient {

    /**
     * @param args the command line arguments
     */
   
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        String path = "C:\\Users\\Ever\\Documents\\file.csv";
        String student = "", delimiter = ",";
        StudentRestfullClient client = new StudentRestfullClient();
        List<String> results = new ArrayList<String>();
        
        String students = client.displayAllStudents();
        
        JSONObject obj = new JSONObject("{students:" + students + "}");
        JSONArray Students = obj.getJSONArray("students");
        
        if(!Students.isEmpty()){
            for (int i = 0; i < Students.length(); i++)
            {
                JSONObject stu = Students.getJSONObject(i);
                student = stu.getString("id") + "," + stu.getString("firstName") + "," + stu.getString("lastName") + "," + stu.getDouble("cgpa");
                System.out.println("student = " + student);
                client.write_to_CSV(student,path);
            }
        }
//        {
//        "cgpa": 2,
//        "firstName": "yemisrach",
//        "id": "1111",
//        "lastName": "embibel"
//    }
        client.createStudent("6543", "akalewold", "yifru", 1);
        client.displayAllStudents();
        client.updateStudent("88888", "efo", "nigussie", 2);
        client.deleteStudent("88888");
        client.displayAllStudents();
        client.displayAllStudents();
        client.createStudent("88888", "efo", "nigussie", 3);
        client.displayAllStudents();
        client.updateStudent("88888", "efo", "nigussie", 4);
        client.displayAllStudents();
        client.deleteStudent("88888");
        client.displayAllStudents();
        client.deleteStudent("88888");
        client.displayAllStudents();
        client.deleteStudentQuery("99999");
        client.displayAllStudents();
        client.deleteStudentQuery("99999");
        client.displayAllStudents();//    
        
        results = client.read_from_CSV(path);//
        
        for(String result: results){
            String[] data = result.split(delimiter);
            client.createStudent(data[0], data[1], data[2], Float.parseFloat(data[3]));
            System.out.println("\n id = " + data[0] + "\n firstName = " + data[1] + "\n lastName = " + data[2] + "\n cgpa = "+ Double.parseDouble(data[3]) + "\n");
        }
        client.displayAllStudents();
    }
    
    private static void write_to_CSV(String json, String filePath) throws FileNotFoundException, IOException{
         
        try{
              File file =new File(filePath);
              if(!file.exists()){
                    file.createNewFile();
              }
               PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
                writer.println(json);  
                writer.flush();
                writer.close();
            
        }
        catch(FileNotFoundException f){
           
            System.out.println("File not found");
        }
        catch(IOException IO){
           
            System.out.println("Error in IO");
        }
    }
    private static List<String> read_from_CSV(String filePath) throws FileNotFoundException, IOException{
         
        String line = "";
        List<String> results = new ArrayList<String>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            
            if((line = reader.readLine())!= null){
                String[] data = line.split(",");                
                
//                if((data[0] != "id") || (data[1] != "name") || (data[2] != "cgpa")){
//                    throw new IOException();
//                }
            }
            while((line = reader.readLine()) != null){
                results.add(line);
            }
            
//            Scanner scanner = new Scanner(new File(filePath));        
//            while(scanner.hasNextLine())
//                line = scanner.nextLine().toString();
            reader.close();
            return results;
        }
        catch(FileNotFoundException f){
           
            System.out.println("File not found");
            return null;
        }
        catch(IOException IO){
           
            System.out.println("Error in IO");
            return null;
        }
    }
    public String displayAllStudents()throws MalformedURLException, IOException{
        
        // Web service read/Get Operation
        try{            
            URL url= new URL("http://localhost:8080/ADS_Code_6/students/student/getAllStudents");
            HttpURLConnection conn= (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br= new BufferedReader( new InputStreamReader(conn.getInputStream()));
            String  stu = "", result = "";
            while((stu = br.readLine())!=null){
                result = stu;
                System.out.println(stu);
            }
            conn.disconnect();
            return result;
            }
        catch(IOException e){
            
                System.out.println("Connection failed");
                return null;
        }
    }
    public void createStudent(String id, String firstName, String lastName, float cgpa)throws MalformedURLException, IOException{

        try{   
           URL url = new URL("http://localhost:8080/ADS_Code_6/students/student/createStudent");
           HttpURLConnection con= (HttpURLConnection)url.openConnection();
           con.setConnectTimeout(5000);
           con.setDoOutput(true);
//           con.setDoInput(true);
           con.setRequestMethod("POST");
           con.setRequestProperty("Content-Type", "application/json");
           String indata=     "{"
                            + "     \"id\":\"" + id + "\","  
                            + "     \"firstName\":\""  + firstName + "\","  
                            + "     \"lastName\":\""  + lastName + "\","  
                            + "     \"cgpa\":" + cgpa + 
                              "}";

           OutputStream os= con.getOutputStream();
           os.write(indata.getBytes("UTF-8"));
           os.flush();
           os.close();
//            InputStream in = new BufferedInputStream(con.getInputStream());
           BufferedReader br= new BufferedReader( new InputStreamReader(con.getInputStream()));
           String  t1;
           while((t1 = br.readLine())!=null){
               System.out.println( t1);
           }
           con.disconnect();
       }
       catch(IOException e){

               System.out.println("Connection failed");
       }
    }
    
    public void updateStudent(String id, String firstName, String lastName, float cgpa)throws MalformedURLException, IOException{
        
        try{   
           URL url = new URL("http://localhost:8080/ADS_Code_6/students/student/updateStudent/" + id);
           HttpURLConnection con= (HttpURLConnection)url.openConnection();
           con.setDoOutput(true);
           con.setRequestMethod("PUT");
           con.setRequestProperty("Content-Type", "application/json");
           String indata=     "{"
                            + "     \"id\":\"" + id + "\","  
                            + "     \"firstName\":\""  + firstName + "\","  
                            + "     \"lastName\":\""  + lastName + "\","  
                            + "     \"cgpa\":" + cgpa + 
                              "}";

           OutputStream os= con.getOutputStream();
           os.write(indata.getBytes());
           os.flush();
           os.close();
           BufferedReader br= new BufferedReader( new InputStreamReader(con.getInputStream()));
           String  t1;
           while((t1 = br.readLine())!=null){
               System.out.println( t1);
           }
           con.disconnect();
       }
       catch(IOException e){

               System.out.println("Connection failed");
       }
    }
    
    public void deleteStudent(String stuid)throws MalformedURLException, IOException{
        
        try{   
           URL url = new URL("http://localhost:8080/ADS_Code_6/students/student/deleteStudent/" + stuid);
           HttpURLConnection con= (HttpURLConnection)url.openConnection();
           con.setRequestMethod("DELETE");
           con.setRequestProperty("Content-Type", "application/json");
           BufferedReader br= new BufferedReader( new InputStreamReader(con.getInputStream()));
           String  t1;
           while((t1 = br.readLine())!=null){
               System.out.println( t1);
           }
           con.disconnect();
       }
       catch(IOException e){

               System.out.println("Connection failed");
       }
    }
    
    public void deleteStudentQuery(String stuid)throws MalformedURLException, IOException{
        
        try{   
           URL url = new URL("http://localhost:8080/ADS_Code_6/students/student/deleteStudent?stuid=" + stuid);
           HttpURLConnection con= (HttpURLConnection)url.openConnection();
           con.setRequestMethod("DELETE");
           con.setRequestProperty("Content-Type", "application/json");
           BufferedReader br= new BufferedReader( new InputStreamReader(con.getInputStream()));
           String  t1;
           while((t1 = br.readLine())!=null){
               System.out.println( t1);
           }
           con.disconnect();
       }
       catch(IOException e){

               System.out.println("Connection failed");
       }
    }
}