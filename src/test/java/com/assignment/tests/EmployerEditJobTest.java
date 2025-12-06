package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class EmployerEditJobTest extends BaseTest {

    @Test
    public void testEditJob() {
        driver.get("http://localhost:5173/login");
        
        // Login
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select")));
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
        
        // Edit job 
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Edit']")));
        WebElement editButton = driver.findElement(By.xpath("//button[text()='Edit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);
        
        // Wait for edit mode to activate
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".check_btn")));
        
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='text']")));
        WebElement titleInput = driver.findElement(By.cssSelector("input[type='text']"));
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", titleInput);
        titleInput.sendKeys("Updated Software Engineer Position");
        
        WebElement saveButton = driver.findElement(By.cssSelector(".check_btn"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveButton);
        
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".check_btn")));
    }
}