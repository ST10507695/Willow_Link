package com.mycompany.willow_link;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class Message {

    // Variables used to store message information
    private final String messageID;
    private final int messageNumber;
    private final String recipient;
    private final String messageText;
    private final String messageHash;

    // Keeps track of successfully sent messages
    private static int totalMessagesSent = 0;

    // Part 3 arrays used to manage message data
    private static final ArrayList<Message> sentMessages
            = new ArrayList<>();

    private static final ArrayList<Message> disregardedMessages
            = new ArrayList<>();

    private static final ArrayList<Message> storedMessages
            = new ArrayList<>();

    private static final ArrayList<String> messageHashes
            = new ArrayList<>();

    private static final ArrayList<String> messageIDs
            = new ArrayList<>();

    // Name of the JSON file
    private static final String JSON_FILE
            = "stored_messages.json";

    // Constructor
    public Message(int messageNumber, String recipient, String messageText) {

        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;

        // Generate a random 10-digit Message ID
        this.messageID = generateMessageID();

        // Create the message hash
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit Message ID
    private String generateMessageID() {

        long randomID = ThreadLocalRandom.current().nextLong(
                1000000000L,
                10000000000L
        );

        return String.valueOf(randomID);
    }

    // Checks that the Message ID contains 10 digits
    public boolean checkMessageID() {

        return messageID != null
                && messageID.matches("\\d{10}");
    }

    // Creates the Message Hash
    public String createMessageHash() {

        if (messageText == null || messageText.trim().isEmpty()) {
            return "";
        }

        String[] words = messageText.trim().split("\\s+");

        String firstWord = words[0]
                .replaceAll("[^a-zA-Z0-9]", "");

        String lastWord = words[words.length - 1]
                .replaceAll("[^a-zA-Z0-9]", "");

        return (
                messageID.substring(0, 2)
                + ":"
                + messageNumber
                + ":"
                + firstWord
                + lastWord
                ).toUpperCase();
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    // Checks whether the recipient cellphone number is correctly formatted
    public String checkRecipientCell() {

        if (recipient != null
                && recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.";
    }

    // Checks whether the message is no more than 250 characters
    public String checkMessageLength() {

        if (messageText != null && messageText.length() <= 250) {
            return "Message ready to send.";
        }

        int extraCharacters = messageText.length() - 250;

        return "Message exceeds 250 characters by "
                + extraCharacters
                + "; please reduce the size.";
    }

    // Allows the user to send, disregard or store a message
    public String SentMessage(int option) {

        switch (option) {

            case 1 -> {

                sentMessages.add(this);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);

                totalMessagesSent++;

                return "Message successfully sent.";
            }

            case 2 -> {

                disregardedMessages.add(this);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);

                return "Press 0 to delete the message.";
            }

            case 3 -> {

                // storeMessage adds the message to storedMessages
                // and saves the array to the JSON file.
                storeMessage();

                messageHashes.add(messageHash);
                messageIDs.add(messageID);

                return "Message successfully stored.";
            }

            default -> {
                return "Invalid option.";
            }
        }
    }

    // Stores the current message in the JSON file
    public void storeMessage() {

        storedMessages.add(this);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try (FileWriter writer = new FileWriter(JSON_FILE)) {

            gson.toJson(storedMessages, writer);

        } catch (IOException e) {

            System.out.println(
                    "Error storing message: "
                    + e.getMessage()
            );
        }
    }

    // Returns all the details of the message
    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }

    // Returns the total number of successfully sent messages
    public static int returnTotalMessagess() {
        return totalMessagesSent;
    }

    // Returns the sent messages array
    public static ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

    // Returns the disregarded messages array
    public static ArrayList<Message> getDisregardedMessages() {
        return disregardedMessages;
    }

    // Returns the stored messages array
    public static ArrayList<Message> getStoredMessages() {
        return storedMessages;
    }

    // Returns the message hashes array
    public static ArrayList<String> getMessageHashes() {
        return messageHashes;
    }

    // Returns the message IDs array
    public static ArrayList<String> getMessageIDs() {
        return messageIDs;
    }
// ==========================================================
// PART 3 - MESSAGE MANAGEMENT METHODS
// ==========================================================

// Displays all successfully sent messages
public static String displaySentMessages() {

    if (sentMessages.isEmpty()) {
        return "No sent messages available.";
    }

    StringBuilder result = new StringBuilder();

    for (Message message : sentMessages) {
        result.append(message.getMessageText())
              .append("\n");
    }

    return result.toString();
}


// Finds the longest stored message
public static String getLongestStoredMessage() {

    if (storedMessages.isEmpty()) {
        return "No stored messages available.";
    }

    Message longestMessage = storedMessages.get(0);

    for (Message message : storedMessages) {

        if (message.getMessageText().length()
                > longestMessage.getMessageText().length()) {

            longestMessage = message;
        }
    }

    return longestMessage.getMessageText();
}


// Searches for a message using the Message ID
public static String searchByMessageID(String searchID) {

    // Search sent messages
    for (Message message : sentMessages) {

        if (message.getMessageID().equals(searchID)) {

            return "Recipient: "
                    + message.getRecipient()
                    + "\nMessage: "
                    + message.getMessageText();
        }
    }

    // Search stored messages
    for (Message message : storedMessages) {

        if (message.getMessageID().equals(searchID)) {

            return "Recipient: "
                    + message.getRecipient()
                    + "\nMessage: "
                    + message.getMessageText();
        }
    }

    return "Message ID not found.";
}


// Searches stored messages using the recipient cellphone number
public static String searchByRecipient(String searchRecipient) {

    StringBuilder result = new StringBuilder();

    for (Message message : storedMessages) {

        if (message.getRecipient().equals(searchRecipient)) {

            result.append(message.getMessageText())
                  .append("\n");
        }
    }

    if (result.length() == 0) {
        return "No messages found for this recipient.";
    }

    return result.toString();
}


// Deletes a stored message using its Message Hash
public static String deleteByMessageHash(String searchHash) {

    for (int i = 0; i < storedMessages.size(); i++) {

        Message message = storedMessages.get(i);

        if (message.getMessageHash().equals(searchHash)) {

            storedMessages.remove(i);

            messageHashes.remove(message.getMessageHash());
            messageIDs.remove(message.getMessageID());

            // Update the JSON file after deletion
            saveStoredMessagesToJSON();

            return "Message successfully deleted.";
        }
    }

    return "Message Hash not found.";
}


// Creates a complete report of stored messages
public static String displayStoredMessageReport() {

    if (storedMessages.isEmpty()) {
        return "No stored messages available.";
    }

    StringBuilder report = new StringBuilder();

    report.append("========== STORED MESSAGE REPORT ==========\n");

    for (Message message : storedMessages) {

        report.append("Message ID: ")
              .append(message.getMessageID())
              .append("\n");

        report.append("Message Hash: ")
              .append(message.getMessageHash())
              .append("\n");

        report.append("Recipient: ")
              .append(message.getRecipient())
              .append("\n");

        report.append("Message: ")
              .append(message.getMessageText())
              .append("\n");

        report.append("-------------------------------------------\n");
    }

    return report.toString();
}


// Saves the current stored message list back to the JSON file
private static void saveStoredMessagesToJSON() {

    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    try (FileWriter writer = new FileWriter(JSON_FILE)) {

        gson.toJson(storedMessages, writer);

    } catch (IOException e) {

        System.out.println(
                "Error updating stored messages: "
                + e.getMessage()
        );
    }
}
} // End of Message class