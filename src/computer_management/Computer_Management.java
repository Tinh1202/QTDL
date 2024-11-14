/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package computer_management;

import Model.DeviceModel.Country;
import View.Login_form;
/*import Model.Connect.Connection;*/

/**
 *
 * @author vntin
 */
public class Computer_Management {
    public static void main(String[] args){  
        
        java.awt.EventQueue.invokeLater(() -> {
            new Login_form().setVisible(true);
        });
    }       
}
