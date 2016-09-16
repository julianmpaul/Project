/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jeankie
 */
public class Database {
    
private static Connection conn;

    private static String url = "jdbc:mysql://localhost/database";
    private static String user = "root";
    private static String pass = "jerick";
    
    public static Connection connect() throws SQLException{

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException cnfe){
            
            System.err.println("Error: "+cnfe.getMessage());
            
        }

        conn = DriverManager.getConnection(url,user,pass);

        return conn;

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException{

        if(conn !=null && !conn.isClosed())

            return conn;

        connect();

        return conn;

}}
