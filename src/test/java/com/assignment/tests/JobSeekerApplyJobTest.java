/*package com.assignment.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.jupiter.api.Assertions.*;

public class JobSeekerApplyJobTest extends BaseTest {

    @Test
    public void testApplyForJob() {
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
        
        driver.get("http://localhost:5173/job/getall");
        
        // Wait for jobs to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".banner")));
        
        WebElement jobLink = null;
        try {
            jobLink = driver.findElement(By.xpath("//a[contains(text(),'Job Details')]"));
        } catch (Exception e) {
            try {
                jobLink = driver.findElement(By.cssSelector(".banner a"));
            } catch (Exception e2) {
                jobLink = driver.findElement(By.cssSelector("a[href*='/job/']"));
            }
        }
        
        // Get the job URL and navigate to it
        String jobUrl = jobLink.getAttribute("href");
        driver.get(jobUrl);
        
        // Wait for job details page to load
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".apply-btn")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Apply Now')]")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Apply')]")),
            ExpectedConditions.presenceOfElementLocated(By.cssSelector(".jobDetail"))
        ));
        
        // Apply for job
        WebElement applyButton = null;
        try {
            applyButton = driver.findElement(By.cssSelector(".apply-btn"));
        } catch (Exception e1) {
            try {
                applyButton = driver.findElement(By.xpath("//a[contains(text(),'Apply Now')]"));
            } catch (Exception e2) {
                applyButton = driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));
            }
        }
        assertTrue(applyButton.isDisplayed(), "Apply button is not displayed");
        
        // Click apply button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", applyButton);
        wait.until(ExpectedConditions.elementToBeClickable(applyButton));
        applyButton.click();
        
        // Verify we're redirected to application form
        wait.until(ExpectedConditions.or(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Your Name']")),
            ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Application Form')]")),
            ExpectedConditions.urlContains("/application/")
        ));
    }
}*/