/**
 * TreeView
 * Displays all the users and groups as a tree.
 * Implements the Singleton pattern and the Composite pattern.
 */

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class TreeView extends JPanel implements TreeSelectionListener {
    private static TreeView instance = null;
    private JTree tree;
    private DefaultMutableTreeNode lastSelected = null;

    private TreeView() { 
        // Initialize the tree with root and a default user
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        root.add(new DefaultMutableTreeNode(new User("User1")));
        tree = new JTree(root);

        // Make the tree single-selection mode and listen for selection
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(this);
        tree.setCellRenderer(new DefaultTreeCellRenderer());

        // Add the scrollable tree
        add(new JScrollPane(tree));
    }

    // Listen for selection
    public void valueChanged(TreeSelectionEvent e) {
        // Get the last selected user
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (node == null) {
            lastSelected = null;
            return;
        }
        // Update the last selected object
        lastSelected = node;
    }

    // Insert a new node after the selected node
    public void addNode(Object node) {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode insertAfter = (DefaultMutableTreeNode) lastSelected;
        if(insertAfter == null) {
            insertAfter = (DefaultMutableTreeNode) model.getRoot();
        }
        else {
            if(insertAfter.getUserObject() instanceof User) {
                // User selected: add node to the parent of the user
                insertAfter = (DefaultMutableTreeNode) insertAfter.getParent();
            }
        }
        if(node instanceof Group) {
            // Inserting a group: make the text bold
            node = new Group("<html><b>"+node.toString()+"</b></html>");
        }
        model.insertNodeInto(new DefaultMutableTreeNode(node), insertAfter, insertAfter.getChildCount());
    }

    // Returns the last selected node
    public DefaultMutableTreeNode getLastSelected() {
        return lastSelected;
    }

    // Return the single instance of TreeView
    public static TreeView getInstance() {
        if(instance == null) {
            instance = new TreeView();
        }
        return instance;
    }
}
