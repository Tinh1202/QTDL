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
import Model.DeviceModel.Supplier;
import Model.DeviceModel.ListSupplier;
import Model.DeviceModel.Country;
import Model.Delivery.Product_Receipt;
import Model.Delivery.ListProduct_Receipt;
import Model.DeviceModel.Specification;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author admin
 */
public class AddSupplier extends javax.swing.JFrame {
    private Supplier Supplier;
    private ListSupplier ListSupplier;
    private ArrayList<Supplier> arrSupplier;
    private Country Country;
    private ArrayList<Country> arrCountry;
    private ListProduct_Receipt Pro;
    private ArrayList<Product_Receipt> arrPro;
    private boolean isEditing = false;
    private String editingId = null;
    private javax.swing.JButton jButtonDelete;
    /**
     * Creates new form AddSupplier
     */
    public AddSupplier() {
        initComponents();
        
        ListSupplier = new ListSupplier();
        this.arrSupplier = ListSupplier.getSuppliersFromDatabase();
        Country = new Country();
        this.arrCountry = Country.getCountry_MySQL();
        Pro = new ListProduct_Receipt();
        this.arrPro = Pro.ListPR_MySQL();
        jTextField1.setText(createNewId(arrSupplier.size()));
        jTextField1.setEditable(false);
        jTextField1.setFocusable(false);
        showSupplier();
        
    }

    private void showSupplier() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        
        if (arrSupplier != null && !arrSupplier.isEmpty()) {
            for(Supplier sup : arrSupplier) {
                Object[] row = new Object[]{
                    sup.getIDSupplier(),
                    sup.getNameSupplier(),
                    sup.getAddressSupplier(),
                    sup.getPhoneSupplier(),
                    sup.getCountry().getNameCountry()
                };
                model.addRow(row);
            }
        }
    }
   
    public String createNewId(int a){
        a = a+1;
        String numberString = Integer.toString(a);
        int length = numberString.length();
        String newIdSpe = "SUP";
        for(int i=0;i<3-length;i++){
            newIdSpe = newIdSpe.concat("0");
        }
        newIdSpe = newIdSpe.concat(numberString);
        return newIdSpe;
    }
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField4 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTextField4.setText("jTextField4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Supplier");

        jLabel2.setText("Address");

        jLabel3.setText("Phone");

        jLabel4.setText("Country");

        jLabel5.setText("Name");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID supplier", "Name", "Address", "Phone", "Country"
            }
        ));
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)
                            .addComponent(jTextField1)
                            .addComponent(jTextField5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(6, 6, 6)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        String idSupplier = jTextField1.getText();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            java.sql.Connection conn = c.getJDBC();
            
            String name_supplier = jTextField3.getText();
            String address_supplier = jTextField2.getText();
            String phone_supplier = jTextField5.getText();
            String id_country = jTextField6.getText();
            
            if (isEditing) {
                // Cập nhật supplier hiện có
                String sql = "UPDATE supplier SET name_supplier=?, address_supplier=?, phone_supplier=?, id_country=? WHERE id_supplier=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name_supplier);
                stmt.setString(2, address_supplier);
                stmt.setString(3, phone_supplier);
                stmt.setString(4, id_country);
                stmt.setString(5, editingId);
                stmt.executeUpdate();
                
                isEditing = false;
                editingId = null;
                jButton1.setText("Add");
            } else {
                // Thêm mới supplier
                String sql = "INSERT INTO supplier (id_supplier, name_supplier, address_supplier, phone_supplier, id_country) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                String id_supplier = createNewId(arrSupplier.size());
                stmt.setString(1, id_supplier);
                stmt.setString(2, name_supplier);
                stmt.setString(3, address_supplier);
                stmt.setString(4, phone_supplier);
                stmt.setString(5, id_country);
                stmt.executeUpdate();
                System.out.println("hhhhhhhhh");
            }
            
            // Refresh data
            ListSupplier = new ListSupplier();
            arrSupplier = ListSupplier.getSuppliersFromDatabase();
            showSupplier();
            
            // Clear form
            clearForm();
            
            JOptionPane.showMessageDialog(rootPane, "Thành công");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable2.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn supplier cần sửa!");
            return;
        }

        // Lấy dữ liệu từ bảng và điền vào form
        jTextField1.setText(jTable2.getValueAt(row, 0).toString());
        jTextField3.setText(jTable2.getValueAt(row, 1).toString());
        jTextField2.setText(jTable2.getValueAt(row, 2).toString());
        jTextField5.setText(jTable2.getValueAt(row, 3).toString());
        jTextField6.setText(getCountryIdByName(jTable2.getValueAt(row, 4).toString()));
        
        // Chuyển sang chế độ edit
        isEditing = true;
        editingId = jTable2.getValueAt(row, 0).toString();
        jButton1.setText("Update");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int row = jTable2.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn supplier cần xóa!");
            return;
        }

        String supplierId = jTable2.getValueAt(row, 0).toString();
        
        // Kiểm tra xem supplier có đang được sử dụng không
        if (isSupplierInUse(supplierId)) {
            JOptionPane.showMessageDialog(rootPane, 
                "Không thể xóa supplier này vì đang được sử dụng trong các đơn hàng!",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(rootPane,
                "Bạn có chắc chắn muốn xóa supplier này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Model.Connect.Connection c = new Model.Connect.Connection() {};
                java.sql.Connection conn = c.getJDBC();
                
                String sql = "DELETE FROM supplier WHERE id_supplier = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, supplierId);
                stmt.executeUpdate();
                
                // Refresh data
                ListSupplier = new ListSupplier();
                arrSupplier = ListSupplier.getSuppliersFromDatabase();
                showSupplier();
                
                // Clear form
                clearForm();
                
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");
                
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, 
                    "Lỗi khi xóa: " + ex.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isSupplierInUse(String supplierId) {
        try {
            Model.Connect.Connection c = new Model.Connect.Connection() {};
            java.sql.Connection conn = c.getJDBC();
            
            // Kiểm tra trong bảng product_receipt
            String sql = "SELECT COUNT(*) FROM product_receipt WHERE id_supplier = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, supplierId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

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
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSupplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSupplier().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {
        int row = jTable2.getSelectedRow();
        if (row >= 0) {
            jTextField1.setText(jTable2.getValueAt(row, 0).toString());
            jTextField3.setText(jTable2.getValueAt(row, 1).toString());
            jTextField2.setText(jTable2.getValueAt(row, 2).toString());
            jTextField5.setText(jTable2.getValueAt(row, 3).toString());
            jTextField6.setText(getCountryIdByName(jTable2.getValueAt(row, 4).toString()));
            
            isEditing = true;
            editingId = jTable2.getValueAt(row, 0).toString();
            jButton1.setText("Update");
        }
    }

    private String getCountryIdByName(String countryName) {
        for (Country country : arrCountry) {
            if (country.getNameCountry().equals(countryName)) {
                return country.getIdCountry();
            }
        }
        return "";
    }

    private void clearForm() {
        jTextField1.setText(createNewId(arrSupplier.size()));
        jTextField3.setText("");
        jTextField2.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
