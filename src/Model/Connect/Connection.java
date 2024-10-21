package Model.Connect;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private final String url = new String("jdbc:mysql://localhost:3306/qlmt");
    private final String username = new String("root");
    private final String password = new String("tvo229033@gmail");
    
    public java.sql.Connection getJDBC() throws SQLException{
        Connection conn = null;
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (NoClassDefFoundError e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}


