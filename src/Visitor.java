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

    private final String positiveWords[] =
        {"good", "great"};

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

    public static Visitor getInstance() {
        if(visitor == null) {
            visitor = new Visitor();
        }
        return visitor;
    }
}
