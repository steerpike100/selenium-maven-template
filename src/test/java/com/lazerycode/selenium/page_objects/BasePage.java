package com.lazerycode.selenium.page_objects;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;

    @Before
    public void setUp(){
        final String CHROMEPATH =  System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", CHROMEPATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.webdriveruniversity.com/");
        WebElement dropDownCheckBoxRadioLink = driver.findElement(By.xpath("//h4[contains(text(),'The choice is yours!')]"));
        waitForIsDisplayed(dropDownCheckBoxRadioLink, 10);
        dropDownCheckBoxRadioLink.click();
    }

    @After
    public void tearDown(){
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }

    /****************************************
     * WAIT METHODS
     ****************************************/

    protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeout){
        timeout = timeout !=null ? timeout:5;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(condition);
    }

    public Boolean waitForIsDisplayed(WebElement element, Integer... timeout){
        try{
            waitFor(ExpectedConditions.visibilityOf(element), (timeout.length >0 ? timeout[0]:null));
        }catch(TimeoutException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
