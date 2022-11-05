/**
 * AdminPanel
 * Implements the Singleton pattern
 */

public class AdminPanel {
    // Single instance
    private static AdminPanel panel = null;
    
    private AdminPanel() {

    }

    public static AdminPanel getInstance() {
        if(panel == null) {
            panel = new AdminPanel();
        }
        return panel;
    }
}
