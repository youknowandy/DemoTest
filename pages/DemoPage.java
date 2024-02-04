package pages;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DemoPage {
  private WebDriver driver;
  
  // Element locators
  private By textInputField = By.id("myTextInput");
  private By svgRect = By.id("svgRect");
  private By checkBoxFrame = By.id("myFrame3"); // since this is a simple frame, not giving it its own methods
  private By checkBoxInFrame = By.id("checkBox6"); // currently within checkBoxFrame, it could be moved outside of this frame in future
  private By selectDropdown = By.id("mySelect");
  private By htmlMeter = By.id("meterBar");
  // TO DO: Add element locators for the rest of the page elements
  
  public DemoPage(WebDriver driver) {
    this.driver = driver;
  }
  
  // Methods for interacting with elements
  public void enterTextInputField(String value) {
    driver.findElement(textInputField).sendKeys(value);
  }
  
  // gets the value of the text input field
  public String getTextInputField() {
    return driver.findElement(textInputField).getAttribute("value");
  }

  // gets the color of the HTML SVG with rectangle
  public String getSvgRectColor() {
    return driver.findElement(svgRect).getAttribute("fill");
  }

  // clicks the check box that is inside an iFrame  
  public void clickCheckBoxInFrame() {
    driver.switchTo().frame(driver.findElement(checkBoxFrame));
    driver.findElement(checkBoxInFrame).click();
    driver.switchTo().defaultContent();
  }
  
  // gets the value of the check box that is inside an iFrame
  public String getCheckBoxInFrameValue() {
    driver.switchTo().frame(driver.findElement(checkBoxFrame));
    String value = driver.findElement(checkBoxInFrame).getAttribute("value");
    driver.switchTo().defaultContent();
    return value;
  }
  
  // selects a percentage value from the Dropdown box
  // Actual pulldown box options are in the form of "Set to <Percent>%"
  // Valid Percent values: 25, 50, 75, 100
  public void selectDropdownPercentage(int Percent) {
    WebElement dropdown = driver.findElement(selectDropdown);
    String option = "Set to " + String.valueOf(Percent) + "%";
    try {
      dropdown.findElement(By.xpath("//option[. = '" + option + "']")).click();
    } catch (NoSuchElementException e) {
      System.out.println(Integer.toString(Percent) + " is an invalid percent. Valid percents are 25, 50, 75, and 100.");
    }
  }
  
  // gets the percentage value of the HMTL Meter
  public int getHtmlMeterPercentage() {
    String attribute = driver.findElement(htmlMeter).getAttribute("value");
    double numericValue = Double.parseDouble(attribute);
    return (int) Math.round(numericValue * 100);
  }
}