# Selenium

- web driver
- IDE - Interaction recorder
- Remote Control - deprecated
- Supports multiple browsers/OSes.
- Client library communicates with browser driver by passing JSON files over HTTP.
- Browser Driver communicates with browser via HTTP.

### Setup:

- Java 17
- Set PATH/JAVA_HOME with java version - sdkman does this automatically

### Selenium Setup:

- Need a driver executable to interact with Browser.
- Need a driver manager to get the correct driver executable. (Can manually do this by setting webdriver.chrome.driver)
  System.setProperty("webdriver.chrome.driver", "/Users/legoman/code/selenium/chromedriver-mac-arm64/chromedriver");
- Use the property to avoid constantly downloading the driver
- Made mistake by downloading a chrome version instead of chromedriver - not clearly obvious on download page.

#### CSS Selectors:

- element_name#id -> input#usernameInput
  OR
  element_name.class_name -> button.signInBtn
  OR
  element_name[attribute='value'] -> input[placeholder='Username']

- driver.findElement(By.id("inputUsername"))
- driver.findElement(By.className("signInBtn"))
- driver.findElement(By.cssSelector("p.error"))
- driver.findElement(By.name("inputPassword"))

- get nth element when css selector selects multiple elements
  input[type='text']:nth-child(2)

- can select an element using this syntax:
  .<CLASSNAME>
  .reset-pwd-btn

- Can select an element using this syntax:
  PARENT_ELEMENT CHILD_ELEMENT

- If you want to get an element that has a class that partially matches 'submit'
  Class could be class="submit mad-btn"
  Asterix makes this a general equality comparison - like regex
  <ELEMENT>[@<CLASS>*='submit']


#### Xpath Selectors:

- FORMAT:  //Tagname[@attribute='value']
- EXAMPLE: //input[@placeholder='Name']
- Don't forget the @ symbol
- get nth element when xpath selects multiple elements
  input[@type='text'][2] -> much easier than css selector
- hidden attribute cas be on other elements causing another match to the CSS selector - confusing things.
- input[@type='text'][2] VS input[type='text']:nth-child(2)

- can browse to children of elements by using:
  //<PARENT>/<CHILD>
- Can get the "nth" element
  //<PARENT>/<CHILD>[index]
  //form/input[3]

- If you want ti get an element that has a class that partially matches 'submit'
  Class could be class="submit mad-btn"
  //<ELEMENT>[contains(@<CLASS>, 'submit')]

#### Dev Tools:
- Using console dev tools tab you can find the element by using the syntax:
  $('CSS_SELECTOR')
  $('p.error')
  This shows the elements on webpage that match that selector.
- You can also type into the Elements tab by pressing CMD+f and typing in the CSS selector.
- NB: need to ensure that one & only one element matches the selector.
- To test xpaths you need to use the expression:
  FORMAT:  $x(<EXPRESSION>)
  EXAMPLE: $x(//input[placeholder='Phone Number'])

#### Waits:

##### Thread.sleep(3000);
##### Implicit   
- Web-driver will wait for a new tag to appear on the page. Doesn't work if the tag already exists 
- Can add implicit wait using driver:
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
- 5 second implicit wait - if element appears after 3 seconds wait will not continue
- 5 seconds is the maximum wait time, after which Exception.
- Monitors DOM for element.
- Applies Loading timeout Globally ****** NB: To all lines in test
- Only defined at beginning - applies to all elements loading 
- Implicit wait will hide any loading issues less than timeout(5seconds)

##### Explicit Wait  
- Wait only applies to specific element, doesn't hide performance bugs elsewhere.
- More code.
- Explicit wait should only be applies to specific steps.
  If your app is generally slow use Implicit waits -> instead of applying explicit waits for each step.
##### Fluent Wait  
- Is a type of Explicit wait
- FluentWait - polling time will polls the dom to check for element.
- WebDriverWait - Polls continuously 
- Regular intervals
- Popular for interview questions.
- Must build our own predicate functions.

### Get Project Path
- works for all OSes:
```java
String projectPath = System.getProperty("user.dir");
```

#### Need to investigate:
- following sibling method in xpath 
  How to get a sibling from a current element.
  e.g. //tr[td='Color Digest'][2]/td/following-sibling::td[1]
- JavascriptExecutor
  Can be used to retrieve a value from an element, by:
  ```java
  String val = "return document.getElementById(\"hiddentext\").value;";
  String text=(String) js.executeScript(val);</p></div></div>
  ```
- PageFactory.initElements(driver, this);
  @FindBy(id='myBtn')
  WebElement fancyBtn; -> automatically associates element -> Page Object Model classes.
  N.B: PageFactory Design Pattern
- PageObject should not hold any data - only page structure & actions(userLogin()).
```java
landingPage.goto();
public void goto(){
    d.get("LANDING_PAGE_URL");        
}
```
- Page Object should not have asserts.

- Relative Locators
  - driver.findElement(withTagName("").above(webElement));
  - above(), below(), toLeftOf(), toRightOf()
  - with(locator).above(ele)
- BrowserStack - executing in different OS browsers.
- Chrome Dev Tools: https://chromedevtools.github.io/devtools-protocol/
  Can manipulate the browser using the API.
- Apache POI - excel data(.xlsx extension) loading to test - Data Driven Test
- NB: JUNIT dataprovider
  - Integrating Junit dataprovider + excel data
- Windows Authenticate prompt alert
  - not html based
  - http://USERNAME:PASSWORD@URL
  - d.get("http://admin:admin@the-internet.herokuapp.com/"
  - d.switchTo().alert().
- Setup Selenium GRID - Node + Worker
  - HUB: localhost:4444
  - Uses drivers in same folder as grid.jar
  - NODE: java -jar <JAR> node --detect-drivers true --publish-events tcp://IP:PORT --subscribe-events tcp://IP:PORT
  - HUB: java -jar <JAR> hub --detect-drivers true
  - WebDriver d = new RemoteWebDriver(new URL("http://<HUB_IP>:<HUB_PORT>), desiredCapabilities);
  - DesiredCapabilities - Browser & platform details.
- 20-25
- Selenium Reporting
- Can monitor browser api calls sent to BE svc - Dev Tools Network Events API
- ThreadLocal - Parallel test runs
- Junit - Retry Mechanism to overcome Flaky tests.
- Jenkins
  - mvn test -Dbrowser=$browserName - browserName parameter "Build with Parameters" 
  - Build Selenium test periodically - regex
- mvn test -Dbrowser=firefox_win|firefox_mac_os|chrome_mac_os|chrome_win|safari_mac_os|safari_win|
- mvn test -P<PROFILE> - can be used to group tests

#### Refreshing the page
```java
d.navigate().refresh();  
d.navigate().refresh();  
d.navigate().to(d.getCurrentUrl());  
d.findElement(***).sendKeys(KEYS.F5);
```