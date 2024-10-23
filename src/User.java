public class User {
    private String username;
    private String password;
    private String email;
    private String RegNumber;

    public User(String username, String password, String email, String RegNumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.RegNumber = RegNumber;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getRegNumber() {return RegNumber;}
}


