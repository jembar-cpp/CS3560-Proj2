/**
 * AdminPanel
 * Contains admin functions
 * Implements the Singleton pattern and Composite pattern.
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
    private static ArrayList<User> users;

    // Groups
    private static ArrayList<Group> groups;

    // Panel components
    private TreeView tv;

    private JButton bAddUser;
    private JButton bAddGroup;
    private JButton bUserView;

    private JTextField fAddUser;
    private JTextField fAddGroup;

    private AdminPanel() {
        users = new ArrayList<>();
        groups = new ArrayList<>();

        // Initialize the buttons and text fields
        users.add(new User("User1"));
        setLayout(null);
        setLocation(100, 100);

        // Add user
        bAddUser = new JButton("Add User");
        bAddUser.setBounds(0, 10, 100, 50);
        bAddUser.addActionListener(e -> addUser());
        fAddUser = new JTextField();
        fAddUser.setBounds(110, 10, 100, 50);
        add(bAddUser);
        add(fAddUser);

        // Add group
        bAddGroup = new JButton("Add Group");
        bAddGroup.setBounds(0, 70, 100, 50);
        bAddGroup.addActionListener(e -> addGroup());
        fAddGroup = new JTextField();
        fAddGroup.setBounds(110, 70, 100, 50);
        add(bAddGroup);
        add(fAddGroup);

        // User view
        bUserView = new JButton("Open User View");
        bUserView.setBounds(0, 130, 210, 50);
        bUserView.addActionListener(e -> openUserView());
        add(bUserView);

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
    public void addUser() {
        String userID = fAddUser.getText();

        if(!userID.isEmpty()) {
            // Only add the user if the text field isn't empty
            User u = new User(userID);
            users.add(u);
            tv.addNode(u);
        }
    }

    /**
     * Adds a new group using the text in the Add Group text field.
     * Gets called when the Add Group button is clicked.
     */
    private void addGroup() {
        String groupID = fAddGroup.getText();

        if(!groupID.isEmpty()) {
            // Only add the user if the text field isn't empty
            Group g = new Group(groupID);
            groups.add(g);
            tv.addNode(g);
        }
    }

    /**
     * Opens the selected user's user view
     */
    private void openUserView() {
        // Get the selected user
        if(tv.getLastSelected() != null) {
            Object o = tv.getLastSelected().getUserObject();
            if(o instanceof User) {
                int i = users.indexOf(o);
                if(i != -1) {
                    new UserView(users.get(i));
                }
            }
        }
    }

    public static AdminPanel getInstance() {
        if(instance == null) {
            instance = new AdminPanel();
        }
        return instance;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }
}
