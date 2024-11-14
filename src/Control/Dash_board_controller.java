/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author vntin
 */
import Model.Delivery.Delivery_Note;
import Model.Delivery.Detail_DeliveryNote;
import Model.Delivery.ListDeliveryNote;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
public class Dash_board_controller {
    
    // return default Model
    public DefaultTableModel model_table(){
        String[] columns = {"Date", "ID device", "Name device", "Price"};
        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        return model;
    }
    
    public DefaultTableModel model_table_mysql(){
        String[] columns_detail_prn = {""};
        DefaultTableModel model_detail = new DefaultTableModel(columns_detail_prn, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
//        for (Delivery_Note detail_delivery : new ListDeliveryNote().ListDN_MySQL()){
//            Object row[] = {
//                detail_delivery.getId_Dn(), 
//                detail_delivery.get
//            }
//        }
//        
        
        return null;
    }
    
}
