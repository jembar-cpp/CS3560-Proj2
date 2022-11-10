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
    private static Visitor visitor;

    // Users
    private static ArrayList<User> users;

    // Groups
    private static ArrayList<Group> groups;

    // Panel components
    private TreeView tv;

    private JButton bAddUser;
    private JButton bAddGroup;
    private JButton bUserView;
    private JButton bUserTotal;
    private JButton bGroupTotal;
    private JButton bMessageTotal;
    private JButton bPositiveMessagePercentage;

    private JTextField fAddUser;
    private JTextField fAddGroup;

    private AdminPanel() {
        JFrame f = new JFrame("Mini Twitter");
        users = new ArrayList<>();
        groups = new ArrayList<>();
        visitor = Visitor.getInstance();

        // Initialize the buttons and text fields
        users.add(new User("User1"));
        setLayout(null);

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

        // Admin functions
        bUserTotal = new JButton("User Total");
        bUserTotal.setBounds(0, 190, 100, 50);
        bUserTotal.addActionListener(
            e -> JOptionPane.showMessageDialog(f,
            String.format("There " + (visitor.getUserTotal() == 1 ? "is %d user." : "are %d users."), visitor.getUserTotal())));
        add(bUserTotal);

        bGroupTotal = new JButton("Group Total");
        bGroupTotal.setBounds(110, 190, 100, 50);
        bGroupTotal.addActionListener(
            e -> JOptionPane.showMessageDialog(f,
            String.format("There " + (visitor.getGroupTotal() == 1 ? "is %d group." : "are %d groups."), visitor.getGroupTotal())));
        add(bGroupTotal);

        bMessageTotal = new JButton("Message Total");
        bMessageTotal.setBounds(0, 250, 210, 50);
        bMessageTotal.addActionListener(
            e -> JOptionPane.showMessageDialog(f,
            String.format("There " + (visitor.getMessageTotal() == 1 ? "has been %d message" : "have been %d messages") + " posted.", visitor.getMessageTotal())));
        add(bMessageTotal);

        bPositiveMessagePercentage = new JButton("Positive Message Percentage");
        bPositiveMessagePercentage.setBounds(0, 310, 210, 50);
        bPositiveMessagePercentage.addActionListener(
            e -> JOptionPane.showMessageDialog(f,
            String.format("The percentage of positive messages is %.2f%%.", visitor.getPositiveMessagePercentage())));
        add(bPositiveMessagePercentage);

        // Set up the JFrame
        tv = TreeView.getInstance();
        f.setPreferredSize(new Dimension(325, 425));
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
            visitor.atUser(u); // Call the visitor
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
            visitor.atGroup(g); // Call the visitor
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
                    users.get(i).openUserView();
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
