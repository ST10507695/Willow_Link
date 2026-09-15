package com.mycompany.willow_link;

public class Login {

    // Variables to store the user's registration information
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhoneNumber;

    // Constructor
    public Login(String firstName, String lastName,
                 String username, String password,
                 String cellPhoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Checks if the username contains an underscore
// and is no more than 5 characters long
public boolean checkUserName() {

    return username.contains("_")
            && username.length() <= 5;
}

// Checks that the password has at least 8 characters,
// one capital letter, one number and one special character
public boolean checkPasswordComplexity() {

    if (password.length() < 8) {
        return false;
    }

    boolean hasCapital = false;
    boolean hasNumber = false;
    boolean hasSpecialCharacter = false;

    for (char character : password.toCharArray()) {

        if (Character.isUpperCase(character)) {
            hasCapital = true;
        }

        if (Character.isDigit(character)) {
            hasNumber = true;
        }

        if (!Character.isLetterOrDigit(character)) {
            hasSpecialCharacter = true;
        }
    }

         return hasCapital && hasNumber && hasSpecialCharacter;
    }

    // Checks if the cellphone number uses the
    // South African international code +27
    public boolean checkCellPhoneNumber() {

        return cellPhoneNumber != null
                && cellPhoneNumber.matches("^\\+27\\d{9}$");
    }
// Registers the user after checking all registration details
public String registerUser() {

    if (!checkUserName()) {
        return "Username is not correctly formatted; please ensure that "
                + "your username contains an underscore and is no more "
                + "than five characters in length.";
    }

    if (!checkPasswordComplexity()) {
        return "Password is not correctly formatted; please ensure that "
                + "the password contains at least eight characters, "
                + "a capital letter, a number, and a special character.";
    }

    if (!checkCellPhoneNumber()) {
        return "Cell phone number incorrectly formatted or does not "
                + "contain international code.";
    }

    return "User registered successfully.";
}
// Checks if the entered username and password
// match the registered username and password
public boolean loginUser(String enteredUsername,
                         String enteredPassword) {

    return username.equals(enteredUsername)
            && password.equals(enteredPassword);
}

// Returns the correct login message
public String returnLoginStatus(String enteredUsername,
                                String enteredPassword) {

    if (loginUser(enteredUsername, enteredPassword)) {

        return "Welcome " + firstName + ", "
                + lastName
                + " it is great to see you again.";
    }

    return "Username or password incorrect, please try again.";
}
}