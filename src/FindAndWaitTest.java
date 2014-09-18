import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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


    final String INBOX_FOLDERS = ".//*[@id='ctl00_ContentPlaceHolder1_RadTreeView1']/ul/li/ul/li[3]/ul/li/div/span[@class='rtIn']";
    final String SUBJECT = "//*[@id='ctl00_ContentPlaceHolder2_RadGrid1_ctl00_Header']//a[.='Subject']";



    private WebDriver driver;
    @Test

    public void testWebMail() throws IOException{
        Properties properties = new Properties();
        properties.load(new FileReader
                ("src/resources/property.properties"));
        FirefoxBinary binary = new FirefoxBinary(new File(properties.getProperty("binaryPath")));
        FirefoxProfile profile = new FirefoxProfile();
        driver = new FirefoxDriver(binary, profile);
        driver.get("http://demos.telerik.com/aspnet-ajax/webmail/default.aspx");
        WebElement mail = driver.findElement(By.xpath(SUBJECT));
        WebDriverWait driverWait = new WebDriverWait(driver, 15);
        for (WebElement folder : driver.findElements(By.xpath(INBOX_FOLDERS))){
            folder.click();
            driverWait
                    .until(ExpectedConditions.stalenessOf(mail));
            driverWait
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(SUBJECT)));



        }

        driver.quit();
    }

}
