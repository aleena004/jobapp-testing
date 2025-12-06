package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class UIElementsTest extends BaseTest {

    @Test
    public void testHomeUIElements() {
        driver.get("http://localhost:5173/login");
        
        // Login first (home page requires authentication)
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Job Seeker']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("us@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("22446688");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        // Verify homepage elements
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".heroSection")));
        
        // Check hero section
        assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Find a job that suits')]")).isDisplayed());
        
        // Check stats cards exist
        assertTrue(driver.findElements(By.cssSelector(".details .card")).size() > 0);
        
        // Check How It Works section
        assertTrue(driver.getPageSource().contains("How JobZee Works"));
        
        // Check Popular Categories section
        assertTrue(driver.getPageSource().contains("POPULAR CATEGORIES"));
        
        // Check navigation is visible
        assertTrue(driver.findElement(By.cssSelector(".navbarShow")).isDisplayed());
    }
}