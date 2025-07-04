/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin.QlyNguoiDung;

/**
 *
 * @author Admin
 */
public class QLND_Model {

    private int userId;
    private String username;
    private String password;
    private String email;
    private int dob;
    private String gender;
    private String roles;
    private String status;

    public QLND_Model() {
    }

    public QLND_Model(int userId, String username, String password, String email, int dob, String gender, String roles, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.roles = roles;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDob() {
        return dob;
    }

    public void setDob(int dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
