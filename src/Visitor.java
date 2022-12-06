import java.util.ArrayList;

/**
 * Generic visitor
 * Keeps track of user count, group count, total messages, and positive message percentage
 * Only one type of visitor is needed, so it does not implement an abstract visitor type.
 * Implements the Visitor and Singleton patterns.
 */

public class Visitor {
    private int userTotal;
    private int groupTotal;
    private int messageTotal;
    private int positiveMessageTotal;

    private final String positiveWords[] = {
        "good", "great", "fun", "happy", "positive",
        "fantastic", "excellent", "joy", "funny", "nice",
        "perfect", "wonderful", "exciting", "successful", "CS3560"
    };

    private static Visitor visitor = null;

    private Visitor() {
        // Default initialized values
        userTotal = 1;
        groupTotal = 0;
        messageTotal = 0;
        positiveMessageTotal = 0;
    }

    public void atUser(User u) {
        userTotal++;
    }

    public void atGroup(Group g) {
        groupTotal++;
    }

    // A message is just a generic string.
    public void atMessage(String message) {
        messageTotal++;

        // Check whether the message is positive or not
        for(String word : positiveWords) {
            if(message.toLowerCase().contains(word)) {
                positiveMessageTotal++;
            }
        }
    }

    public int getUserTotal() {
        return userTotal;
    }

    public int getGroupTotal() {
        return groupTotal;
    }

    public int getMessageTotal() {
        return messageTotal;
    }

    public double getPositiveMessagePercentage() {
        return messageTotal == 0 ? 0 : positiveMessageTotal * 100.0 / messageTotal;
    }

    // Validates user and group IDs
    public boolean validateIDs() {
        ArrayList<User> users = AdminPanel.getUsers();
        ArrayList<Group> groups = AdminPanel.getGroups();

        // Check for duplicate user IDs
        for(int i = 0; i < users.size(); i++) { // O(n^2)
            for(int j = 0; j < users.size(); j++) {
                if(i == j) {continue;} // skip same element
                if(users.get(i) == users.get(j)) {
                    return false; // duplicate
                }
            }
        }

        // Check for duplicate group IDs
        for(int i = 0; i < groups.size(); i++) {
            for(int j = 0; j < groups.size(); j++) {
                if(i == j) {continue;} // skip same element
                if(groups.get(i) == groups.get(j)) {
                    return false; // duplicate
                }
            }
        }

        return true;
    }

    /**
     * Returns the last updated user
     * In the case of duplicate last updated users, returns the one which was created earliest
     */
    public String getLastUpdatedUser() {
        long max = 0;
        User lastUpdatedUser = null;
        for(User u : AdminPanel.getUsers()) {
            if(u.getUpdateTime() > max) {
                max = u.getUpdateTime();
                lastUpdatedUser = u;
            }
        }
        if(lastUpdatedUser != null) {
            return lastUpdatedUser.toString();
        }
        return "No users found";
    }

    public static Visitor getInstance() {
        if(visitor == null) {
            visitor = new Visitor();
        }
        return visitor;
    }
}
