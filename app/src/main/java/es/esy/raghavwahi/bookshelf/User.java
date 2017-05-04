package es.esy.raghavwahi.bookshelf;

//POJO or Bean or Model
public class User {

    //Attributes
    String name,phone,email,password;
    int age;

    //Constructor
    public User() {
    }

    public User(String name, String phone, String email, String password, int age) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
