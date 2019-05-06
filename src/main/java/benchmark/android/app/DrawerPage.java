package benchmark.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DrawerPage {
    WebDriver driver;

    public DrawerPage(WebDriver driver) {
        this.driver = driver;
    }

    public Page openPage(String page) {
        List<WebElement> elements = this.driver.findElements(By.className("android.widget.CheckedTextView"));
        for (WebElement item:elements) {
            if (item.getText().equals(page)) {
                item.click();
            }
        }

        // TODO in future releases it should return a generic class page becuase this method can open
        // any type of pages
        return new NASPage(this.driver);
    }

}
