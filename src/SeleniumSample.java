import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SeleniumSample {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://selenium2.ru");
        driver.quit();
    }
}
