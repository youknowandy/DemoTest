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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;


import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import pages.DemoPage;


public class DemoTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  private DemoPage demoPage;
  
  
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    demoPage = new DemoPage(driver);
  }
  @After
  public void tearDown() {
    //driver.quit();
  }
  public void test1Text() {
    // Test 1
    // Assignment: Fill in the Text Input Field with "MAPS is boring" and verify it filled in correctly
    System.out.println("Test 1: Text Input Field");
    demoPage.enterTextInputField("MAPS is boring");
    String value = demoPage.getTextInputField();
    System.out.println(value.toString());
    assertThat(value, is("MAPS is boring"));
  }
  public void test2Color() {
    // Test 2
    // Assignment: Grab the rgb color from "HTML SVG with rect" and populate a variable with the result.
    System.out.println("Test 2: SVG Rectangle Color");
    String color = demoPage.getSvgRectColor();
    System.out.println(color);
  }
  
  public void test3Checkbox() {
    // Test 3
    // Assignment: Toggle the CheckBox in the iFrame and confirm it is checked.
    System.out.println("Test 3: CheckBox in an iFrame");
    demoPage.clickCheckBoxInFrame();
    String value = demoPage.getCheckBoxInFrameValue();
    System.out.println(value.toString());
    assertThat(value, is("on"));
  }
  
  public void test4Dropdown() {
    // Test 4
    // Assignment: Change Select Dropdown to "Set to 50%" and Assert the HTML meter changes to what is selected.
    System.out.println("Test 4: Dropdown Box Selection");
    int percent = 50;
    demoPage.selectDropdownPercentage(percent);
    int percentFilled = demoPage.getHtmlMeterPercentage();
    System.out.println(String.valueOf(percentFilled) + "%");
    assertEquals(percentFilled, percent);
  }
  
  public void test5TextArea() {
    // Test 5
    // Assignment: Verify that there is a vertical scrollbar present if 3 lines are present in a text area, but not if only 2.
    System.out.println("Test 5: Text Area Scrollbar");
    demoPage.enterTextAreaField("Line 1\nLine 2");
    assertEquals(demoPage.textAreaHasVerticalScrollbar(), false);
    demoPage.enterTextAreaField("\nLine 3");
    assertEquals(demoPage.textAreaHasVerticalScrollbar(), true);
  }
  
  public void test6HoverDropdown() {
    // Test 6
    // Assignment: Verify that selecting a hover dropdown item results in the selection appearing in the H3 text
    System.out.println("Test 6: Hover Dropdown");
    // Default
    assertEquals(demoPage.getH3Text(), "Automation Practice");
    // 1st option
    String optionText = demoPage.selectHoverOption(0);
    assertEquals(demoPage.getH3Text(), optionText + " Selected");
    // 2nd option
    optionText = demoPage.selectHoverOption(1);
    assertEquals(demoPage.getH3Text(), optionText + " Selected");
    // 3rd option
    optionText = demoPage.selectHoverOption(2);
    assertEquals(demoPage.getH3Text(), optionText + " Selected");
  }
  
  public void test7PreFilledAndPlaceholder() {
    // Test 7
    // Assignment: Verify the PreFilled and Placeholder text field's starting values and placeholder values (if any)
    System.out.println("Test 7: Prefilled and Placeholder Text Fields");
    assertEquals("Text...", demoPage.getPreFilledText());
    assertEquals(null, demoPage.getPlaceholderText());
    assertEquals("", demoPage.getPreFilledPlaceholder());
    assertEquals("Placeholder Text Field", demoPage.getPlaceholderPlaceholder());
  }
  
  public void test8ColoredButton() {
    // Test 8
    // Assignment: Verify that clicking a button when it's purple causes it, a read only, and paragraph to turn green,
    //             while if it is green, they instead turn purple.
    System.out.println("Test 8: Colored Button");
    
    // Verify Initial Text
    assertEquals("Click Me (Green)", demoPage.getButtonText());
    assertEquals("The Color is Green", demoPage.getReadOnlyTextFieldText());
    assertEquals("This Text is Green", demoPage.getParagraphWithTextText());
    
    // Verify Initial Colors
    assertEquals(Color.fromString("green"), Color.fromString(demoPage.getButtonColor()));
    assertEquals(Color.fromString("green"), Color.fromString(demoPage.getReadOnlyTextFieldColor()));
    assertEquals(Color.fromString("green"), Color.fromString(demoPage.getParagraphWithTextColor()));
    
    // Click Button
    demoPage.clickButton();
    
    // Verify New Text
    assertEquals("Click Me (Purple)", demoPage.getButtonText());
    assertEquals("The Color is Purple", demoPage.getReadOnlyTextFieldText());
    assertEquals("This Text is Purple", demoPage.getParagraphWithTextText());
    
    // Verify New Colors
    assertEquals(Color.fromString("purple"), Color.fromString(demoPage.getButtonColor()));
    assertEquals(Color.fromString("purple"), Color.fromString(demoPage.getReadOnlyTextFieldColor()));
    assertEquals(Color.fromString("purple"), Color.fromString(demoPage.getParagraphWithTextColor()));    
  }
  
  public void test9Slider() {
    // Test #9
    // Assignment: Verify that the slider can go from 0 to 100 in steps of 10 and the Progress Bar and its label update accordingly.
    System.out.println("Test 9: Slider");
        
    // Set to 0%
    demoPage.setSliderControl(0);
    assertEquals("Progress Bar: (0%)", demoPage.getProgressLabelText());
    assertEquals(0, demoPage.getProgressBarValue());
    
    // Set to 10%
    demoPage.setSliderControl(10);
    assertEquals("Progress Bar: (10%)", demoPage.getProgressLabelText());
    assertEquals(10, demoPage.getProgressBarValue());
    
    // ... TO DO:  Fill in the rest and add a subroutine for this; verify can't do a value not a multiple of 10
    
    // Set to 90%
    demoPage.setSliderControl(90);
    assertEquals("Progress Bar: (90%)", demoPage.getProgressLabelText());
    assertEquals(90, demoPage.getProgressBarValue());

    // Set to 100%
    demoPage.setSliderControl(100);
    assertEquals("Progress Bar: (100%)", demoPage.getProgressLabelText());
    assertEquals(100, demoPage.getProgressBarValue());
    
  }
  
  public void test10ImageAndTextInFrame() {
    // Test 10
    // Assignment: Verify the src of an image in a frame and the text of a text in a frame are as expected
    System.out.println("Test 10: Image and Text In Frame");
    assertEquals("data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0jvb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAARe8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0CcguWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7", demoPage.imageInFrameSource());
    assertEquals("iFrame Text", demoPage.textInFrameText());
  }
  
  public void test11RadioButtons() {
    // Test 11
    // Assignment: Verify the starting values for the radio buttons and that when you click one, the other unselects
    System.out.println("Test 11: Radio Buttons");
    demoPage.verifyRadioButton1Selected();
    demoPage.selectRadioButton2();
    demoPage.verifyRadioButton2Selected();
    demoPage.selectRadioButton1();
    demoPage.verifyRadioButton1Selected();   
  }
  
  public void test12Checkboxes() {
    // Test 12
    // Assignment: Verify the starting values for all the checkboxes, then select all Class B check boxes
    System.out.println("Test 12: Checkboxes");
    assertFalse(demoPage.isRowRevealCheckboxChecked());
    assertFalse(demoPage.isGroupedCheckbox1Checked());
    assertFalse(demoPage.isGroupedCheckbox2Checked());
    assertFalse(demoPage.isGroupedCheckbox3Checked());
    assertTrue(demoPage.isPreselectedCheckboxChecked());
    demoPage.checkAllGroupedCheckboxes();
  }
  
  public void test13HiddenDragAndDrop() {
    // Test 13
    // Assignment: Verify the Drag and Drop row is hidden until associated checkbox is checked.
    //             Drag from A to B and verify the image moved.    
    System.out.println("Test 13: Hidden Drag and Drop");
    assertTrue(demoPage.hiddenRowIsHidden());
    demoPage.clickRowRevealCheckbox();
    assertFalse(demoPage.hiddenRowIsHidden());
    
    assertTrue(demoPage.draggableImageIsInZoneA());
    assertFalse(demoPage.draggableImageIsInZoneB());
    demoPage.DragFromAToB();
    assertTrue(demoPage.draggableImageIsInZoneB());
    assertFalse(demoPage.draggableImageIsInZoneA());
  }
  
  public void test14Links() {
    // Test 14
    // Assignment: Verify the link and text of the url link
    //             Click this link, verify the web address is as expected, and then navigate back.
    System.out.println("Test 14: Links");
    assertEquals("https://seleniumbase.com/", demoPage.urlLinkUrl());
    assertEquals("seleniumbase.com", demoPage.urlLinkText());

    demoPage.followUrlLink();
    assertEquals("https://seleniumbase.com/", driver.getCurrentUrl());
    driver.navigate().back();
  }

  @Test
  public void demo() {
    driver.get("https://seleniumbase.io/demo_page");
    driver.manage().window().setSize(new Dimension(1012, 648));    
    
    // Run Tests
    test1Text();
    test2Color();
    test3Checkbox();
    test4Dropdown();
    test5TextArea();
    test6HoverDropdown();
    test7PreFilledAndPlaceholder();
    test8ColoredButton();
    test9Slider();
    test10ImageAndTextInFrame();
    test11RadioButtons();
    test12Checkboxes();
    test13HiddenDragAndDrop();
    test14Links();
  }
}
