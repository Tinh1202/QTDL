/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.UserModel.User_Account;
import View.Login_form;
import javax.swing.JButton;

/**
 *
 * @author vntin
 */
public class LoginControler {
    
    public boolean CheckLoginState(String username, String password){
        User_Account account = new User_Account().getAccount_MySQL(username, password);
        return (account != null) ? true : false;   
    }
}
