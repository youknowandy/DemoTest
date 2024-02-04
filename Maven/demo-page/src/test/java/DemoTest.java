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
  public void subTest1Text() {
    // Test 1
    // Assignment: Fill in the Text Input Field with "MAPS is boring" and verify it filled in correctly
    System.out.println("Test 1: Text Input Field");
    demoPage.enterTextInputField("MAPS is boring");
    String value = demoPage.getTextInputField();
    System.out.println(value.toString());
    assertThat(value, is("MAPS is boring"));
  }
  public void subTest2Color() {
    // Test 2
    // Assignment: Grab the rgb color from "HTML SVG with rect" and populate a variable with the result.
    System.out.println("Test 2: SVG Rectangle Color");
    String color = demoPage.getSvgRectColor();
    System.out.println(color);
  }
  
  public void subTest3Checkbox() {
    // Test 3
    // Assignment: Toggle the CheckBox in the iFrame and confirm it is checked.
    System.out.println("Test 3: CheckBox in an iFrame");
    demoPage.clickCheckBoxInFrame();
    String value = demoPage.getCheckBoxInFrameValue();
    System.out.println(value.toString());
    assertThat(value, is("on"));
  }
  
  public void subTest4Dropdown() {
    // Test 4
    // Assignment: Change Select Dropdown to "Set to 50%" and Assert the HTML meter changes to what is selected.
    System.out.println("Test 4: Dropdown Box Selection");
    int percent = 50;
    demoPage.selectDropdownPercentage(percent);
    int percentFilled = demoPage.getHtmlMeterPercentage();
    System.out.println(String.valueOf(percentFilled) + "%");
    assertEquals(percentFilled, percent);
  }
  
  @Test
  public void demo() {
    driver.get("https://seleniumbase.io/demo_page");
    driver.manage().window().setSize(new Dimension(1012, 648));    
    
    // Run Tests
    subTest1Text();
    subTest2Color();
    subTest3Checkbox();
    subTest4Dropdown();
  }
}
