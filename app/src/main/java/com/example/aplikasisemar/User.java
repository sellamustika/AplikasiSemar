package com.example.aplikasisemar;

public class User {

    private String email;
    private String fullName;
    private String phone;
    private String password;
    private String sex;

    public User() {}

    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public User(String fullName, String email, String phone) {
        this.email = email;
        this.fullName = fullName;

        this.phone = phone;
    }

/*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);

        //Add or remove more key value pair

        return userAsMap;
    }*/

    public String getSex() { return sex; }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }



    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setSex(String sex) { this.sex = sex; }

    public String getpassword() {
        return password;
    }
}
