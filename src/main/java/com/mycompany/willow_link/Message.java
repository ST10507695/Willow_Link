package com.mycompany.willow_link;

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

    if (recipient != null && recipient.matches("^\\+27\\d{9}$")) {
        return "Cell phone number successfully captured.";
    }

    return "Cell phone number is incorrectly formatted or does not contain "
            + "an international code. Please correct the number and try again.";
}


// Checks whether the message is no more than 250 characters
public String checkMessageLength() {

    if (messageText.length() <= 250) {
        return "Message ready to send.";
    }

    int extraCharacters = messageText.length() - 250;

    return "Message exceeds 250 characters by "
            + extraCharacters
            + "; please reduce the size.";
}
public String SentMessage(int option) {

    switch (option) {
        case 1 -> {
            totalMessagesSent++;
            return "Message successfully sent.";
            }

        case 2 -> {
            return "Press 0 to delete the message.";
            }

        case 3 -> {
            return "Message successfully stored.";
            }

        default -> {
            return "Invalid option.";
            }
    }
}

// Returns all the details of the message
public String printMessages() {

    return "Message ID: " + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + messageText;
}

// Returns the total number of messages successfully sent
public static int returnTotalMessagess() {
    return totalMessagesSent;
}

} // End of Message class