package com.napier.semCW;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
    }

    @Test
    public void testConnect() {
        try {
            // Attempt to connect
            app.connect();
            // After connection, the connection object should not be null
            assertNotNull(app.con, "Connection successful");
        } catch (Exception e) {
            fail("Connection to database failed: " + e.getMessage());
        }
    }

    @Test
    public void testAlwaysPasses() {
        // This test will always pass since it's checking a simple true condition
        assertTrue(true, "This condition is guaranteed to be true");
    }
}
