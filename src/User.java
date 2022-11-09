/**
 * User
 * Contains a reference to its User View
 * Uses the Visitor pattern.
 */

import java.util.ArrayList;

public class User {
    private String id;
    private UserView userView = null;
    private ArrayList<User> followers;
    private ArrayList<User> following;
    private ArrayList<String> messageFeed;

    // Initialize a user with its unique ID
    public User(String id) {
        followers = new ArrayList<>();
        following = new ArrayList<>();
        messageFeed = new ArrayList<>();
        this.id = id;
    }

    // Opens the user view
    public void openUserView() {
        userView = new UserView(this);
    }

    // Add a follower
    public void addFollower(User u) {
        followers.add(u);
    }

    // Follow a user
    public void follow(User u) {
        following.add(u);
    }

    // Add a message to the feed
    public void addMessage(String s) {
        messageFeed.add(s);
        if(userView != null) {
            // Update the user view if it's initialized
            userView.addToFeed(s);
        }
    }

    public String getID() {
        return id;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public ArrayList<String> getMessageFeed() {
        return messageFeed;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof User) && (this.id.equals(o.toString()));
    }

    public String toString() {
        return getID();
    }
}