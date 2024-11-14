package Model.UserModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vntin
 */
import Model.UserModel.Person;
import java.util.ArrayList;
import java.time.*;
public class Staff extends Person {
    private User_Account account;
    private String position;

    // Mặc định constructor
    public Staff() {
        super();
        this.account = new User_Account();
        this.position = new String("");
    }

    // Tham số constructor
    public Staff(String id, String fullname, String phone_number, LocalDate birthDate, String position, User_Account account) {
        super(id, fullname, phone_number, birthDate);
        this.account = account;
        this.position = position;
    }

    // Sao chép constructor
    public Staff(Staff s) {
        super(s.getId(), s.getFullname(), s.getPhone_number(), s.getBirthDate());
        this.account = s.account;
        this.position = s.position;
    }
    
    /*
    create getter, setter position
    */
    
    // Getter for position
    public void setAccount(User_Account account){
        this.account = account;
    }
    
    public User_Account getAccount(){
        return this.account;
    }
    
    
    public String getPosition() {
        return new String(this.position);
    }

    // Setter for position
    public void setPosition(String position) {
        this.position = new String(position);
    }

    // Override toString method
    @Override
    public String toString() {
        return "Staff{" +
                "id='" + getId() + '\'' +
                ", fullname='" + getFullname() + '\'' +
                ", phone_number='" + getPhone_number() + '\'' +
                ", birthDate=" + getBirthDate() +
                ", position='" + position + '\'' +
                '}';
    }
    
    
    
    public static Staff accessAccountInfo() {
        // Kiểm tra xem người dùng có đang đăng nhập hay không
        Staff staff_new = new Staff();

        if (Session_account.getInstance().isLoggedIn()) {
            // Lấy thông tin người dùng đã đăng nhập
            User_Account loggedInUser = Session_account.getInstance().getLoggedInUser();
            // Hiển thị thông tin người dùng đã đăng nhập
            String id_account = loggedInUser.getIdAccount();
            
            for (Staff staff : new ListStaff().ListStaff_MySQL()){
                if (id_account.equals(staff.getAccount().getIdAccount())) {
                    staff_new = staff;
                    break;
                }
            }
        }
        return staff_new;
        
    }
    
//    public static void main(String[] args){
//        boolean isLoggedIn = new User_Account().Check_Account("vntinh", "root");
//
//        if (isLoggedIn) {
//            System.out.println("Đăng nhập thành công!");
//
//            // Truy cập thông tin người dùng đã đăng nhập từ SessionManager
//            User_Account loggedInUser = Session_account.getInstance().getLoggedInUser();
//            System.out.println("Xin chào, " + loggedInUser.getUsername());
//
//            // Giả sử bạn muốn truy cập người dùng đã đăng nhập ở phần khác của ứng dụng
//            Staff staff = accessAccountInfo();
//            
//            System.out.println(staff.getFullname());
//        } else {
//            System.out.println("Sai tài khoản hoặc mật khẩu.");
//        }
//    }
}

