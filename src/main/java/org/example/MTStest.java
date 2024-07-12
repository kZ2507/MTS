package org.example;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MTStest {
    public static void testing() {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        driver.manage().window();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
        WebDriverWait wait = new WebDriverWait(driver, 20);


        @Test
        String expectedTitle;
        expectedTitle = "Онлайн пополнение без комиссии";
        String actualText = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText().replaceAll("\\n", " ");
        Assertions.assertEquals(expectedTitle, actualText);


        @Test
        List<WebElement> logos = driver.findElements(By.xpath("//div[@class='pay__partners']/descendant::img"));
        for(int i = 0; i< ((List<?>) logos).size(); i++) {
            String url = logos.get(i).getAttribute("src");
        }


        @Test
        WebElement infoLink = driver.findElement(By.xpath("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"));
        infoLink.click();
        String expectredURL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectredURL);


        @Test
        WebElement buttonSubmit= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='pay-connection']")));
        WebElement inputPhone = driver.findElement(By.xpath("//input[@id='connection-phone']"));
        inputPhone.sendKeys("297777777");
        WebElement inputSum = driver.findElement(By.xpath("//input[@id='connection-sum']"));
        inputSum.sendKeys("1");
        buttonSubmit.click();
    }
}
