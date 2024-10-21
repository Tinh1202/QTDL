/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author vntin
 */
public class User_Account { // account login 
    private String username;
    private String password;
    
    /*
        create constructor
    */
    User_Account(){
        
    }
    
    // create getter, setter
    /*
        code *
    */
    
    
    // SHA-256/bcrypt
    public User_Account hashingAccount(String username, String password){ // hashing password and username
        // input -> hashing
        return null;
    }
    
    
    public boolean CheckAccount(User_Account account){
        // connected -> get Account from database(also hash) -> hash (hashingAccount) -> check
        return false;
    }
    
    
    public void Login(String username, String password){ // function check password and username
        /* if true do ... 
           if false do ... 
        */ 
    } 

    public void WriteLog(){ // write log while users login
        /*
            code *
        */
    }
}
