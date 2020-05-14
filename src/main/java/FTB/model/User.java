package FTB.model;

import javafx.collections.ObservableList;

import java.util.Observable;

public class User extends Observable {

    private String username;
    private String password;
    private String role;
    private double accBalance;
    private String age;
    private String email;


    public User(){
    }

    public User(String username, String password, String role, String age, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accBalance=Math.random()*1200;
        this.age=age;
        this.email=email;
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

    public double getAccBalance(){
        return accBalance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accBalance=" + accBalance +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
