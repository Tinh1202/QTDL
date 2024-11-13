package Model.UserModel;

public class Session_account {
    private static Session_account instance;
    private User_Account loggedInUser;

    // Private constructor để ngăn việc tạo đối tượng từ bên ngoài
    private Session_account() {}

    // Phương thức lấy instance của Singleton
    public static Session_account getInstance() {
        if (instance == null) {
            instance = new Session_account();
        }
        return instance;
    }

    // Thiết lập người dùng đăng nhập
    public void setLoggedInUser(User_Account user) {
        this.loggedInUser = user;
    }

    // Trả về người dùng đã đăng nhập
    public User_Account getLoggedInUser() {
        return loggedInUser;
    }

    // Kiểm tra nếu có người dùng đăng nhập
    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    // Đăng xuất
    public void logout() {
        loggedInUser = null;
    }
}
