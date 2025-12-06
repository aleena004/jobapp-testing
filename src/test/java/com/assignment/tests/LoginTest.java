package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    @Test
    public void testLogin() {
        driver.get("http://localhost:5173/login");
        
        // Select role
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Employer']")).click();
        
        // Fill email and password 
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("au@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("12345678");
        
        // Click login button
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        assertTrue(driver.getCurrentUrl().contains("/") && !driver.getCurrentUrl().contains("login"));
    }
}