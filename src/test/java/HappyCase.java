import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class HappyCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String pageTitle1 = driver.getTitle();

        WebElement inputEmail = driver.findElement(By.name("Email"));
        inputEmail.sendKeys("hantp032@vnext.vn");

        WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
        inputFirstName.sendKeys("Daisy");

        WebElement inputLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName")));
        inputLastName.sendKeys("Anne");

        WebElement inputCompany = driver.findElement(By.name("Company"));
        inputCompany.sendKeys("Vnext Software");

        WebElement inputPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone")));
        inputPhone.sendKeys("+91123456789");

        Select selectCountry = new Select(Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")))));
        selectCountry.selectByValue("Albania");

        Select selectInterest = new Select(Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@id,'Interest')]")))));
        selectInterest.selectByIndex(1);

        WebElement areaComment = driver.findElement(By.xpath("//textarea[contains(@id,'Comments')]"));
        areaComment.sendKeys("This is the test content");

        WebElement inputCheckbox = driver.findElement(By.xpath("//input[@id = 'mktoCheckbox_47208_0']"));
        inputCheckbox.click();
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class = 'mktoButton']"));
        btnSubmit.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.titleIs(pageTitle1)));

        String pageTitle2 = driver.getTitle();
        if (Objects.equals(pageTitle2, "Thank you")) {
            System.out.println("Happy Case has passed.");
        }
        else  {
            System.out.println(pageTitle2);
        }
        driver.quit();
    }
}
