/**
 * AdminPanel
 * Implements the Singleton pattern
 */

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class AdminPanel extends JPanel {
    // Single instance
    private static AdminPanel instance = null;

    // Users
    ArrayList<User> users;

    // Panel components
    private TreeView tv;

    private JButton bAddUser;
    private JButton bAddGroup;
    private JButton bUserView;

    private JTextField fAddUser;

    private AdminPanel() {
        users = new ArrayList<>();

        // Initialize the buttons and text fields
        bAddUser = new JButton("Add User");
        bAddUser.setBounds(0, 10, 100, 50);
        bAddUser.addActionListener(e -> addUser());
        fAddUser = new JTextField();
        fAddUser.setBounds(110,10,100,50);
        JButton b2 = new JButton("Test2");
        b2.setBounds(200, 100, 50, 50);
        add(bAddUser);
        add(fAddUser);
        add(b2);
        setLayout(null);
        setLocation(100, 100);

        // Set up the JFrame
        tv = TreeView.getInstance();
        JFrame f = new JFrame("Mini Twitter");
        f.setPreferredSize(new Dimension(450, 450));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(tv, BorderLayout.WEST);
        f.add(this);
        f.pack();
        f.setVisible(true);
    }

    /**
     * Adds a new user using the text in the Add User text field.
     * Gets called when the Add User button is clicked.
     */
    private void addUser() {
        String userID = fAddUser.getText();

        if(!userID.isEmpty()) {
            // Only add the user if the text field isn't empty
            User u = new User(userID);
            users.add(u);
            tv.addNode(u);
        }
    }

    public static AdminPanel getInstance() {
        if(instance == null) {
            instance = new AdminPanel();
        }
        return instance;
    }
}
