package FirefoxSettingsSample;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.IOException;

public class FirefoxSample {
    @Test
    public void runFromNonstandardLocation() {
        FirefoxBinary binary = new FirefoxBinary(
                new File("D:\\firefox new\\firefox.exe"));
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://selenium2.ru");
    }

    @Test
    public void runWithCustomProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxBinary binary = new FirefoxBinary(
                new File("D:\\firefox new\\firefox.exe"));
        profile.setPreference("intl.accept_languages", "ru");
        FirefoxDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://ciserver.intersog.com");
    }
    @Test
    public void runWithExtension() throws IOException {
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(new File("D:\\firebug-2.0.3-fx.xpi"));
        profile.setPreference("extensions.firebug.currentVersion", "9.9.9");
        profile.setPreference("extensions.firebug.allPagesActivation", "on");
        profile.setPreference("extensions.firebug.defaultPanelName", "net");
        profile.setPreference("extensions.firebug.net.enableSites", "true");
        FirefoxBinary binary = new FirefoxBinary(
                new File("D:\\firefox new\\firefox.exe"));
        FirefoxDriver driver = new FirefoxDriver(binary, profile);
        driver.get("http://selenium2.ru");
    }
}
