import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FindAndWaitTest {

    // Locator list
    final String INBOX = ".//span[.='Inbox']";
    final String AJAX = ".//span[.='AJAX']";
    final String ANNOUNCEMENTS  = ".//span[.='Announcements']";
    final String OPEN_ACCESS_ORM  = ".//span[.='OpenAccess ORM']";
    final String SILVER_LIGHT  = ".//span[.='Silverlight']";
    final String WIN_FORMS  = ".//span[.='WinForms']";
    final String WPF  = ".//span[.='WPF']";
    final String SUBJECT = "//*[@id='ctl00_ContentPlaceHolder2_RadGrid1_ctl00_Header']//a[.='Subject']";

    private WebDriver driver;

    public void clickAndWaitForPresent(String clickElement, final String waitElement) {
        driver.findElement(By.xpath(clickElement)).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitElement)));
    }


    @Test

    public void testWebMail() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileReader
                ("src/resources/property.properties"));
        FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("binaryPath")));
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
        driver.get("http://demos.telerik.com/aspnet-ajax/webmail/default.aspx");
        clickAndWaitForPresent(INBOX, SUBJECT);
        clickAndWaitForPresent(AJAX, SUBJECT);
        clickAndWaitForPresent(ANNOUNCEMENTS, SUBJECT);
        clickAndWaitForPresent(OPEN_ACCESS_ORM, SUBJECT);
        clickAndWaitForPresent(SILVER_LIGHT, SUBJECT);
        clickAndWaitForPresent(WIN_FORMS, SUBJECT);
        clickAndWaitForPresent(WPF, SUBJECT);
        driver.quit();
    }

}
