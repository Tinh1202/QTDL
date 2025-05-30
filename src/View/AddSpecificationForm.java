/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import Model.Connect.Connection;
import Model.DeviceModel.Device;
import Model.DeviceModel.ListDevice;
import Model.DeviceModel.Manufacturer;
import Model.DeviceModel.ListManuf;
import Model.DeviceModel.Specification;
import Model.DeviceModel.ListSpecification;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author admin
 */
public class AddSpecificationForm extends javax.swing.JFrame {

    /**
     * Creates new form AddSpecificationForm
     */
    private Device device;
    private ListDevice listdevice;
    private ArrayList<Device> arrDevice;
    private ListSpecification listSpe;
    private ArrayList<Specification> arrSpe;
    private ListManuf listManuf;
    private ArrayList<Manufacturer> arrManuf;
    private boolean isEditing = false;
    private String editingId = null;
    public AddSpecificationForm() {
        initComponents();
        ListSpecification ls = new ListSpecification();
        this.arrSpe = new ArrayList<Specification>(ls.ListSpec_MySQL());
        ListDevice ld = new ListDevice();
        this.arrDevice = new ArrayList<Device>(ld.getDevicesFromDatabase());
        ListManuf lm= new ListManuf();
        this.arrManuf = new ArrayList<Manufacturer>(lm.getManufacturersFromDatabase());
        
        for(Specification s: this.arrSpe){
            jComboBox1.addItem(s.getNameSpec());
        }
        for(Manufacturer m: this.arrManuf){
            jComboBox2.addItem(m.getNameManuf());
        }
        
        
        
    }
    
    public String getIdManuf(String name){
        String id = new String("");
        for(Manufacturer m: this.arrManuf){
            if(m.getNameManuf().equals(name)){
                id = m.getIdManuf();
                break;
            }
        }
        return id;
    }
    
    public String createNewId(int a){
        a = a+1;
        String numberString = Integer.toString(a);
        int length = numberString.length();
        String newIdSpe = "SPEC";
        for(int i=0;i<3-length;i++){
            newIdSpe = newIdSpe.concat("0");
        }
        newIdSpe = newIdSpe.concat(numberString);
        return newIdSpe;
    }
    public boolean checkID_Device(String ID){
        for(Device d : arrDevice){
            if(d.getIdDevice().equals(ID)){
                return true;
            }
        }return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Device");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose specification" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Name Specification");

        jLabel3.setText("Name Manufacturer");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose manufacturer" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(457, 457, 457)
                        .addComponent(jButton1)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(jTextField1.getText().trim().isEmpty() || jTextArea1.getText().trim().isEmpty() && jTextField2.getText().trim().isEmpty()){
           JOptionPane.showMessageDialog(null, "Enter full information", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!checkID_Device(jTextField1.getText().trim()) ){
            JOptionPane.showMessageDialog(null, "Id does not exist", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {Model.Connect.Connection c = new Model.Connect.Connection() {};
                java.sql.Connection conn = c.getJDBC();
                String sql = "INSERT INTO specification (id_specification, name_specification, data_spec, id_manufacturer) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                String id_specification = createNewId(arrSpe.size());
                String name_specification = jTextField2.getText();
                String data_spec = jTextArea1.getText();
                String id_manufacturer = getIdManuf(jTextField3.getText());
            
                stmt.setString(1, id_specification); stmt.setString(2, name_specification); stmt.setString(3, data_spec);  stmt.setString(4, id_manufacturer);
                stmt.executeUpdate();
                
                String sql1 = "INSERT INTO device_specification (id_device ,id_specification) VALUES (?, ?)";
                PreparedStatement stmt1 = conn.prepareStatement(sql1);
                String id_device = jTextField1.getText().trim();
                stmt1.setString(1, id_device); stmt1.setString(2, id_specification);
                stmt1.executeUpdate();
                
                stmt.close(); stmt1.close(); conn.close();
                jTextField1.setText(""); jTextField2.setText(""); jTextField3.setText(""); jTextArea1.setText("");    
                JOptionPane.showMessageDialog(rootPane, "Thanh cong");
                ListSpecification ls = new ListSpecification();
                this.arrSpe = new ArrayList<Specification>(ls.ListSpec_MySQL());
//                System.exit(0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String name = jComboBox1.getSelectedItem().toString();
        if(!name.equals("Choose specification")){
            jTextField2.setText(name);
            
        }
            
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String name = jComboBox2.getSelectedItem().toString();
        if(!name.equals("Choose manufacturer")){
            jTextField3.setText(name);
            
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

//    private void populateComboBox() {
//        // Xóa tất cả các item hiện có trong JComboBox
//        jComboBox1.removeAllItems();
//        
//        // Thêm các item mới vào JComboBox
//        for (Device device : arrDevice) {
//            jComboBox1.addItem(device.getName()); // Giả sử Device có phương thức getName()
//        }
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddSpecificationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSpecificationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSpecificationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSpecificationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSpecificationForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
