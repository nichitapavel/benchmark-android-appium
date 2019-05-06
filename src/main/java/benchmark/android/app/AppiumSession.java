package benchmark.android.app;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class AppiumSession {
    WebDriver driver;


    public AppiumSession(URL url, DeviceCapabilities deviceCap) {
        DesiredCapabilities desireCap = deviceCap.getCapabilities();
        this.driver = new AndroidDriver(url, desireCap);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
