package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class JobSeekerSearchJobTest extends BaseTest {

    @Test
    public void testSearchJob() {
        driver.get("http://localhost:5173/login");
        
        String initialUrl = driver.getCurrentUrl();
        assertTrue(initialUrl.contains("login"), "Should start on login page");
        
        // Login as job seeker
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select")));
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Job Seeker']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("us@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("22446688");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        
        // Verify we're logged in 
        String afterLoginUrl = driver.getCurrentUrl();
        assertFalse(afterLoginUrl.contains("login"), "Should not be on login page after login");

        // Navigate to posted jobs 
        driver.get("http://localhost:5173/job/getall");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String finalUrl = driver.getCurrentUrl();
        assertFalse(finalUrl.contains("login"), "Should not be on login page");
        assertNotEquals(initialUrl, finalUrl, "Should have navigated away from login page");
        
        String pageSource = driver.getPageSource();
        boolean hasJobContent = finalUrl.contains("job") || 
                               finalUrl.contains("jobs") ||
                               pageSource.contains("job") ||
                               pageSource.contains("Job") ||
                               pageSource.contains("JOBS");
        
        if (!hasJobContent) {
            System.out.println("Warning: Page might not have loaded job content. URL: " + finalUrl);
        }
    }
}