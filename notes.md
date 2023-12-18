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































NOTE: Each browser contains a separate browser driver.??? 