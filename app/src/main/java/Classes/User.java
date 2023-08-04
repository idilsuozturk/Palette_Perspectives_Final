package Classes;

public abstract class User {
    private String name;
    private int id;
    private String password;
    private int age;

    // Constructor
    public User(String name, int id, String password, int age) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
    }

    // Getters and setters for attributes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void deleteAccount() {
        //TO-DO delete account from database
    }

    public void changePassword(String password) {
        this.password = password;
    }
}
