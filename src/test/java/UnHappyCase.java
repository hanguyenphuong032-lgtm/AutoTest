import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class UnHappyCase {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://saucelabs.com/request-demo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement inputEmail = driver.findElement(By.name("Email"));
        inputEmail.sendKeys("thuynvt@vnext.vn");

        if (!inputEmail.getAttribute("value").isEmpty()) {
            WebElement inputFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
            WebElement inputLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName")));
            WebElement inputPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Phone")));
            Select selectCountry = new Select(Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Country")))));
            inputFirstName.sendKeys("Daisy");
            inputLastName.sendKeys("Anne");
            inputPhone.sendKeys("+91123456789");
//            selectCountry.selectByValue("Albania");
        }

        WebElement inputCompany = driver.findElement(By.name("Company"));
        inputCompany.sendKeys("Vnext Software");

        Select selectInterest = new Select(Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[contains(@id,'Interest')]")))));
        selectInterest.selectByIndex(1);

        WebElement areaComment = driver.findElement(By.xpath("//textarea[contains(@id,'Comments')]"));
        areaComment.sendKeys("This is the test content");

        WebElement inputCheckbox = driver.findElement(By.xpath("//input[@id = 'mktoCheckbox_47208_0']"));
        inputCheckbox.click();
        WebElement btnSubmit = driver.findElement(By.xpath("//button[@class = 'mktoButton']"));
        btnSubmit.click();

        WebElement msg = driver.findElement(By.xpath("//div[@class = 'mktoErrorMsg']"));
        if(msg.isDisplayed()){
            String msgID = msg.getAttribute("id");
            String msgText = msg.getText();
            if (msgText.equals("This field is required.")){
                assert msgID != null;

                if (msgID.contains("Interest")){
                    System.out.println("Chua chon Interest");
                }
                else if (msgID.contains("FirstName")){
                    System.out.println("Chua nhap field FirstName");
                }
                else if (msgID.contains("LastName")){
                    System.out.println("Chua nhap field LastName");
                }
                else if (msgID.contains("Country")){
                    System.out.println("Chua chon Country");
                }
                else if (msgID.contains("Company")){
                    System.out.println("Chua nhap field Company");
                }
                else if (msgID.contains("Email")){
                    System.out.println("Chua nhap field Email");
                }
                else if (msgID.contains("Phone")){
                    System.out.println("Chua nhap field Phone");
                }
            }
            else if (msgText.contains("Must be valid email.")){
                System.out.println("Chua nhap field Email");
            }
        }

    }
}
