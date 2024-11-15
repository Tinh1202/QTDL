package View.PR_GUI;





import Control.Input_Pr_controller;
import Control.input_detail_controller;
import Model.Delivery.Detail_PRN;
import Model.Delivery.ListDetailPRN;
import Model.Delivery.ListProduct_Receipt;
import Model.Delivery.Product_Receipt;
import Model.DeviceModel.Device;
import Model.DeviceModel.Device_Type;
import Model.DeviceModel.ListDevice;
import Model.DeviceModel.ListDeviceType;
import Model.DeviceModel.ListSpecification;
import Model.DeviceModel.ListSupplier;
import Model.DeviceModel.Specification;
import Model.DeviceModel.Supplier;
import Model.UserModel.ListStaff;
import Model.UserModel.Session_account;
import Model.UserModel.Staff;
import Model.UserModel.User_Account;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vntin
 */
public class Input_Product_Receipt_GUI extends javax.swing.JFrame {

    /**
     * Creates new form Input_Product_Receipt_GUI
     */
    ArrayList<Detail_PRN> lst_detail_prn = new ArrayList<Detail_PRN>();
    Staff staff = new Staff();
    Detail_PRN selectedDetail = null;    
    public Input_Product_Receipt_GUI() {
        initComponents();
        
        // lấy các danh sách phiếu trong csdl vào bảng 
        List_PR_table.setModel((new Input_Pr_controller().setDefaultTable()));
        
        // set ID random cho phiếu nhập
        id_pr_input.setText(new Input_Pr_controller().random_id_product_receipt());
        
        // set datetime cho phiếu
        time_label.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));

        
        // set default table detail product receipt
        List_detail_PR_table.setModel(new input_detail_controller().DefaultTable());
        
        // set total price chi tiết phiếu nhập mặc định bằng 0
        total_price_input.setText(Double.toString(0.0));
        
        // khởi tạo đối tượng chứa mảng ListDetail_PRN
        final ListDetailPRN list_detail_prn = new ListDetailPRN();
        
        // set thông tin staff nhân viên nhập phiếu từ session
        if (Session_account.getInstance().isLoggedIn()) {
            User_Account loggedInUser = Session_account.getInstance().getLoggedInUser();
            staff = new Staff().accessAccountInfo();
        }
        jTextArea1.setText("========Staff========" + "\n"
                + "Staff id: " + staff.getId() + "\n"
                    + "Staff name: " + staff.getFullname() + "\n"
                        + "Staff phone: " + staff.getPhone_number() + "\n"
                                + "Staff position: " + staff.getPosition() + "\n"
        
        );
        // kết thúc
        
        
        // set lựa chọn supplier 
        supplier_combobox.removeAllItems();
        for (Supplier s : new ListSupplier().getSuppliersFromDatabase()){
            supplier_combobox.addItem(s.getNameSupplier());
        }
        // kết thúc
        
        
        // action nhập thông tin thiết bị muốn nhập
        id_device_input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
               // lấy chuỗi id device thực hiện action
               String id_device = id_device_input.getText();
               Device device = new ListDevice().getDevice_MySQL(id_device);
               
               // cập nhật thông tin của thiết bị lên các component tương ứng
               device_name_input.setText(device.getNameDevice()); // tên thiết bị
               name_dt.setText(device.getDeviceType().getNameType()); // tên loại thiết bị
               price_per_device_input.setText(Double.toString(device.getPrice()));
               
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        
        });
        
        
        // set sự kiện cho jspinner: total price tăng giảm theo số lượng * price thiết bị
        number_spiner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int number = (int) number_spiner.getValue();
                double price = Double.parseDouble(price_per_device_input.getText());
                double total = price * number;

                // Sử dụng DecimalFormat để định dạng total thành chuỗi với 2 chữ số thập phân
                total_price_input.setText(new DecimalFormat("#.00").format(total));
            }
        });
        // kết thúc
        
        // set sự kiện button reset
        
        reset_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reset tạo id nhập phiếu mới
                id_pr_input.setText(new Input_Pr_controller().random_id_product_receipt());
                // set lại thời gian
                time_label.setText(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
                // reset lại id
                id_device_input.setText("");
                //reset device name
                device_name_input.setText("");
                
                // reset lại type
                name_dt.setText("");
                price_per_device_input.setText("");
                total_price_input.setText("0.0");
                
                // reset lại bảng list detail prn -> reset lại list_detail
                List_detail_PR_table.setModel(new input_detail_controller().DefaultTable());
                
                lst_detail_prn = new ArrayList<>();
                
                supplier_combobox.setSelectedIndex(0);
            }
        });
        // kết thúc
        
        
        // thêm sự kiện cho nút add detail
        // tạo mảng chứa các chi tiết phiếu nhập để hiển thị lên bảng
        
        add_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // tạo detail_prn 
                //----------------
                // tạo random id cho chi tiết phiếu
                String id_detail_prn = new input_detail_controller().create_random_id();
                String id_prn = id_pr_input.getText();
                String id_device = id_device_input.getText();
                int number = (int) number_spiner.getValue();
                
                Detail_PRN detail = new Detail_PRN(id_detail_prn, id_prn, id_device, number);
                lst_detail_prn.add(detail);
                // set cho bảng hiển thị lên các chi tiết phiếu (chưa cập nhật csdl)
                List_detail_PR_table.setModel(new input_detail_controller().setDefaultTable(lst_detail_prn));
            }
            
        });
        
        
        
        // thêm sự kiện cho button create -> cập nhật các chi tiết phiếu lên csdl và cập nhật phiếu nhập lên csdl
        Create_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // insert các chi phiếu trong bảng detail vào bảng csdl 
                
                // insert product receipt trước
                String id_pr = id_pr_input.getText();
                
                String timeText = time_label.getText();
                
                
                
                // lấy id của supplier
                String name_supplier = (String) supplier_combobox.getSelectedItem();
                Supplier supplier = new Supplier();
                for (Supplier s : new ListSupplier().getSuppliersFromDatabase()){
                    if (s.getNameSupplier().equals(name_supplier)){
                        supplier = s;
                        break;
                    }
                }
                
                Product_Receipt pr = new Product_Receipt(id_pr, LocalDateTime.now(), staff, supplier, lst_detail_prn);
                
//                System.out.println(pr.toString());
                
                // thêm pr vào csdl
                new Input_Pr_controller().insert_product_receipt(pr);
                
                // thêm detail pr vào csdl
                for (Detail_PRN detail : lst_detail_prn){
                    new input_detail_controller().insert_detail_prn_into_mysql(detail);
                }
                
                // reset lại bảng và array list detail
                lst_detail_prn = new ArrayList<Detail_PRN>();
                List_detail_PR_table.setModel(new input_detail_controller().DefaultTable());
                
                // reload lại bảng list pr
                List_PR_table.setModel(new Input_Pr_controller().setDefaultTable());
            }
            
        });
        
        // thêm sự kiện cho chọn row trong detail pr table
       List_detail_PR_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent event) {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = List_detail_PR_table.getSelectedRow();

                if (selectedRow != -1) {
                    String id_detail_pr = List_detail_PR_table.getValueAt(selectedRow, 0).toString();
                    String id_pr = List_detail_PR_table.getValueAt(selectedRow, 1).toString();
                    String id_device = List_detail_PR_table.getValueAt(selectedRow, 2).toString();
                    String name_device = List_detail_PR_table.getValueAt(selectedRow, 3).toString();
                    int number = Integer.parseInt(List_detail_PR_table.getValueAt(selectedRow, 4).toString());

                    // Lưu lại đối tượng Detail PR đã chọn
                    selectedDetail = new Detail_PRN(id_detail_pr, id_pr, id_device, number);
                }
            }
        }
    });

// Thêm sự kiện cho nút xóa
    Detele_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedDetail != null) {
                // Sử dụng Iterator để xóa phần tử khỏi danh sách
                Iterator<Detail_PRN> iterator = lst_detail_prn.iterator();
                while (iterator.hasNext()) {
                    Detail_PRN detail = iterator.next();
                    if (detail.getIdDetail_PR().equals(selectedDetail.getIdDetail_PR())) {
                        iterator.remove(); // Xóa phần tử đã chọn
                    }
                }

                // Cập nhật lại bảng với dữ liệu mới
                List_detail_PR_table.setModel(new input_detail_controller().setDefaultTable(lst_detail_prn));

                // Reset selectedDetail sau khi xóa
                selectedDetail = null;
            } else {
                System.out.println("Vui lòng chọn một dòng để xóa.");
            }
        }
    });
    
    
    // set action button cho edit -> navigating product_receipt
        
    edit_button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product_Receipt_GUI pr_gui =  new Product_Receipt_GUI();
            pr_gui.setVisible(true);
            pr_gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Chỉ đóng cửa sổ hiện tại
            
        }
    });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        List_detail_PR_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        List_PR_table = new javax.swing.JTable();
        label1 = new java.awt.Label();
        id_pr_input = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        id_device_input = new javax.swing.JTextField();
        add_button = new javax.swing.JButton();
        cancel_button = new javax.swing.JButton();
        label4 = new java.awt.Label();
        device_name_input = new javax.swing.JTextField();
        label5 = new java.awt.Label();
        number_spiner = new javax.swing.JSpinner();
        label9 = new java.awt.Label();
        label10 = new java.awt.Label();
        price_per_device_input = new javax.swing.JTextField();
        List_detail_PR_label = new java.awt.Label();
        List_PR_label = new java.awt.Label();
        reset_button = new javax.swing.JButton();
        label13 = new java.awt.Label();
        total_price_input = new javax.swing.JTextField();
        back_button = new javax.swing.JButton();
        Create_button = new javax.swing.JButton();
        time_label = new java.awt.Label();
        Staff = new java.awt.Label();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        name_dt = new javax.swing.JTextField();
        Detele_button = new javax.swing.JButton();
        supplier_combobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        edit_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        List_detail_PR_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(List_detail_PR_table);

        List_PR_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(List_PR_table);

        label1.setText("Input detail Product Receipt");

        label2.setText("ID Product Receipt");

        label3.setText("ID Device");

        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });

        cancel_button.setText("Cancel");

        label4.setText("Device's name");

        label5.setText("Device type");

        label9.setText("Number");

        label10.setText("Price");

        price_per_device_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                price_per_device_inputActionPerformed(evt);
            }
        });

        List_detail_PR_label.setText("List detail PR");

        List_PR_label.setText("List PR");

        reset_button.setText("Reset");

        label13.setText("Total price");

        back_button.setText("Back");

        Create_button.setText("Create");
        Create_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Create_buttonActionPerformed(evt);
            }
        });

        Staff.setText("Staff");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        Detele_button.setText("Delete");

        supplier_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Supplier");

        edit_button.setText("More edit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(List_PR_label, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(List_detail_PR_label, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Create_button, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Detele_button, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Staff, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(label10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                            .addComponent(label13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(number_spiner, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_price_input, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(price_per_device_input, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(supplier_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(time_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(label4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(back_button))
                            .addComponent(device_name_input, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(id_pr_input)
                            .addComponent(id_device_input, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(name_dt)
                                .addGap(110, 110, 110)))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(List_detail_PR_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back_button)
                    .addComponent(time_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(id_pr_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_device_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(device_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_dt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(price_per_device_input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(number_spiner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_price_input, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Staff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(supplier_combobox)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Create_button, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(Detele_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(List_PR_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reset_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_buttonActionPerformed

    private void price_per_device_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_price_per_device_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_price_per_device_inputActionPerformed

    private void Create_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Create_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Create_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(Input_Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Input_Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Input_Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Input_Product_Receipt_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Input_Product_Receipt_GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Create_button;
    private javax.swing.JButton Detele_button;
    private java.awt.Label List_PR_label;
    private javax.swing.JTable List_PR_table;
    private java.awt.Label List_detail_PR_label;
    private javax.swing.JTable List_detail_PR_table;
    private java.awt.Label Staff;
    private javax.swing.JButton add_button;
    private javax.swing.JButton back_button;
    private javax.swing.JButton cancel_button;
    private javax.swing.JTextField device_name_input;
    private javax.swing.JButton edit_button;
    private javax.swing.JTextField id_device_input;
    private javax.swing.JTextField id_pr_input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label13;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label9;
    private javax.swing.JTextField name_dt;
    private javax.swing.JSpinner number_spiner;
    private javax.swing.JTextField price_per_device_input;
    private javax.swing.JButton reset_button;
    private javax.swing.JComboBox<String> supplier_combobox;
    private java.awt.Label time_label;
    private javax.swing.JTextField total_price_input;
    // End of variables declaration//GEN-END:variables
}
