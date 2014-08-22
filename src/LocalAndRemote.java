import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by amiheev on 22.08.2014.
 */
public class LocalAndRemote {
    @Test
    public void runInLocalBrowser() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://selenium2.ru");
        driver.quit();

    }
    @Test
    public void runRemoteOnLocalHost() {
        WebDriver driver = new RemoteWebDriver(
                DesiredCapabilities.firefox());
        driver.get("http://selenium2.ru");
        driver.quit();
    }
    @Test
    public void runRemotelyInCloud() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(
                new URL("http://pwnzallmf:a015463f-2d09-4efd-b203-986ba98fe5de@ondemand.saucelabs.com:80/wd/hub"),
                DesiredCapabilities.firefox());
        driver.get("http://selenium2.ru");
        driver.quit();


    }
    @Test
    public void runRemotelyOnRemoteHost() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(
                new URL("192.168.3.42:8080/ipsco/wd/hub"),
                DesiredCapabilities.firefox());
        driver.get("http://selenium2.ru");
        driver.quit();
    }

}
