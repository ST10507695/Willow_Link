package com.mycompany.willow_link;

import java.util.Scanner;

public class Willow_Link {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Willow Link heading
        System.out.println("====================================");
        System.out.println("            WILLOW LINK");
        System.out.println("====================================");
        System.out.println("      IT Communication System");
        System.out.println("  Connecting People & Technology");
        System.out.println("====================================");

        System.out.println();
        System.out.println("Welcome to Willow Link!");

        // =========================================
        // PART 1 - REGISTRATION
        // =========================================

        System.out.println();
        System.out.println("========== REGISTRATION ==========");

        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        System.out.print("Create a username: ");
        String username = input.nextLine();

        System.out.print("Create a password: ");
        String password = input.nextLine();

        System.out.print("Enter cellphone number (+27): ");
        String cellPhoneNumber = input.nextLine();

        // Create Login object
        Login user = new Login(
                firstName,
                lastName,
                username,
                password,
                cellPhoneNumber
        );

        System.out.println();

        // Check username
        if (user.checkUserName()) {

            System.out.println(
                    "Username successfully captured."
            );

        } else {

            System.out.println(
                    "Username is not correctly formatted; "
                    + "please ensure that your username contains "
                    + "an underscore and is no more than five "
                    + "characters in length."
            );
        }

        // Check password
        if (user.checkPasswordComplexity()) {

            System.out.println(
                    "Password successfully captured."
            );

        } else {

            System.out.println(
                    "Password is not correctly formatted; "
                    + "please ensure that the password contains "
                    + "at least eight characters, a capital letter, "
                    + "a number, and a special character."
            );
        }

        // Check cellphone number
        if (user.checkCellPhoneNumber()) {

            System.out.println(
                    "Cell phone number successfully added."
            );

        } else {

            System.out.println(
                    "Cell phone number incorrectly formatted "
                    + "or does not contain international code."
            );
        }

        // Display final registration result
        System.out.println();
        System.out.println(user.registerUser());

        // =========================================
        // LOGIN
        // =========================================

        if (user.checkUserName()
                && user.checkPasswordComplexity()
                && user.checkCellPhoneNumber()) {

            System.out.println();
            System.out.println("============== LOGIN ==============");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            System.out.println();

            System.out.println(
                    user.returnLoginStatus(
                            loginUsername,
                            loginPassword
                    )
            );

            // Continue only if login is successful
            if (user.loginUser(loginUsername, loginPassword)) {

                // =========================================
                // PART 2 - QUICKCHAT
                // =========================================

                System.out.println();
                System.out.println("Welcome to QuickChat.");

                System.out.print(
                        "How many messages would you like to enter? "
                );

                int numberOfMessages = input.nextInt();
                input.nextLine();

                int menuChoice = 0;

                // Keep menu running until user selects Quit
                while (menuChoice != 3) {

                    System.out.println();
                    System.out.println(
                            "========== QUICKCHAT MENU =========="
                    );
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Quit");
                    System.out.println("4. Stored Messages");

                    System.out.print("Enter your choice: ");
                    menuChoice = input.nextInt();
                    input.nextLine();

                    switch (menuChoice) {

                        case 1:

                            // Required FOR loop for message entry
                            for (int i = 0;
                                    i < numberOfMessages;
                                    i++) {

                                System.out.println();
                                System.out.println(
                                        "========== MESSAGE "
                                        + (i + 1)
                                        + " =========="
                                );

                                System.out.print(
                                        "Enter recipient cellphone "
                                        + "number (+27): "
                                );

                                String recipient =
                                        input.nextLine();

                                System.out.print(
                                        "Enter your message: "
                                );

                                String messageText =
                                        input.nextLine();

                                // Create Message object
                                Message message =
                                        new Message(
                                                i,
                                                recipient,
                                                messageText
                                        );

                                System.out.println();

                                // Display Message ID
                                System.out.println(
                                        "Message ID generated: "
                                        + message.getMessageID()
                                );

                                // Check Message ID
                                if (message.checkMessageID()) {

                                    System.out.println(
                                            "Message ID successfully "
                                            + "generated."
                                    );
                                }

                                // Check recipient
                                System.out.println(
                                        message.checkRecipientCell()
                                );

                                // Check message length
                                System.out.println(
                                        message.checkMessageLength()
                                );

                                // Display Message Hash
                                System.out.println(
                                        "Message Hash: "
                                        + message.getMessageHash()
                                );

                                // Ask what should happen
                                System.out.println();
                                System.out.println(
                                        "What would you like to do?"
                                );
                                System.out.println(
                                        "1. Send Message"
                                );
                                System.out.println(
                                        "2. Disregard Message"
                                );
                                System.out.println(
                                        "3. Store Message"
                                );

                                System.out.print(
                                        "Enter your choice: "
                                );

                                int messageChoice =
                                        input.nextInt();

                                input.nextLine();

                                // Display result
                                System.out.println(
                                        message.SentMessage(
                                                messageChoice
                                        )
                                );

                                // Print details if message was sent
                                if (messageChoice == 1) {

                                    System.out.println();
                                    System.out.println(
                                            "MESSAGE DETAILS"
                                    );

                                    System.out.println(
                                            message.printMessages()
                                    );
                                }
                            }

                            System.out.println();

                            System.out.println(
                                    "Total messages sent: "
                                    + Message.returnTotalMessagess()
                            );

                            break;

                        case 2:

                            System.out.println();
                            System.out.println("Coming Soon.");

                            break;

                        case 3:

                            System.out.println();
                            System.out.println(
                                    "Thank you for using Willow Link."
                            );

                            break;
                        case 4:

                            System.out.println();
                            System.out.println(
                                    "========== STORED MESSAGES =========="
                            );

                            System.out.println(
                                    "1. Display all stored messages"
                            );
                            System.out.println(
                                    "2. Display longest stored message"
                            );
                            System.out.println(
                                    "3. Search by Message ID"
                            );
                            System.out.println(
                                    "4. Search by recipient"
                            );
                            System.out.println(
                                    "5. Delete message by hash"
                            );
                            System.out.println(
                                    "6. Display stored message report"
                            );

                            System.out.print(
                                    "Enter your choice: "
                            );

                            int storedChoice = input.nextInt();
                            input.nextLine();

                            switch (storedChoice) {

                                case 1:
                                    System.out.println(
                                            Message.displayStoredMessages()
                                    );
                                    break;

                                case 2:
                                    System.out.println(
                                            "Longest stored message:"
                                    );
                                    System.out.println(
                                            Message.getLongestStoredMessage()
                                    );
                                    break;

                                case 3:
                                    System.out.print(
                                            "Enter Message ID: "
                                    );

                                    String searchID =
                                            input.nextLine();

                                    System.out.println(
                                            Message.searchByMessageID(
                                                    searchID
                                            )
                                    );
                                    break;

                                case 4:
                                    System.out.print(
                                            "Enter recipient cellphone number: "
                                    );

                                    String searchRecipient =
                                            input.nextLine();

                                    System.out.println(
                                            Message.searchByRecipient(
                                                    searchRecipient
                                            )
                                    );
                                    break;

                                case 5:
                                    System.out.print(
                                            "Enter Message Hash: "
                                    );

                                    String deleteHash =
                                            input.nextLine();

                                    System.out.println(
        Message.deleteByMessageHash(
                deleteHash
        )
);
                                    break;

                                case 6:
                                    System.out.println(
                                            Message.displayStoredMessageReport()
                                    );
                                    break;

                                default:
                                    System.out.println(
                                            "Invalid stored message option."
                                    );
                            }

                            break;
                            
                        default:

                            System.out.println();
                            System.out.println(
                                    "Invalid option. "
                                    + "Please select 1, 2, 3 or 4."
                            );
                    }
                }

            } else {

                System.out.println(
                        "Login unsuccessful. "
                        + "QuickChat cannot be opened."
                );
            }
        }

        input.close();
    }
}