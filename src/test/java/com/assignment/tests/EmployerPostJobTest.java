package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

public class EmployerPostJobTest extends BaseTest {

    @Test
    public void testEmployerPostJob() {
        driver.get("http://localhost:5173/login");
        
        // Login as employer
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("select")));
        driver.findElement(By.cssSelector("select")).click();
        driver.findElement(By.xpath("//option[text()='Employer']")).click();
        driver.findElement(By.cssSelector("input[placeholder='zk@gmail.com']")).sendKeys("au@gmail.com");
        driver.findElement(By.cssSelector("input[placeholder='Your Password']")).sendKeys("12345678");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        
        // Wait for login and navigate to post job page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".navbarShow")));
        driver.get("http://localhost:5173/job/post");
        
        // Wait for form to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Job Title']")));
        
        // Fill job form
        driver.findElement(By.cssSelector("input[placeholder='Job Title']")).sendKeys("Software Engineer");
        
        // Select category 
        WebElement categoryDropdown = driver.findElement(By.cssSelector("select"));
        Select categorySelect = new Select(categoryDropdown);
        categorySelect.selectByVisibleText("MERN STACK Development"); 
        
        driver.findElement(By.cssSelector("input[placeholder='Country']")).sendKeys("Pakistan");
        driver.findElement(By.cssSelector("input[placeholder='City']")).sendKeys("Lahore");
        
        WebElement locationField = driver.findElement(By.cssSelector("input[placeholder='Location']"));
        wait.until(ExpectedConditions.elementToBeClickable(locationField));
        locationField.sendKeys("DHA Phase 5");
        
        WebElement salaryTypeDropdown = null;
        try {
            salaryTypeDropdown = driver.findElement(By.xpath("//*[contains(text(),'Select Salary Type')]/following-sibling::select"));
        } catch (Exception e) {
            salaryTypeDropdown = driver.findElements(By.tagName("select")).get(1);
        }
        
        Select salaryTypeSelect = new Select(salaryTypeDropdown);
        salaryTypeSelect.selectByVisibleText("Fixed Salary");
        
        WebElement salaryInput = null;
        try {
            salaryInput = driver.findElement(By.cssSelector("input[placeholder='Enter Fixed Salary']"));
        } catch (Exception e) {
            salaryInput = driver.findElement(By.cssSelector("input[type='number']"));
        }
        
        salaryInput.sendKeys("50000");
        
        driver.findElement(By.cssSelector("textarea[placeholder='Job Description']")).sendKeys("Looking for experienced MERN developer...");
        
        // Submit form
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Create Job']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}