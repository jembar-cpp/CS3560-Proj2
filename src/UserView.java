/**
 * User view for a user
 * Contains functions for users
 * Uses the Composite pattern.
 */

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public class UserView extends JPanel {
    
    User user;

    // Panel components
    private JButton bFollowUser;
    private JButton bPost;

    private JTextField fUserID;
    private JTextField fMessage;

    private DefaultListModel<String> lModel;

    // Construct a UserView with the user
    public UserView(User u) {
        user = u;
        setLayout(null);
        setLocation(100, 100);

        // Initialize the buttons and text fields
        bFollowUser = new JButton("Follow User");
        bFollowUser.setBounds(220, 10, 200, 50);
        bFollowUser.addActionListener(e -> followUser());
        fUserID = new JTextField();
        fUserID.setBounds(10, 10, 200, 50);
        add(bFollowUser);
        add(fUserID);

        // Add the following list view
        lModel = new DefaultListModel<String>();
        lModel.addElement("Following:");
        JList<String> lFollowing = new JList<>(lModel);
        lFollowing.setLayoutOrientation(JList.VERTICAL);
        lFollowing.setVisibleRowCount(5);
        lFollowing.setBounds(10, 70, 410, 100);
        add(new JScrollPane(lFollowing));
        add(lFollowing);

        // Set up the JFrame
        JFrame f = new JFrame(u.getID() + " User View");
        f.setPreferredSize(new Dimension(450, 450));
        f.add(this);
        f.pack();
        f.setVisible(true);
    }

    /**
     * Follows the user in the text field
     */
    private void followUser() {
        String userID = fUserID.getText();

        if(!userID.isEmpty()) {
            ArrayList<User> users = AdminPanel.getUsers();
            // Only follow the user if the text field isn't empty
            User u = new User(userID);
            int i = users.indexOf(u);
            if(i != -1) {
                // Valid user
                users.get(users.indexOf(user)).follow(users.get(i));
                lModel.addElement(users.get(i).toString());
            }
        }
    }
}
