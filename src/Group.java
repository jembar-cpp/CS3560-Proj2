/**
 * Group
 */

import java.util.ArrayList;

public class Group {
    private String id;
    private ArrayList<User> users; // Users in the group

    // Initialize a group with its unique ID
    public Group(String id) {
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String toString() {
        return getID();
    }
}