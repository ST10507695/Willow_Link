package com.mycompany.willow_link;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @Test
    public void testValidUsername() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertTrue(user.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        Login user = new Login(
                "Kyle", "Smith", "kyle!!!!!!!",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertFalse(user.checkUserName());
    }

    @Test
    public void testValidPassword() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "password", "+27838968976"
        );

        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testValidCellPhoneNumber() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "08966553"
        );

        assertFalse(user.checkCellPhoneNumber());
    }

    @Test
    public void testSuccessfulLogin() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertTrue(
                user.loginUser("kyl_1", "Ch&&sec@ke99!")
        );
    }

    @Test
    public void testFailedLogin() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertFalse(
                user.loginUser("kyle!!!!!!!", "password")
        );
    }

    @Test
    public void testSuccessfulLoginMessage() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertEquals(
                "Welcome Kyle, Smith it is great to see you again.",
                user.returnLoginStatus(
                        "kyl_1",
                        "Ch&&sec@ke99!"
                )
        );
    }

    @Test
    public void testFailedLoginMessage() {
        Login user = new Login(
                "Kyle", "Smith", "kyl_1",
                "Ch&&sec@ke99!", "+27838968976"
        );

        assertEquals(
                "Username or password incorrect, please try again.",
                user.returnLoginStatus(
                        "wrong",
                        "password"
                )
        );
    }
}