/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.DeviceModel.Device;
import Model.DeviceModel.Device_Type;
import Model.DeviceModel.ListDevice;
import Model.DeviceModel.ListDeviceType;
import Model.Delivery.Detail_DeliveryNote;
import Model.Delivery.ListDetailDN;
import Model.Delivery.Delivery_Note;
import Model.Delivery.ListDeliveryNote;
import Model.UserModel.Customer;
import Model.UserModel.List_Customer;
import Model.UserModel.Session_account;
import Model.UserModel.Staff;
import Model.UserModel.User_Account;
import com.mysql.cj.jdbc.CallableStatement;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.table.DefaultTableModel;
import java.sql.Timestamp;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author OS
 */
public class Export_Note extends javax.swing.JFrame {

    /**
     * Creates new form Export_Note
     */
    
    private ArrayList<Device> listDevice;
    private ArrayList<Delivery_Note> listDN;
    private ArrayList<Detail_DeliveryNote> ListDDN;
    private ArrayList<Customer> listCus;
    private ListDetailDN newListDDN;
    private ListDeliveryNote newListDN;
    private Delivery_Note deliveryNote;
    Staff staff = new Staff();
    public Export_Note() {
        initComponents();
        if (Session_account.getInstance().isLoggedIn()) {
            User_Account loggedInUser = Session_account.getInstance().getLoggedInUser();
            staff = new Staff().accessAccountInfo();
        }
        
        jTextField1.setText(staff.getId());
        jTextField1.setEditable(false);
        jLabel1.setFocusable(true);

        ListDevice ld = new ListDevice();
        this.listDevice = new ArrayList<Device>(ld.getDevicesFromDatabase());

        refresh();

    }

    public void refresh(){
        ListDeliveryNote ldn = new ListDeliveryNote();
        this.listDN = new ArrayList<Delivery_Note>(ldn.ListDN_MySQL());

        ListDetailDN lddn = new ListDetailDN();
        this.ListDDN = new ArrayList<Detail_DeliveryNote>(lddn.ListDDN_MySQL());

        this.newListDDN = new ListDetailDN();
        this.newListDN = new ListDeliveryNote();
        this.deliveryNote = new Delivery_Note();

        this.listCus = new ArrayList<Customer>(new List_Customer().ListCustomer_MySQL());
        
    }
    

    public boolean checkIdDevice(String id) {
        for (Device d : listDevice) {
            if (d.getIdDevice().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkNameDevice(String name) {
        for (Device d : listDevice) {
            if (d.getNameDevice().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkNameCustomer(String name) {
        for (Customer c : this.listCus) {
            if (c.getFullname().equals(name)) {
                return true;
            }
        }
        return false;

    }
    public boolean checkPhoneCustomer(String phone) {
        for (Customer c : this.listCus) {
            if (c.getPhone_number().equals(phone)) {
                return true;
            }
        }
        return false;

    }
    public boolean checkField() {
        if (jTextField5.getText().isEmpty() || jTextField6.getText().isEmpty() || jTextField7.getText().isEmpty()
                || jTextField2.getText().isEmpty() || jTextField3.getText().isEmpty()
                || jTextField2.getText().equals("Enter name") && jTextField3.getText().equals("Enter phone number")) {
            return false;
        }
        return true;
    }
    public String getNameTypeFromId(String id){
        String name = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL getNameTypeDeviceFormID(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, id);
            callableStatement.execute();
            name = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    public String getNameDeviceFromID(String id) {
        String name = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL getNameDeviceFromID(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, id);
            callableStatement.execute();
            name = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    
    public ArrayList<String> getPhoneCustomer(String name) {
        ArrayList<String> a = new ArrayList<>();
        for (Customer c : this.listCus) {
            if (c.getFullname().equals(name)) {
                a.add(c.getPhone_number());
            }
        }
        return a;
    }
    public String getNameCustomerFromPhone(String phone){
        String name = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL  getNameCustomerFromPhone(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, phone);
            callableStatement.execute();
            name = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    public String getIdCustomerFromNamePhone(String name, String phone){
        String id = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? =  CALL getIdCustomerFromNamePhone(?,?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, name);
            callableStatement.setString(3, phone);
            callableStatement.execute();
            id = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
    public String getNameTypeFormIdDevice(String id_device) {
        String name = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL getNameTypeFromIdDevice(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, id_device);
            callableStatement.execute();
            name = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return name;
    }
    public String getIdDeviceFromName(String name) {
        String id = "";
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL getIdDeviceFromName(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.VARCHAR);
            callableStatement.setString(2, name);
            callableStatement.execute();
            id = callableStatement.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }
    public Double getPriceFromId(String id_device){
        double price = 0;
        CallableStatement callableStatement = null;
        java.sql.Connection conn = null;
        try {
            Model.Connect.Connection c = new Model.Connect.Connection();
            conn = c.getJDBC();
            String sql = "{ ? = CALL getPriceFromId(?) }";
            callableStatement = (CallableStatement) conn.prepareCall(sql);
            callableStatement.registerOutParameter(1, java.sql.Types.DOUBLE);
            callableStatement.setString(2, id_device);
            callableStatement.execute();
            price = callableStatement.getDouble(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (callableStatement != null) callableStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return price;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String newIdDN(int row) {
        row = row + 1;
        String str = String.valueOf(row);
        StringBuilder sb = new StringBuilder("DN");
        for (int i = 0; i < 3 - str.length(); i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }
    public static String newIdDDN(int row){
        row = row + 1;
        String str = String.valueOf(row);
        StringBuilder sb = new StringBuilder("DDN");
        for (int i = 0; i < 3 - str.length(); i++) {
            sb.append("0");
        }
        sb.append(str);
        return sb.toString();
    }
    public void createImgExNo(String id_dn, double payment){
        int width = 791;
        int height = 600;
        BufferedImage invoiceImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = invoiceImage.getGraphics();      
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        //Thêm tiêu đề hóa đơn
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24)); // Tăng kích thước font tiêu đề
        String title = "Group 3";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        int titleX = (width - titleWidth) / 2;  // Căn giữa tiêu đề
        g.drawString(title, titleX, 40);
        // Địa chỉ
        g.setFont(new Font("Arial", Font.PLAIN, 18)); // Font cho địa chỉ
        String address = "Address: KTX KHU A";
        int addressWidth = g.getFontMetrics().stringWidth(address);
        int addressX = (width - addressWidth) / 2;  // Căn giữa địa chỉ
        g.drawString(address, addressX, 60);      
        
        g.setFont(new Font("Arial", Font.BOLD, 20)); // Tăng kích thước font cho phần tiêu đề bảng
        String invoiceTitle = "Invoice";
        int invoiceTitleWidth = g.getFontMetrics().stringWidth(invoiceTitle);
        int invoiceTitleX = (width - invoiceTitleWidth) / 2;  // Căn giữa tiêu đề bảng
        g.drawString(invoiceTitle, invoiceTitleX, 110);
        // Đặt font cho bảng
        g.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng kích thước font cho các dòng thông tin
        int yPosition = 180;
        // Căn giữa các dòng thông tin
        String[] lines1 = {
            "Customer name: " + jTextField2.getText(),           
            "Customer phone number: " + jTextField3.getText()    
        };
        String[] lines2 = {
            "Staff name: " + staff.getFullname(),
            "Staff id: " + staff.getId()
        };       
        for (String line : lines1) {
            int lineWidth = g.getFontMetrics().stringWidth(line);
            int lineX = 50;  // Căn giữa các dòng thông tin
            g.drawString(line, lineX, yPosition);
            yPosition += 20;
        }
        yPosition = 180;
        for (String line : lines2) {
            int lineWidth = g.getFontMetrics().stringWidth(line);
            int lineX = 500;  // Căn giữa các dòng thông tin
            g.drawString(line, lineX, yPosition);
            yPosition += 20;
        }
        yPosition += 50;
        // Thêm tiêu đề cho bảng sản phẩm
        int xPosition = 50;
        String[] headers = {"Product", "Quantity", "Price"};
        for (String header : headers) {
            int headerWidth = g.getFontMetrics().stringWidth(header);
            int headerX = xPosition;  
            g.drawString(header, headerX, yPosition);
            xPosition += 247;
        }
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        int columnCount = model.getColumnCount();
        String[][] items = new String[rowCount][columnCount-1];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount-1 ; j++) {
                items[i][j] = model.getValueAt(i, j).toString();
            }
        }
        System.out.print(payment);
        yPosition += 20;
        for (String[] item : items) {
            xPosition = 50;
            for(String s: item){                            
                int itemX = xPosition;  
                g.drawString(s, itemX, yPosition);          
                xPosition += 247;
            }
            yPosition += 20;
        }
        //Tính và hiển thị tổng cộng
        g.setFont(new Font("Arial", Font.BOLD, 16)); // Tăng kích thước font cho tổng cộng
        String total1 = "Payment: ";
        String format = String.format("%.2f", payment);
        
        String total = total1.concat(String.valueOf(format));
        int totalWidth = g.getFontMetrics().stringWidth(total);
        int totalX = (width - totalWidth) / 2;  // Căn giữa tổng cộng
        g.drawString(total, totalX, yPosition + 20);
        g.drawString("Customer",100,yPosition + 60);
        g.drawString("Staff", 544, yPosition + 60);
        g.dispose();
        // Đặt đường dẫn lưu ảnh
        String a = "D:/t1/Invoices/";
        String outputPath1 = a.concat(id_dn);
        String outputPath = new String(outputPath1.concat(".png"));
        try {
            File outputFile = new File(outputPath);
            // Tạo thư mục nếu chưa tồn tại
            outputFile.getParentFile().mkdirs();
            ImageIO.write(invoiceImage, "png", outputFile);
            System.out.println("Hóa đơn đã được lưu thành công tại: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu hóa đơn: " + e.getMessage());
        }
        JFrame frame = new JFrame("Hiển thị hóa đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        // Tạo JPanel để vẽ ảnh
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(invoiceImage, 0, 0, this); // Vẽ ảnh lên JPanel
            }
        };
        frame.add(panel); frame.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Staff");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 21, 37, -1));

        jLabel2.setText("Customer");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 69, -1, -1));

        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Enter id staff");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 18, 440, -1));

        jTextField2.setForeground(new java.awt.Color(204, 204, 204));
        jTextField2.setText("Enter name");
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 66, 121, -1));

        jButton2.setText("Add new customer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, -1, -1));

        jTextField3.setColumns(12);
        jTextField3.setForeground(new java.awt.Color(204, 204, 204));
        jTextField3.setText("Enter phone number");
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jPanel2.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 66, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose phone number" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 66, 160, -1));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name device", "Quantity", "Price", "Total price"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Id device");

        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Name device");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel7.setText("Quantity");

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jButton1.setText("Add product");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose name device" }));
        jComboBox2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox2FocusGained(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jTextField5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7)
                    .addComponent(jComboBox2, 0, 171, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("Done");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(193, 193, 193))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id_device = new String(jTextField5.getText().trim());
        String name_device = new String(jTextField6.getText().trim());
    
        if (!checkField()) {
            JOptionPane.showConfirmDialog(rootPane, "Please enter full information");
        }
        else if (getNameDeviceFromID(id_device)!=null && checkNameDevice(name_device) && isInteger(jTextField7.getText().trim())) {
            int quantity = Integer.valueOf(jTextField7.getText().trim());
            int selectedRow = -1;
            int index = 0;
            int a = 0;
            for(Detail_DeliveryNote dn : newListDDN.getListDDN()){
                if(dn.getId_device().equals(id_device)){
                    int new_quantity = quantity + dn.getQuantity();                  
                    dn.setQuantity(new_quantity);                   
                    selectedRow = index;
                    a = 1;
                    jTable1.setValueAt(new_quantity, selectedRow, 1);
                    jTable1.setValueAt(new_quantity * getPriceFromId(id_device), selectedRow, 3);
                    break;
                }
                index++;
            }
            if(a==0){
                String name_type = getNameTypeFormIdDevice(id_device);           
                double price = getPriceFromId(id_device);
                DecimalFormat df = new DecimalFormat("#,##0.00"); // Định dạng số với dấu phẩy và 2 chữ số thập phân
                String formattedPrice = df.format(price);
                String id_dn = newIdDN(this.listDN.size());
                String id_ddn = newIdDDN(this.ListDDN.size() + jTable1.getRowCount());
                Detail_DeliveryNote ddn = new Detail_DeliveryNote(id_ddn,id_dn, id_device, quantity);
                double totalPrice = quantity * getPriceFromId(id_device);
                String formattedTotalPrice = df.format(totalPrice);
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{name_device, quantity, formattedPrice, formattedTotalPrice});

                newListDDN.AddDDNToList(ddn);
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
            }           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if (jTextField1.getText().equals("Enter id staff")) {
            jTextField1.setText(""); // Xóa placeholder
            jTextField1.setForeground(Color.BLACK); // Đổi lại màu chữ
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().isEmpty()) {
            jTextField1.setText("Enter id staff"); // Đặt lại placeholder
            jTextField1.setForeground(Color.GRAY); // Đổi lại màu chữ mờ
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        if (jTextField2.getText().equals("Enter name")) {
            jTextField2.setText(""); // Xóa placeholder
            jTextField2.setForeground(Color.BLACK); // Đổi lại màu chữ
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        if (jTextField2.getText().isEmpty()) {
            jTextField2.setText("Enter name"); // Đặt lại placeholder
            jTextField2.setForeground(Color.GRAY); // Đổi lại màu chữ mờ
        } else if (checkNameCustomer(jTextField2.getText().trim())) {
            ArrayList<String> listphone = new ArrayList<>(getPhoneCustomer(jTextField2.getText().trim()));
            String phone1 = listphone.getFirst();

            if (listphone.size() == 1) {
                jTextField3.setText(phone1);
                jTextField3.setForeground(Color.BLACK);
            } else {
                for (String phone : listphone) {
                    jComboBox1.addItem(phone);
                    jComboBox1.setMaximumRowCount(jComboBox1.getItemCount());
                }
            }

        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AddCustomer_Form dialog = new AddCustomer_Form(this, true);
        dialog.setLocationRelativeTo(this); // Căn giữa
        dialog.setVisible(true); // Hiển thị popup
        String phone_number = dialog.sharePhoneNumber();
        refresh();
        jTextField3.setText(phone_number);
        jTextField2.setText(dialog.shareName());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
        if (getNameDeviceFromID(jTextField5.getText().trim())!=null) {
            jTextField6.setText(getNameDeviceFromID(jTextField5.getText().trim()));
        }
    }//GEN-LAST:event_jTextField5FocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (newListDDN.getLengthListDDN() != 0) {
            try {
                Model.Connect.Connection c = new Model.Connect.Connection(){};
                java.sql.Connection conn = c.getJDBC();
                String sqlDeliveryNote = "{CALL insertDeliveryNote(?, ?, ?, ?)}";
                CallableStatement stmtDeliveryNote = (CallableStatement) conn.prepareCall(sqlDeliveryNote);
                String id_dn = newIdDN(this.listDN.size());
                System.out.println(id_dn);
                String id_staff = jTextField1.getText();
                String id_customer = getIdCustomerFromNamePhone(jTextField2.getText().trim(), jTextField3.getText().trim());
                LocalDateTime date_shipment = LocalDateTime.now();
                stmtDeliveryNote.setString(1, id_dn);
                stmtDeliveryNote.setString(2, id_staff);
                stmtDeliveryNote.setString(3, id_customer);
                stmtDeliveryNote.setTimestamp(4, Timestamp.valueOf(date_shipment));
                stmtDeliveryNote.executeUpdate();
                stmtDeliveryNote.close();
                String sqlDetailDeliveryNote = "{CALL insertDetailDeliveryNote(?, ?, ?, ?)}";
                CallableStatement stmtDetailDeliveryNote = (CallableStatement) conn.prepareCall(sqlDetailDeliveryNote);
                double payment = 0;
                for (Detail_DeliveryNote dn : newListDDN.getListDDN()) {
                    String id_device = dn.getId_device();
                    String id_ddn = dn.getIdDetail_dn();
                    int quantity = dn.getQuantity();
                    payment += getPriceFromId(id_device) * quantity;
                    stmtDetailDeliveryNote.setString(1, id_ddn);
                    stmtDetailDeliveryNote.setString(2, id_dn);
                    stmtDetailDeliveryNote.setString(3, id_device);
                    stmtDetailDeliveryNote.setInt(4, quantity);
                    stmtDetailDeliveryNote.executeUpdate();
                }
                stmtDetailDeliveryNote.close();
                conn.close();
                JOptionPane.showMessageDialog(this, "Success");
                createImgExNo(id_dn, payment);
                refresh();
                jTextField2.setText(""); 
                jTextField3.setText(""); 
                jTextField6.setText(""); 
                jTextField5.setText(""); 
                jTextField7.setText("");
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "There are no products yet");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained
        if (jTextField3.getText().equals("Enter phone number")) {
            jTextField3.setText(""); // Xóa placeholder
            jTextField3.setForeground(Color.BLACK); // Đổi lại màu chữ
        }
    }//GEN-LAST:event_jTextField3FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        if (jTextField3.getText().isEmpty()) {
            jTextField3.setText("Enter phone number"); // Đặt lại placeholder
            jTextField3.setForeground(Color.GRAY); // Đổi lại màu chữ mờ
        } else if (checkPhoneCustomer(jTextField3.getText().trim()) && !jTextField2.getText().isEmpty()) {
            jTextField2.setText(getNameCustomerFromPhone(jTextField3.getText()));
            jTextField2.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String phone = jComboBox1.getSelectedItem().toString();
        if (!phone.equals("Choose phone number")) {
            jTextField3.setText(phone);
            jTextField3.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String name = jComboBox2.getSelectedItem().toString();
        if (!name.equals("Choose name device")) {
            jTextField6.setText(name);
            jTextField6.setForeground(Color.BLACK);
            jTextField5.setText(getIdDeviceFromName(name));
            jTextField5.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox2FocusGained
        for (Device d : this.listDevice) {
            jComboBox2.addItem(d.getNameDevice());
        }
        jComboBox2.setMaximumRowCount(5);
    }//GEN-LAST:event_jComboBox2FocusGained

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow(); // Lấy hàng được chọn
        int selectedColumn = 1; // Lấy cột được chọn   
        if (selectedRow != -1) {
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) { // Chuột trái
                // Hiển thị hộp thoại sửa giá trị
                String newValue = JOptionPane.showInputDialog(this, "Enter new quantity:");
                if (newValue != null && !newValue.trim().isEmpty()) {
                    jTable1.setValueAt(newValue, selectedRow, selectedColumn);

                    double a = Double.parseDouble(jTable1.getValueAt(selectedRow, 2).toString());
                    double b = Double.parseDouble(newValue);
                    jTable1.setValueAt(a * b, selectedRow, 3);
                    
                    String id = getIdDeviceFromName(jTable1.getValueAt(selectedRow,0).toString());
                    for(Detail_DeliveryNote dn : newListDDN.getListDDN()){
                        if(dn.getId_device().equals(id)){
                            dn.setQuantity(Integer.parseInt(newValue));
//                            dn.setPrice(a*b);
                        }
                    }
                }
            } 
            if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) { // Chuột phải
                int choice = JOptionPane.showConfirmDialog(this, "Delete this product?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();                                     
                    String deviceIdToDelete = getIdDeviceFromName(jTable1.getValueAt(selectedRow,0).toString());
                    ArrayList<Detail_DeliveryNote> updatedList = newListDDN.DeleteIdDeviceFromList(newListDDN.getListDDN(), deviceIdToDelete);
                    // Cập nhật danh sách sau khi xóa
                    newListDDN.setListDDN(updatedList);
                    model.removeRow(selectedRow);
//                    for (Detail_DeliveryNote ddn : newListDDN.getListDDN()) {
//                        System.out.println("ID DN: " + ddn.getId_dn() + ", ID Device: " + ddn.getId_device() + 
//                           ", Quantity: " + ddn.getQuantity() + ", Price: " + ddn.getPrice());
//                    }
                }
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        int row = jTable1.rowAtPoint(evt.getPoint()); // Lấy vị trí hàng được nhấn
        if (SwingUtilities.isRightMouseButton(evt)) { // Kiểm tra nếu nhấp chuột phải
            if (row != -1 && !jTable1.isRowSelected(row)) { // Nếu hàng chưa chọn, chọn hàng đó
                jTable1.setRowSelectionInterval(row, row);
            }   
        }
    }//GEN-LAST:event_jTable1MousePressed

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
            java.util.logging.Logger.getLogger(Export_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Export_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Export_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Export_Note.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

//        System.out.print(list_DT.size());
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Export_Note().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
