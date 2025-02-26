package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    public static void waitUntilElementVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void searchForProduct(WebDriver driver, WebElement element, String product) {
        element.sendKeys(product);
    }

    public static void pressEnter(WebDriver driver, WebElement element) {
        element.sendKeys(Keys.ENTER);
    }

    public static void chooseOption(WebDriver driver, WebElement element) {
        element.click();
    }

    public static List<String> getCountOfProducts(WebDriver driver, List<WebElement> items) {
        List<String> itemsText = new ArrayList<>();
        for (WebElement item : items) {
            itemsText.add(item.getText());
        }
        return itemsText;
    }

    public static List<String> getDiscountPercent(WebDriver driver, List<WebElement> items) {
        List<String> itemsText = new ArrayList<>();
        for (WebElement item : items) {
            itemsText.add(item.getText());
        }
        return itemsText;
    }

    public static List<String> getProductTitle(WebDriver driver, List<WebElement> items) {
        List<String> itemsText = new ArrayList<>();
        for (WebElement item : items) {
            itemsText.add(item.getText());
        }
        return itemsText;
    }
}
