package com.mycompany.willow_link;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    // Test Case 1 from the PoE
    private final Message message1 = new Message(
            0,
            "+27718693002",
            "Hi Mike, can you join us for dinner tonight?"
    );

    // Test Case 2 from the PoE
    private final Message message2 = new Message(
            1,
            "08575975889",
            "Hi Keegan, did you receive the payment?"
    );

    @Test
    public void testMessageLengthSuccess() {

        assertEquals(
                "Message ready to send.",
                message1.checkMessageLength()
        );
    }

    @Test
    public void testMessageLengthFailure() {

        String longMessage = "A".repeat(260);

        Message message = new Message(
                2,
                "+27718693002",
                longMessage
        );

        assertEquals(
                "Message exceeds 250 characters by 10; please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testRecipientSuccess() {

        assertEquals(
                "Cell phone number successfully captured.",
                message1.checkRecipientCell()
        );
    }

    @Test
    public void testRecipientFailure() {

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain "
                + "an international code. Please correct the number and try again.",
                message2.checkRecipientCell()
        );
    }

    @Test
    public void testMessageIDLength() {

        assertTrue(message1.checkMessageID());

        assertEquals(
                10,
                message1.getMessageID().length()
        );
    }

    @Test
    public void testMessageHash() {

        String expectedStart =
                message1.getMessageID().substring(0, 2);

        assertEquals(
                expectedStart + ":0:HITONIGHT",
                message1.getMessageHash()
        );
    }

    @Test
    public void testSendMessage() {

        assertEquals(
                "Message successfully sent.",
                message1.SentMessage(1)
        );
    }

    @Test
    public void testDisregardMessage() {

        assertEquals(
                "Press 0 to delete the message.",
                message2.SentMessage(2)
        );
    }

    @Test
    public void testStoreMessage() {

        assertEquals(
                "Message successfully stored.",
                message1.SentMessage(3)
        );
    }

    @Test
    public void testPrintMessages() {

        String result = message1.printMessages();

        assertTrue(result.contains("Message ID:"));
        assertTrue(result.contains("Message Hash:"));
        assertTrue(result.contains("+27718693002"));
        assertTrue(result.contains(
                "Hi Mike, can you join us for dinner tonight?"
        ));
    }
// ==========================================================
// PART 3 UNIT TESTS
// ==========================================================

@Test
public void testDisplaySentMessages() {

    Message message = new Message(
            20,
            "+27834557896",
            "Did you get the cake?"
    );

    message.SentMessage(1);

    String result = Message.displaySentMessages();

    assertTrue(
            result.contains("Did you get the cake?")
    );
}


@Test
public void testLongestStoredMessage() {

    Message shortMessage = new Message(
            21,
            "+27838884567",
            "Ok, I am leaving without you."
    );

    Message longMessage = new Message(
            22,
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    shortMessage.SentMessage(3);
    longMessage.SentMessage(3);

    String result = Message.getLongestStoredMessage();

    assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            result
    );
}


@Test
public void testSearchByMessageID() {

    Message message = new Message(
            23,
            "+27834557896",
            "It is dinner time!"
    );

    message.SentMessage(1);

    String result = Message.searchByMessageID(
            message.getMessageID()
    );

    assertTrue(
            result.contains("It is dinner time!")
    );

    assertTrue(
            result.contains("+27834557896")
    );
}


@Test
public void testSearchByRecipient() {

    Message message1 = new Message(
            24,
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    Message message2 = new Message(
            25,
            "+27838884567",
            "Ok, I am leaving without you."
    );

    message1.SentMessage(3);
    message2.SentMessage(3);

    String result = Message.searchByRecipient(
            "+27838884567"
    );

    assertTrue(
            result.contains(
                    "Where are you? You are late! I have asked you to be on time."
            )
    );

    assertTrue(
            result.contains(
                    "Ok, I am leaving without you."
            )
    );
}


@Test
public void testDeleteByMessageHash() {

    Message message = new Message(
            26,
            "+27838884567",
            "Message to delete"
    );

    message.SentMessage(3);

    String result = Message.deleteByMessageHash(
            message.getMessageHash()
    );

    assertEquals(
            "Message successfully deleted.",
            result
    );
}


@Test
public void testStoredMessageReport() {

    Message message = new Message(
            27,
            "+27838884567",
            "Stored report message"
    );

    message.SentMessage(3);

    String result =
            Message.displayStoredMessageReport();

    assertTrue(result.contains("Message ID:"));
    assertTrue(result.contains("Message Hash:"));
    assertTrue(result.contains("+27838884567"));
    assertTrue(result.contains("Stored report message"));
}
}