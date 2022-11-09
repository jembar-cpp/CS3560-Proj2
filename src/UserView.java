/**
 * User view for a user
 * Contains functions for users
 * Uses the Composite pattern.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;

public class UserView extends JPanel {
    private User user;

    // Panel components
    private JButton bFollowUser;
    private JButton bPost;

    private JTextField fUserID;
    private JTextField fMessage;

    private JLabel lbFollowing;
    private JLabel lbFeed;

    private DefaultListModel<String> lmFollowerModel;
    private DefaultListModel<String> lmFeedModel;

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
        lbFollowing = new JLabel("Following:");
        lbFollowing.setBounds(10, 20, 410, 100);
        lmFollowerModel = new DefaultListModel<String>();
        for(User following : user.getFollowing()) {
            // Add each follower
            lmFollowerModel.addElement(following.toString());
        }
        JList<String> lFollowing = new JList<>(lmFollowerModel);
        lFollowing.setLayoutOrientation(JList.VERTICAL);
        JScrollPane spFollowing = new JScrollPane(lFollowing);  
        spFollowing.setBounds(10, 80, 410, 100);
        add(lbFollowing);
        add(spFollowing);

        // Add the button to post a message
        bPost = new JButton("Post Message");
        bPost.setBounds(220, 190, 200, 50);
        bPost.addActionListener(e -> postToFeed());
        fMessage = new JTextField();
        fMessage.setBounds(10, 190, 200, 50);
        add(bPost);
        add(fMessage);

        // Add the message feed list view
        lbFeed = new JLabel("Message feed:");
        lbFeed.setBounds(10, 200, 410, 100);
        lmFeedModel = new DefaultListModel<String>();
        for(String s : user.getMessageFeed()) {
            // Add the messages
            lmFeedModel.addElement(s);
        }
        JList<String> lFeed = new JList<>(lmFeedModel);
        lFollowing.setLayoutOrientation(JList.VERTICAL);
        JScrollPane spFeed = new JScrollPane(lFeed);  
        spFeed.setBounds(10, 260, 410, 100);
        add(lbFeed);
        add(spFeed);

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
                users.get(users.indexOf(user)).follow(users.get(i)); // Update the following list
                lmFollowerModel.addElement(users.get(i).toString());  // Add follower to list view
                users.get(i).addFollower(user);                      // Update the followers list of the user
            }
        }
    }

    /**
     * Posts a message and updates the feeds of users following the user
     * Takes the message string from the text field
     */
    private void postToFeed() {
        String message = fMessage.getText();
        message = user.toString() + ": " + message; // Add the user's name before the message

        if(!message.isEmpty()) {
            // Only post the message if it isn't empty
            user.addMessage(message); // Post message to the user's own feed
            for(User u : user.getFollowers()) {
                // Update the feeds of followers
                u.addMessage(message);
            }
        }
    }

    /**
     * Add a message to the user's feed and update it
     */
    public void addToFeed(String message) {
        lmFeedModel.addElement(message);
    }
}
