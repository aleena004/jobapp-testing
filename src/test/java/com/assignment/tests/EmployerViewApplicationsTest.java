package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class EmployerViewApplicationsTest extends BaseTest {

    @Test
    public void testViewApplications() {
        driver.get("http://localhost:5173/login");
        
        // Login as employer
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Employer']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("au@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        // Navigate to applications page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        driver.get("http://localhost:5173/applications/me");
        
        // Check if applications page loads
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Applications')]")));
        assertTrue(driver.getPageSource().contains("Applications From Job Seekers") || 
                  driver.getPageSource().contains("No Applications Found"));
    }
}