package benchmark.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;

    public MainPage(AppiumSession session) {
        this.driver = session.getDriver();
    }

    public DrawerPage openDrawer() {
        this.driver.findElement(By.className("android.widget.ImageButton"))
                .click();

        return new DrawerPage(this.driver);
    }

    public void quit() {
        this.driver.quit();
    }

}
