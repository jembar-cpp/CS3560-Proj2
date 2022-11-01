# Project 2 - Mini Twitter

Java-based Mini Twitter with GUI using Java Swing.

## GUI
The User Interface contains the following:  

### Main view
Tree: shows all the users and groups in a nested structure. Uses the **composite** pattern.  
Add user using a user ID, or add a group using a group ID. The user/group is added using insert mode (after the currently selected user), or root if none are selected.  
Open the currently selected user in user view. Only works if a user is selected.  
Show total users, total groups, total messages, and total positive messages using the **visitor** pattern.

### User view
User view opens a new window.  
Displays the users the user is currently following.  
Add user to follow using the user's ID.  
Shows the news feed, latest messages from followed users and the user itself, using the **observer** pattern.  
Post a new message, updating the relevant news feeds.

## Program components

### Driver
Driver controls the admin panel and initializes the GUI.  
Driver contains the main() function and is the entry point for the program.  
The admin panel contains functions to create users and user groups.  
Only one admin panel can exist at a time, using the **singleton** pattern.  

### User
A user has a unique ID, a list of followers (user IDs), a list of users that they're following, and a news feed list which contains a list of messages from followed users.  
A user can follow another user with the user ID.  
Users can post messages (strings).  

### User group
A user group has a unique ID, a list of user IDs, and a list of group IDs.  