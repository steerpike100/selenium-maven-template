package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.page_objects.BasePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SampleTests extends BasePage {

    @Test
    public void checkBoxTest() {

        for (String winHandles : driver.getWindowHandles()) {
            driver.switchTo().window(winHandles);
        }

        List<WebElement> allCheckbox = driver.findElements(By.xpath("//input[@type='checkbox']"));

        for (WebElement e : allCheckbox) {
            if (e.isSelected()) {
                e.click();
            }
        }

        WebElement checkBoxThree = driver.findElement(By.xpath("//input[@value='option-3']"));
        checkBoxThree.click();

        assertTrue(checkBoxThree.isSelected());

    }


    @Test
    public void selectYellowRadioButton() {
        for(String winHandles: driver.getWindowHandles()){
            driver.switchTo().window(winHandles);
        }

        List<WebElement> allRadioButtons = driver.findElements(By.cssSelector("#radio-buttons"));

        for(WebElement e : allRadioButtons){
            if(e.isSelected()){
                e.click();
            }
        }

        WebElement yellowRadioButton = driver.findElement(By.xpath("//input[@value='yellow']"));
        yellowRadioButton.click();

        assertTrue(yellowRadioButton.isSelected());


    }

    @Test
    public void selectMavenFromDropDown(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebElement secondDropDown = driver.findElement(By.xpath("//select[@id='dropdowm-menu-2']"));
        WebElement mavenOption = secondDropDown.findElement(By.xpath("//option[@value='maven']"));
        mavenOption.click();

        customWait();

        assertEquals("Maven", mavenOption.getText());

    }

    public void customWait(){
        new WebDriverWait(driver, 10).until(
                ExpectedConditions.elementToBeSelected(By.xpath("//option[@value='maven']"))
        );
    }
}