import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;


public class TestBase {

    public String baseUrl = "http://barancev.w.pw/php4dvd/";
    protected WebDriver driver;
    protected WebDriverWait wait;


    protected ExpectedCondition<Boolean> ajaxComplete() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                long x = (Long) ((JavascriptExecutor) d).executeScript(
                        "return window.jQuery.active", new Object[]{});
                return x == 0;
            }
        };
    }

    protected WebElement findElement(By locator) {
        return waitFor(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(WebElement element) {
        element.click();
        waitFor(ajaxComplete());
    }

    protected void click(By locator) {
        findElement(locator).click();
        waitFor(ajaxComplete());
    }

    protected <T> T waitFor(ExpectedCondition<T> condition) {
        wait = new WebDriverWait(driver, 10);
        try {
            return wait.until(condition);
        } catch (TimeoutException e) {
            throw e;
        }
    }

    protected void type(By locator, String text) {
        WebElement element = findElement(locator);
        element.click();
        element.clear();
        element.sendKeys(text);
        waitFor(ajaxComplete());
    }

    protected void openUrl(String relativeUrl) {
        driver.get(baseUrl + relativeUrl);
        waitFor(ajaxComplete());
    }

    protected void loginAs(String login, String password) {
        openUrl("");
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
        type(By.id("username"), login);
        type(By.name("password"), password);
        findElement(By.name("submit")).sendKeys(Keys.ENTER);
        waitFor(ajaxComplete());
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@type, 'text')]")));


    }

    protected void filmSearchingBy(String text) {
        WebElement element = findElement(By.xpath("//input[contains(@type, 'text')]"));
        element.click();
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
        waitFor(ajaxComplete());
    }


    @BeforeTest
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}



