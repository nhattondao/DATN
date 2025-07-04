/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AfterLogin;

/**
 *
 * @author PC
 */
public class Users {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int dob; // Năm sinh
    private String gender;
    private int baiDaTap;
    private String roles;
    private String status;

    public Users() {
    }

    public Users(int userId, String username, String password, String email, int dob, String gender, int baiDaTap, String roles, String status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.baiDaTap = baiDaTap;
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

    public int getBaiDaTap() {
        return baiDaTap;
    }

    public void setBaiDaTap(int baiDaTap) {
        this.baiDaTap = baiDaTap;
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
