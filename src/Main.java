//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Run GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            UserManager userManager = new UserManager();
            new LoginFrame(userManager);
        });
    }
}