import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UserManager {
    private List<User> users;
    private static final String USER_FILE = "users.txt";

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    public boolean registerUser(String username, String password, String email, String RegNumber) {
        // Check if username already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
            if (user.getEmail().equals(email)) {
                return false;
            }
        }

        // Add new user
        users.add(new User(username, password, email, RegNumber));
        saveUsers();
        return true;
    }

    public String getRegNumber(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getRegNumber();
            }
        }
        return "";
    }

    public boolean loginUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE))) {
            for (User user : users) {
                writer.println(user.getUsername() + "," +
                        user.getPassword() + "," +
                        user.getEmail() + "," +
                user.getRegNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    users.add(new User(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

