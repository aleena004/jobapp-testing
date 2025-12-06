package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Alert;

public class EmployerDeleteJobTest extends BaseTest {

    @Test
    public void testDeleteJob() {
        driver.get("http://localhost:5173/login");
        
        // Login
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Employer']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("au@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        // Go to My Jobs page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        driver.get("http://localhost:5173/job/me");
        
        // Wait for jobs to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card")));
        
        // Click Delete button on first job
        driver.findElement(By.cssSelector(".delete_btn")).click();
        
        // Handle browser alert if present
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
        }
    }
}