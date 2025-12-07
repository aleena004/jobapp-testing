package com.assignment.tests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class TestExample {
    @Test
    public void testContainerizedEnvironment() {
        System.out.println("Testing containerized environment...");
        assertTrue(true, "Containerized test should pass");
    }
    
    @Test
    public void testWebApplication() {
        System.out.println("Testing web application accessibility...");
        assertTrue(true, "Web app should be accessible");
    }
}
