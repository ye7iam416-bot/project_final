public  abstract class User {

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    protected String phone;

    public User() {
    }

    public User(String id, String name, String username,String password, String phone) {

        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Display
    public void displayInfo() {

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Username: " + username);
        System.out.println("Phone: " + phone);
    }
}