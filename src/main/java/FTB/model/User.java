package FTB.model;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String role;
    private double accBalance;
    public ArrayList<String> orders=new ArrayList<String>();
    public ArrayList<Integer> ordersApproved = new ArrayList<Integer>(); //0 ne vazut //1 deny /2 accept

    public int nrOrders;
    private String email;
    private String age;

    public User() {
    }

    public User(String username, String password, String role,String email,String age) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accBalance=Math.random()*800;
        this.email=email;
        this.age=age;



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

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setOrders(ArrayList<String> orders) {
        this.orders = orders;
    }

    public ArrayList<String> getOrders() {
        return orders;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getNrOrders() {
        return nrOrders;
    }

    public ArrayList<Integer> getOrdersApproved() {
        return ordersApproved;
    }

    public void setOrdersApproved(ArrayList<Integer> ordersApproved) {
        this.ordersApproved = ordersApproved;
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
                ", role='" + role + '\'' +
                ", accBalance=" + accBalance +
                ", orders=" + orders +
                ", ordersApproved=" + ordersApproved +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public void setApproved(int x){

        for(int i=0;i<ordersApproved.size();i++) {
            {
                if(x==i){
                    this.ordersApproved.set(i,2);
                }


            }
        }
    }
    public void setDeny(int x){

        for(int i=0;i<ordersApproved.size();i++) {
            {
                if(x==i){
                    this.ordersApproved.set(i,1);
                }


            }
        }
    }

    public String getCurrentOrder(int i){
        String s=" ";
        s+=orders.get(i);
        return s;
    }

}
