import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LogInScript {
    public static void Login(WebDriver driver, String email_input, String password_input, String email, String password, String button)
    {

        WebElement inputEmaill = driver.findElement(By.name(email_input));
        inputEmaill.sendKeys(email);

        WebElement inputPassword = driver.findElement(By.name(password_input));
        inputPassword.sendKeys(password);

        WebElement buttn = driver.findElement(By.name(button));
        buttn.click();
    }
}
