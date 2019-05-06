package benchmark.android.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class NASPage extends Page {
    WebElement status;
    public final static String name = "NAS Benchmarks";

    public NASPage(WebDriver driver) {
        super(driver);
    }

    public String getData() {
        return this.driver.findElement(By.id("matrix_timing")).getText();
    }

    public void fillData(String benchmark, String threads, String classSize, String httpEndpoint) {
        clickSpinner("nas_benchmark", benchmark);
        clickSpinner("nas_is_threads", threads);
        clickSpinner("nas_is_class", classSize);
        this.driver.findElement(By.id("http_endpoint")).sendKeys(httpEndpoint);
    }

    private boolean clickSpinner(String spinner, String option) {
        WebElement spinnerWE = this.driver.findElement(By.id(spinner));
        spinnerWE.click();

        WebDriverWait wait = new WebDriverWait(this.driver, 5);
        List<WebElement> options = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.className("android.widget.CheckedTextView")
                )
        );

        for (WebElement item:options) {
            if (item.getText().startsWith(option.toUpperCase())) {
                item.click();
                return true;
            }
        }
        return false;
    }

    public void compute() {
        this.driver.findElement(By.id("compute")).click();
    }

    public boolean isFinished(){
        if (this.status == null) {
            WebDriverWait wait = new WebDriverWait(this.driver, 5);

            this.status = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.id("status")
                    )
            );
        }


        if (this.status.getText().equals("Done.")) {
            return true;
        }
        return false;
    }
}
