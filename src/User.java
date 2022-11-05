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
        this.id = id;
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

    public String toString() {
        return getID();
    }
}