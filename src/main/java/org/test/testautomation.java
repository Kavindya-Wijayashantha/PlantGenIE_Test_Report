package org.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class testautomation
{
    WebDriver driver;

    @Test
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();

        String baseUrl = "https://plantgenie.org";
        driver.get(baseUrl);

        WebElement agreeButton = driver.findElement(By.cssSelector(".hi-cookie-btn.agree"));

        // Click the 'Agree' button
        agreeButton.click();

        // Verify that the cookie consent popup is no longer displayed
        boolean isPopupDisplayed = driver.findElements(By.id("hi-cookie-box")).size() > 0;
        assert !isPopupDisplayed : "Cookie consent popup is still displayed!";


    }

    @Test
    public void verifySearchBar() {
        WebElement searchBar = driver.findElement(By.id("mainsearch")); // Replace with actual ID
        searchBar.sendKeys("Eucgr.A00001");

    }

    @Test
    public void verifyMobileView() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=375,812"); // iPhone X resolution
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://plantgenie.org");
        WebElement logo = driver.findElement(By.id("logo_img"));
        WebElement searchBar = driver.findElement(By.id("mainsearch"));

        int logoWidth = logo.getSize().getWidth();
        int searchBarWidth = searchBar.getSize().getWidth();

        Assert.assertTrue(logoWidth + searchBarWidth < 375, "Logo and search bar overlap in mobile view.");
    }

    @Test
    public void verifyBrokenLink() {
        WebElement link = driver.findElement(By.linkText("Click here to learn how to"));
        Assert.assertTrue(link.isDisplayed() && link.isEnabled(), "Link is not clickable.");
        link.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(currentUrl, "https://plantgenie.org", "Link did not navigate.");
    }








}
