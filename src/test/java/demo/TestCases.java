package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    // ----------------------- Testcase01 -------------------------
    // Navigate to "https://www.flipkart.com/"
    // Locate webelement for searchBar : //input[contains(@title,'Search for
    // Products')] and search for "Washing Machine": sendKeys and click enter
    // Locate webelement for popularity and click :
    // //div[text()='Popularity'].click()
    // Get the number of items for less than or equal to 4: //div[@class='XQDdHH'
    // and number(text()) <= 4]
    // Print the count of items: print 0, if no item is there
    // Close the browser quit()
    @Test
    public void testCase01() {
        try {
            driver.get("https://www.flipkart.com/");

            WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search for Products')]"));
            Wrappers.waitUntilElementVisible(driver, searchBar);
            Wrappers.searchForProduct(driver, searchBar, "Washing Machine");
            Wrappers.pressEnter(driver, searchBar);

            WebElement popularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
            Wrappers.waitUntilElementVisible(driver, popularity);
            Wrappers.chooseOption(driver, popularity);

            double productRating = 4;
            List<WebElement> items = driver
                    .findElements(By.xpath("//div[@class='XQDdHH' and number(text()) <= " + productRating + "]"));
            List<String> item_result = Wrappers.getCountOfProducts(driver, items);
            if (item_result.size() == 0) {
                System.out.println("Products with less than or equal to 4 are: 0");
            } else {
                System.out.println("Products with less than or equal to 4 are: " + item_result.size());
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    // ----------------------- Testcase02 -------------------------
    // Navigate to "https://www.flipkart.com/"
    // Locate webelement for searchBar : //input[contains(@title,'Search for
    // Products')] and search for "iPhone": sendKeys and click enter
    // Define an empty hash map<String><String>
    // Locate webelements for discountPercents for all the products :
    // //div[@class='yKfJKb row']//div[@class='UkUFwK']/span
    // If text stored in above webelement is >17%, then store it in value of the
    // hashmap
    // Get the title for the product : /ancestor::div[@class='yKfJKb
    // row']//div[@class='KzDlHZ'].getText() and store it as key in the hash map
    // Print both key and value of the hash map in console
    // Close the browser quit()

    @Test
    public void testCase02() {
        try {
            driver.get("https://www.flipkart.com/");

            WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search for Products')]"));
            Wrappers.waitUntilElementVisible(driver, searchBar);
            Wrappers.searchForProduct(driver, searchBar, "iPhone");
            Wrappers.pressEnter(driver, searchBar);

            List<WebElement> discountPercents = driver
                    .findElements(By.xpath("//div[@class='yKfJKb row']//div[@class='UkUFwK']/span"));
            List<WebElement> productTitles = driver
                    .findElements(By.xpath("//div[@class='yKfJKb row']//div[@class='KzDlHZ']"));

            List<String> discountPercent = Wrappers.getDiscountPercent(driver, discountPercents);
            List<String> productTitle = Wrappers.getProductTitle(driver, productTitles);

            HashMap<String, String> result = new HashMap<String, String>();
            double discount = 17;
            for (int i = 0; i < discountPercent.size(); i++) {
                if (Integer.parseInt(discountPercent.get(i).replaceAll("[^0-9]", "")) > discount) {
                    result.put(productTitle.get(i), discountPercent.get(i));
                }
            }
            if (result.size() == 0) {
                System.out.println("No product with discount greater than 17%");
            } else {
                for (String key : result.keySet()) {
                    System.out.println("Product Title: " + key + " Discount Percent: " + result.get(key));
                }
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
        // ----------------------- Testcase03 -------------------------
        // Navigate to "https://www.flipkart.com/"
        // Locate webelement for searchBar : //input[contains(@title,'Search for
        // Products')] and search for "Coffee Mug": sendKeys and click enter
        // Locate webelement for 4stars and more and click:
        // //div[@class='XqNaEv']/following-sibling::div[contains(text(),'4')]
        // Locate webelements for reviews and sort it in decsending and then put top 5
        // in a integer list : //span[@class='Wphh3N'].getText
        // Define a empty HashMap
        // for the top 5 reviewed elements, get the title : //a[@class='wjcEIp'] and
        // image url by: //img[@loading='eager']
        // print tilte and image
        // Close the browser quit()

        @Test
        public void testCase03() {
            try{
                driver.get("https://www.flipkart.com/");
    
                WebElement searchBar = driver.findElement(By.xpath("//input[contains(@title,'Search for Products')]"));
                Wrappers.waitUntilElementVisible(driver, searchBar);
                Wrappers.searchForProduct(driver, searchBar, "Coffee Mug");
                Wrappers.pressEnter(driver, searchBar);
    
                WebElement fourStars = driver.findElement(By.xpath("//div[@class='XqNaEv']/following-sibling::div[contains(text(),'4')]"));
                Wrappers.waitUntilElementVisible(driver, fourStars);
                Wrappers.chooseOption(driver, fourStars);
                
                Thread.sleep(5000);
                List<WebElement> reviews = driver.findElements(By.xpath("//span[@class='Wphh3N']"));
                List<String> reviewList = new ArrayList<>();
                for (WebElement review : reviews) {
                    reviewList.add(review.getText().replaceAll("[^0-9]", ""));
                }
                List<Integer> top5Reviews = reviewList.stream().map(Integer::parseInt).sorted(Collections.reverseOrder()).limit(5).collect(Collectors.toList());
    
                List<WebElement> productTitles = driver.findElements(By.xpath("//a[@class='wjcEIp']"));
                List<WebElement> productImages = driver.findElements(By.xpath("//img[@loading='eager']"));
    
                HashMap<String, String> result = new HashMap<String, String>();
                for (int i = 0; i < top5Reviews.size(); i++) {
                    result.put(productTitles.get(i).getText(), productImages.get(i).getAttribute("src"));
                }
                for (String key : result.keySet()) {
                    System.out.println("Product Title: " + key + " Image URL: " + result.get(key));
                }
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
        }
    }


    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}