package View.PR_GUI;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import Model.Delivery.Detail_PRN;
import Model.Delivery.ListDetailPRN;
import Model.Delivery.ListProduct_Receipt;
import Model.Delivery.Product_Receipt;
import Model.DeviceModel.Device;
import Model.DeviceModel.Device_Type;
import Model.DeviceModel.ListDevice;
import Model.DeviceModel.ListSpecification;
import Model.DeviceModel.ListSupplier;
import Model.DeviceModel.Specification;
import Model.DeviceModel.Supplier;
import Model.UserModel.Staff;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vntin
 */
public class Product_Receipt_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Product_Receipt_GUI
     */
    public Product_Receipt_GUI() {
        initComponents();
        setResizable(false);
        // in các phiếu nhập Product Receipt
        String[] columnNames = {"ID Receipt Note", "Date import", "Supplier", "Total cost"};
        
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
        ArrayList<Product_Receipt> lst_pr = new ArrayList<Product_Receipt>();
        lst_pr = new ListProduct_Receipt().ListPR_MySQL(); // lấy ds phiếu nhập từ mysql
        
        
        for (Product_Receipt pr : lst_pr){
            String id_pr = new String(pr.getId_PRN());
            LocalDateTime date_import = pr.getDateImport();
            Supplier sp = pr.get_supplier();
            Staff staff = pr.get_staff();
            ListDetailPRN lst_prn = new ListDetailPRN(pr.getListDetailPRN());
            double total_cost = 0.0;
            
            for (Detail_PRN d : lst_prn.getListDPRN()){
                Device device = new ListDevice().getDevice_MySQL(d.getId_device());
                total_cost += device.getPrice() * d.getNumber();
            }
            
            Object[] row = {
                id_pr, 
                date_import,
                sp.getNameSupplier(),
                new DecimalFormat("#.00").format(total_cost)
            };
            
            model.addRow(row);
        }
        
        T_Product_Receipt.setModel(model);
        
        
        
        // default table detail
        String[] columnNames_detail = {"ID Receipt Note", "ID Device", "Name Device", "Quantity", "Total Devices"};
        
        DefaultTableModel model_detail = new DefaultTableModel(columnNames_detail, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tất cả các ô không thể chỉnh sửa
            }
        };
        
        ArrayList<Detail_PRN> lst_dprn = new ArrayList<Detail_PRN>();
        lst_dprn = new ListDetailPRN().ListDPRN_MySQL();
        
        for (Detail_PRN d : lst_dprn){
            String id_dprn = new String(d.getId_prn());
            String id_device = new String(d.getId_device());
            
            Device device = new ListDevice().getDevice_MySQL(id_device);
            int number = d.getNumber();
            
            double total_price_device = number * device.getPrice();
            
            Object[] row = {
                id_dprn, 
                id_device, 
                device.getNameDevice(),
                number, 
                new DecimalFormat("#.00").format(total_price_device)
            };
            
            model_detail.addRow(row);
        }
        
        T_Detail.setModel(model_detail);
        
        
        
        
        T_Product_Receipt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = T_Product_Receipt.getSelectedRow(); // Lấy hàng được chọn
                
                // Lấy dữ liệu từ hàng được chọn
                
                  String id_prn = new String(T_Product_Receipt.getValueAt(selectedRow, 0).toString());
                  
                  Product_Receipt pr = new Product_Receipt().getPR_MySQL(id_prn);
                  Staff st = pr.get_staff();
                  jTextArea2.setText("=======Staff=======\n"
                          + "Staff id: " + st.getId() + "\n"
                          + "Staff name: " + st.getFullname() + "\n"
                          + "Position: " + st.getPosition() + "\n"
                          + "Phone staff: " + st.getPhone_number() + "\n");
                  
                  ArrayList<Detail_PRN> lst_detail_prn = new ArrayList<Detail_PRN>(new ListDetailPRN().ListDPRN_MySQL(id_prn));
                  
                  
                  DefaultTableModel model_detail = new DefaultTableModel(columnNames_detail, 0) {
                    @Override
                  public boolean isCellEditable(int row, int column) {
                       return false; // Tất cả các ô không thể chỉnh sửa
                    }
                };
                  
                for (Detail_PRN d_prn : lst_detail_prn){
                    String id_dprn = new String(d_prn.getId_prn());
                    String id_device = new String(d_prn.getId_device());
                    Device d = new ListDevice().getDevice_MySQL(id_device);
                    
                    int number = d_prn.getNumber();
                    double price_perdevice = number * d.getPrice();
                    
                    Object[] row = {
                        id_dprn, 
                        id_device, 
                        d.getNameDevice(),
                        number, 
                        new DecimalFormat("#.00").format(price_perdevice)
                    };
                     model_detail.addRow(row);
                }  
                
                T_Detail.setModel(model_detail);
            }
        });
        
        
        
        // event click row list detail
        
        T_Detail.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = T_Detail.getSelectedRow(); // Lấy hàng được chọn
                
                String id_device = new String(T_Detail.getValueAt(selectedRow, 1).toString());
                
                Device device = new ListDevice().getDevice_MySQL(id_device);
                
                String name_device = new String(device.getNameDevice());
                
                Device_Type dt = device.getDeviceType();
                
                ListSpecification lst_spec = device.getListSpec();
                
                String spec = new String("");
                
                double price_device = device.getPrice();
                
                for (Specification s : lst_spec.getListSpec()){
                    spec += s.getNameSpec() + "; ";
                }
                
                Area_DT.setText("====== DEVICE =====\n"
                        + "ID device: " + id_device + "\n"
                        + "Name device: " + name_device + "\n"
                        + "Device type: " + dt.getNameType() + "\n"
                        + "Description: " + spec + "\n"
                        + "Price: " + new DecimalFormat("#.00").format(price_device));
            }
        });
        
        
        Button_reset.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Xóa dữ liệu cũ trong model_detail trước khi thêm mới
                model_detail.setRowCount(0);
                Area_DT.setText("");
                jTextArea2.setText("");
                ArrayList<Detail_PRN> lst_dprn = new ListDetailPRN().ListDPRN_MySQL();

                for (Detail_PRN d : lst_dprn) {
                    String id_dprn = d.getId_prn();
                    String id_device = d.getId_device();

                    Device device = new ListDevice().getDevice_MySQL(id_device);
                    int number = d.getNumber();

                    double total_price_device = number * device.getPrice();
                    Object[] row = {
                        id_dprn, 
                        id_device, 
                        device.getNameDevice(),
                        number, 
                        new DecimalFormat("#.00").format(total_price_device)
                    };

                    model_detail.addRow(row);
                }

                T_Detail.setModel(model_detail);
            }
        });
        
        
        // thêm sự kiện nhập phiếu -> chuyển sang giao diện input pr
       Button_add_PR.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new Input_Product_Receipt_GUI().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        T_Product_Receipt = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        T_Detail = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Area_DT = new javax.swing.JTextArea();
        Button_add_PR = new javax.swing.JButton();
        TextField_search = new javax.swing.JTextField();
        Button_search = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        Button_reset = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        T_Product_Receipt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(T_Product_Receipt);

        T_Detail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(T_Detail);

        Area_DT.setColumns(20);
        Area_DT.setRows(5);
        jScrollPane3.setViewportView(Area_DT);

        Button_add_PR.setText("Nhập phiếu");
        Button_add_PR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_add_PRActionPerformed(evt);
            }
        });

        TextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_searchActionPerformed(evt);
            }
        });

        Button_search.setText("Tìm ID Phiếu Nhập");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane4.setViewportView(jTextArea2);

        Button_reset.setText("Reset");

        jButton1.setText("Sort");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)))
                    .addComponent(Button_reset, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Button_add_PR, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_search, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Button_search, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(Button_add_PR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Button_reset))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextField_searchActionPerformed

    private void Button_add_PRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_add_PRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_add_PRActionPerformed

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
            java.util.logging.Logger.getLogger(Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Product_Receipt_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Area_DT;
    private javax.swing.JButton Button_add_PR;
    private javax.swing.JButton Button_reset;
    private javax.swing.JButton Button_search;
    private javax.swing.JTable T_Detail;
    private javax.swing.JTable T_Product_Receipt;
    private javax.swing.JTextField TextField_search;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
