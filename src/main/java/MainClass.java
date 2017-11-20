import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainClass {
    public static void main(String... args) {

        By element1 = By.linkText("Заказы");
        By element3 = By.xpath("//li[@id='subtab-AdminCategories']");
        By element5 = By.xpath("//div[@class='col-lg-9']/input[@id='name_1']");
        By element6 = By.xpath("//a[@id='page-header-desc-category-new_category']");

        String property = System.getProperty("user.dir") + "/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", property);
        WebDriver driver = new ChromeDriver();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

        //log in
        ScriptA.Login(driver, "email", "passwd", "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw", "submitLogin");

        //find catalog
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(element1));
        WebElement element2 = driver.findElement(By.linkText("Каталог"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element2).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(element3));

        //нажимаем на категории
        WebElement element4 =driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']"));
        actions.moveToElement(element4).build().perform();
        element4.click();

        // ищем кнопоку добавить категорию
        wait.until(ExpectedConditions.visibilityOfElementLocated(element1));

       // WebElement element8 = driver.findElement(By.cssSelector("#page-header-desc-category-new_category > div"));
       // WebElement element8 = driver.findElement(By.xpath("//*[@id='page-header-desc-category-new_category']/div"));
       // WebElement element8 = driver.findElement(By.xpath("//*[@id='page-header-desc-category-new_category']/i"));
       // WebElement element8 = driver.findElement(By.xpath("//*[@id='page-header-desc-category-new_category']/i"));
        WebElement element8 = driver.findElement(By.xpath("//*[@id='page-header-desc-category-new_category']/i"));
        List<WebElement> arrayBeforeSort = driver.findElements(By.xpath("//*[@id='table-category']"));

        //click on the button
        element8.click();


        //пишем в названии категории Тест и нажимаем сохранить
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name_1")));
        WebElement element9 = driver.findElement(By.id("name_1"));
        element9.sendKeys("Test");
        WebElement element7 = driver.findElement(By.xpath("//*[@id='category_form_submit_btn']"));
        element7.click();

        //sure that the category is saved
        WebElement element10 = driver.findElement(By.cssSelector("#content > div:nth-child(4) > div"));

        //find the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(element1));
        List<WebElement> arrayAfterSort = driver.findElements(By.xpath("//*[@id='table-category']"));

        if (arrayAfterSort.size()>arrayBeforeSort.size()) {
            //find my element of list
            System.out.print("The element is found");
        }
        else
        {
            System.out.println("The element is not found");
        }

        driver.quit();
    }
}
