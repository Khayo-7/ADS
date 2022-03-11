/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.astu.k7m.Storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.ejb.Stateless;

/**
 *
 * @author Ever
 */
@Stateless
 
public class StudentDBManager {
    private static final String DB_name = "studentdb";
    private static final String host = "127.0.0.1";
    private static final String port = "3306";
    private static final String username = "root";
    private static final String password = "";
    
    private static Statement statement;
    PreparedStatement preStmt;

    public StudentDBManager() throws SQLException, ClassNotFoundException{              
       initialize();
    }    
    
    public static void initialize() throws ClassNotFoundException, SQLException{
        Connection connection = null;
         try{ //int res = 0;
            connection = getConnection();
            statement= connection.createStatement();
            int res = statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (\n" +
                    "  id VARCHAR(245) NOT NULL ,\n" +
                    "  firstName VARCHAR(245) NOT NULL,\n" +
                    "  lastName VARCHAR(245),\n" +
                    "  cgpa FLOAT,\n" +
                    "  UNIQUE INDEX id_UNIQUE (id ASC),\n" +
                    "  PRIMARY KEY (id))\n"
                    );
            if(res>0)
                System.out.println("Table created");
        else            
            System.out.println("Table already created");
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException{        
        
        Connection connection = null;
        String DBurl = "jdbc:mysql://" + host + ":" + port + "/" + DB_name; 
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + DB_name, username, password);
            System.out.println("connected with database");
        }
        catch (SQLException e) {
            System.out.println("unable to connect with database");
        }
        return connection;
        
        }  
    public static Statement getStatement() {
        return statement;
    }
    public static void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
}


