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
  private By textAreaField = By.id("myTextarea");
  private By PreFilledTextField = By.id("myTextInput2");
  private By PlaceholderTextField = By.id("placeholderText");
  private By Button = By.id("myButton");
  private By ReadOnlyTextField = By.id("readOnlyText");
  private By ParagraphWithText = By.id("pText");
  private By svgRect = By.id("svgRect");
  private By slider = By.id("mySlider");
  private By progressBar = By.id("progressBar");
  private By progressLabel = By.id("progressLabel");
  private By imageFrame = By.id("myFrame1");
  private By imageInFrame = By.cssSelector("img:nth-of-type(1)"); // in imageFrame
  private By textFrame = By.id("myFrame2");
  private By textInFrame = By.cssSelector("h4"); // in textFrame
  private String radioGroup = "radioGroup1";
  private By radioButton1 = By.id("radioButton1");
  private By radioButton2 = By.id("radioButton2");
  private By rowRevealCheckbox = By.id("checkBox1");
  private By groupedCheckbox1 = By.id("checkBox2");
  private By groupedCheckbox2 = By.id("checkBox3");
  private By groupedCheckbox3 = By.id("checkBox4");
  private By preselectedCheckbox = By.id("checkBox5");
  private By checkBoxFrame = By.id("myFrame3"); // since this is a simple frame, not giving it its own methods
  private By checkBoxInFrame = By.id("checkBox6"); // currently within checkBoxFrame, it could be moved outside of this frame in future
  private By selectDropdown = By.id("mySelect");
  private By htmlMeter = By.id("meterBar");
  private By hoverDropdown = By.id("myDropdown");
  private By H3Text = By.cssSelector("h3");
  private By[] hoverDropOptions = {
    By.id("dropOption1"),
    By.id("dropOption2"),
    By.id("dropOption3")
  };
  private By hiddenRow = By.cssSelector("tr.hidden_row");
  private By dragAndDropA = By.id("drop1");
  private By dragAndDropB = By.id("drop2");
  private By draggableImage = By.id("logo");
  private By urlLink = By.id("myLink1");
  private By linkWithText = By.id("myLink2");
  private By seleniumLink = By.id("myLink3");
  private By demoPageLink = By.id("myLink4");
  
  public DemoPage(WebDriver driver) {
    this.driver = driver;
  }
  
  // enters text into the text input field
  public void enterTextInputField(String value) {
    driver.findElement(textInputField).sendKeys(value);
  }
  
  // gets the value of the text input field
  public String getTextInputField() {
    return driver.findElement(textInputField).getAttribute("value");
  }

  // enters text into the text area field
  public void enterTextAreaField(String value) {
    driver.findElement(textAreaField).sendKeys(value);
  }
  
  // returns true if there is a text area has a vertical scrollbar
  public boolean textAreaHasVerticalScrollbar() {
    WebElement textArea = driver.findElement(textAreaField);
    return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollHeight > arguments[0].clientHeight;", textArea);
  }
  
  // returns the value of the H3 text
  public String getH3Text() {
    return driver.findElement(H3Text).getText();
  }
  
  public String getPreFilledText() {
    return driver.findElement(PreFilledTextField).getAttribute("value");
  }
  
  public String getPreFilledPlaceholder() {
    return driver.findElement(PreFilledTextField).getAttribute("placeholder");
  }
  
  public String getPlaceholderText() {
    return driver.findElement(PlaceholderTextField).getAttribute("text");
  }
  
  public String getPlaceholderPlaceholder() {
    return driver.findElement(PlaceholderTextField).getAttribute("placeholder");
  }

  public String getButtonText() {
    return driver.findElement(Button).getText();
  }
  
  public String getReadOnlyTextFieldText() {
    return driver.findElement(ReadOnlyTextField).getAttribute("value");
  }
  
  public String getParagraphWithTextText() {
    return driver.findElement(ParagraphWithText).getText();
  }

  public String getButtonColor() {
    return driver.findElement(Button).getCssValue("color");
  }
  
  public String getReadOnlyTextFieldColor() {
    return driver.findElement(ReadOnlyTextField).getCssValue("color");
  }
  
  public String getParagraphWithTextColor() {
    return driver.findElement(ParagraphWithText).getCssValue("color");
  }
  
  public void clickButton() {
    driver.findElement(Button).click();
  }

  // selects a specific hover option and returns its text value
  public String selectHoverOption(int number) {
    Actions actions = new Actions(driver);
    actions.moveToElement(driver.findElement(hoverDropdown)).build().perform();
    WebElement hoverOption = driver.findElement(hoverDropOptions[number]);
    String hoverOptionText = hoverOption.getText();
    hoverOption.click();
    return hoverOptionText;
  }
  
  // gets the color of the HTML SVG with rectangle
  public String getSvgRectColor() {
    return driver.findElement(svgRect).getAttribute("fill");
  }

  public void setSliderControl(int value) {
    WebElement sliderElement = driver.findElement(slider);
    int width = sliderElement.getSize().getWidth();
    int nearestValue = (value / 10) * 10; // rounded to the nearest 10
    int relativeValue = nearestValue - 50; // relative to the center of the track (0 to 100)
    int pixelPosition = relativeValue * width / 100; 
    Actions actions = new Actions(driver);
    //System.out.println(width);
    //System.out.println(nearestValue);
    //System.out.println(relativeValue);
    //System.out.println(pixelPosition);
    actions.dragAndDropBy(sliderElement, pixelPosition, 0).build().perform();

    //actions.moveToElement(driver.findElement(hoverDropdown)).build().perform();
  }
  
  public int getProgressBarValue() {
    return Integer.parseInt(driver.findElement(progressBar).getAttribute("value"));
  }
  
  public String getProgressLabelText() {
    return driver.findElement(progressLabel).getText();
  }

  public String imageInFrameSource() {
    driver.switchTo().frame(driver.findElement(imageFrame));
    String source = driver.findElement(imageInFrame).getAttribute("src");
    driver.switchTo().defaultContent();
    return source;
  }
  
  public String textInFrameText() {
    driver.switchTo().frame(driver.findElement(textFrame));
    String source = driver.findElement(textInFrame).getText();
    driver.switchTo().defaultContent();
    return source;
  }

  public void selectRadioButton1() {
    driver.findElement(radioButton1).click();
  }

  public void selectRadioButton2() {
    driver.findElement(radioButton2).click();
  }
  
  public void verifyRadioButton1Selected() {
    assertTrue(driver.findElement(radioButton1).isSelected());
    assertFalse(driver.findElement(radioButton2).isSelected());
  }

  public void verifyRadioButton2Selected() {
    assertTrue(driver.findElement(radioButton2).isSelected());
    assertFalse(driver.findElement(radioButton1).isSelected());
  }
  
  public boolean isRowRevealCheckboxChecked() {
    return driver.findElement(rowRevealCheckbox).isSelected();
  }
  
  public boolean isGroupedCheckbox1Checked() {
    return driver.findElement(groupedCheckbox1).isSelected();
  }

  public boolean isGroupedCheckbox2Checked() {
    return driver.findElement(groupedCheckbox2).isSelected();
  }
  
  public boolean isGroupedCheckbox3Checked() {
    return driver.findElement(groupedCheckbox3).isSelected();
  }
  
  public boolean isPreselectedCheckboxChecked() {
    return driver.findElement(preselectedCheckbox).isSelected();
  }
  
  public void checkAllGroupedCheckboxes() {
    // Find all checkboxes with class "ClassB"
    List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox'].checkBoxClassB"));

    // Iterate through the checkboxes and select them
    for (WebElement checkbox : checkboxes) {
      checkbox.click(); // Select the checkbox
    }
  }
  
  public void clickRowRevealCheckbox() {
    driver.findElement(rowRevealCheckbox).click();
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
  
  //private By hiddenRow = By.cssSelector("tr.hidden_row");
  //private By DragAndDropA = By.id("drop1");
  //private By DragAndDropB = By.id("drop2");
  
  public boolean hiddenRowIsHidden() {
    //System.out.println("  =  " + driver.findElement(hiddenRow).getCssValue("display"));
    return driver.findElement(hiddenRow).getCssValue("display").equals("none");
  }
  
  public void DragFromAToB() {
    Actions actions = new Actions(driver);
    actions.dragAndDrop(driver.findElement(dragAndDropA), driver.findElement(dragAndDropB)).build().perform();
  } 
  
  private boolean draggableImageIsInZone(By ZoneBy) {
    WebElement ZoneElement = driver.findElement(ZoneBy);
    WebElement ImageElement = driver.findElement(draggableImage);
    WebElement ParentElement = ImageElement.findElement(By.xpath(".."));
    //System.out.println("Zone ID = " + ZoneElement.getAttribute("ID") + "; Image ID = " + ImageElement.getAttribute("ID") + "; Parent ID = " + ParentElement.getAttribute("ID"));
    return ZoneElement.getAttribute("ID").equals(ParentElement.getAttribute("ID"));
  }
  
  public boolean draggableImageIsInZoneA() {
    return draggableImageIsInZone(dragAndDropA);
  }
  
  public boolean draggableImageIsInZoneB() {
    return draggableImageIsInZone(dragAndDropB);    
  }
  
  public String urlLinkUrl() {
    return driver.findElement(urlLink).getAttribute("href");
  }
  
  public String urlLinkText() {
    return driver.findElement(urlLink).getText();
  }
  
  public void followUrlLink() {
    driver.findElement(urlLink).click();
  }
}