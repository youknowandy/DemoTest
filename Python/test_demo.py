# Generated by Selenium IDE
import pytest
import time
import json
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

class TestDemo():
  def setup_method(self, method):
    #self.driver = webdriver.Firefox()
    self.driver = webdriver.Chrome()
    self.vars = {}
  
  def teardown_method(self, method):
    self.driver.quit()
  
  def subTest1Text(self):
    # Test 1
    # Assignment: Fill in the Text Input Field with "MAPS is boring" and verify it filled in correctly
    self.driver.find_element(By.ID, "myTextInput").send_keys("MAPS is boring")
    value = self.driver.find_element(By.ID, "myTextInput").get_attribute("value")
    print(value)
    assert value == "MAPS is boring"
  
  def subTest2Color(self):
    # Test 2
    # Assignment: Grab the rgb color from "HTML SVG with rect" and populate a variable with the result.
    #self.driver.find_element(By.ID, "svgRect").click()
    attribute = self.driver.find_element(By.ID, "svgRect").get_attribute("fill")
    self.vars["color"] = attribute
    print("{}".format(self.vars["color"]))
  
  def subTest3Checkbox(self):
    # Test 3
    # Assignment: Toggle the CheckBox in the iFrame and confirm it is checked.
    element = self.driver.find_element(By.ID, "myFrame3")
    self.driver.switch_to.frame(element)
    self.driver.find_element(By.ID, "checkBox6").click()
    value = self.driver.find_element(By.ID, "checkBox6").get_attribute("value")
    assert value == "on"
    print(value)
    self.driver.switch_to.default_content()
  
  def subTest4Dropdown(self):
    # Test 4
    # Assignment: Change Select Dropdown to "Set to 50%" and Assert the HTML meter changes to what is selected.
    dropdown = self.driver.find_element(By.ID, "mySelect")
    dropdown.find_element(By.XPATH, "//option[. = 'Set to 50%']").click()
    self.driver.find_element(By.CSS_SELECTOR, "option:nth-child(2)").click()
    attribute = self.driver.find_element(By.ID, "meterBar").get_attribute("value")
    self.vars["amountFilled"] = attribute
    print("{}".format(self.vars["amountFilled"]))
    assert(self.vars["amountFilled"] == "0.5")
  
  def test_demo(self):
    self.driver.get("https://seleniumbase.io/demo_page")
    self.driver.set_window_size(1012, 648)
    
    # Execute tests
    self.subTest1Text()
    self.subTest2Color()
    self.subTest3Checkbox()
    self.subTest4Dropdown()
    input("Test complete. Press Enter to close browser and exit test...")
  
