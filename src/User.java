/**
 * User
 */

import java.util.ArrayList;

public class User {
    private String id;
    private ArrayList<String> followers;
    private ArrayList<String> following;

    // Initialize a user with its unique ID
    public User(String id) {
        followers = new ArrayList<>();
        following = new ArrayList<>();
        this.id = id;
    }

    // Add a follower
    public void addFollower(Object o) {
        followers.add(o.toString());
    }

    // Follow a user
    public void follow(Object o) {
        following.add(o.toString());
    }

    public String getID() {
        return id;
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof User) && (this.id.equals(o.toString()));
    }

    public String toString() {
        return getID();
    }
}