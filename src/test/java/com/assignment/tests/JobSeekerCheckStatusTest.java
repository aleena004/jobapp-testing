package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class JobSeekerCheckStatusTest extends BaseTest {

    @Test
    public void testApplicationStatus() {
        driver.get("http://localhost:5173/login");
        
        // Login as job seeker
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select")));
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Job Seeker']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("us@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("22446688");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        // Wait for login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        
        // Go to applications page
        driver.get("http://localhost:5173/applications/me");
        
        // Check My Applications (for job seeker)
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'My Applications')]")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Applications')]")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[contains(text(),'No Applications')]")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".my_applications")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".container"))
        ));
        
        // Check if page loaded successfully
        String pageSource = driver.getPageSource();
        boolean pageLoaded = pageSource.contains("Applications") || 
                           pageSource.contains("applications") ||
                           pageSource.contains("No Applications") ||
                           driver.findElements(By.cssSelector(".container")).size() > 0;
        
        assertTrue(pageLoaded, "Applications page did not load properly");
        
        assertTrue(driver.getCurrentUrl().contains("applications"), 
                  "Not on applications page. URL: " + driver.getCurrentUrl());
    }
}