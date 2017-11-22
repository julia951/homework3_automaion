import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainClass {
    public static void main(String... args) {

        By subCategoryOrders = By.linkText("Заказы");
        By xpathAdminCategories = By.xpath("//li[@id='subtab-AdminCategories']");

        String property = System.getProperty("user.dir") + "/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", property);
        WebDriver driver = new ChromeDriver();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");

        LogInScript.Login(driver, "email", "passwd", "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw", "submitLogin");

        //find catalog
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(subCategoryOrders));
        WebElement subCatalogue = driver.findElement(By.linkText("Каталог"));
        Actions actions = new Actions(driver);
        actions.moveToElement(subCatalogue).build().perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(xpathAdminCategories));

        //нажимаем на категории
        WebElement subtabAdminCategories =driver.findElement(By.xpath("//li[@id='subtab-AdminCategories']"));
        actions.moveToElement(subtabAdminCategories).build().perform();
        subtabAdminCategories.click();

        // ищем кнопоку добавить категорию
        wait.until(ExpectedConditions.visibilityOfElementLocated(subCategoryOrders));

         List<WebElement> arrayBeforeSort = driver.findElements(By.name("categoryBox[]"));

         //search the buttonadding new category
        WebElement addNewCategoryBtn = driver.findElement(By.xpath("//*[@class='toolbar_btn  pointer']"));
        //click on the button
        addNewCategoryBtn.click();
        
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }

      //  WebElement inputNewCategory= driver.findElement(By.id("name_1"));
//        WebElement inputNewCategory = driver.findElement(By.className("copy2friendlyUrl"));
        //WebElement inputNewCategory = driver.findElement(By.xpath("//input[@id='name_1']"));
        //WebElement inputNewCategory = driver.findElement(By.xpath("//*[@id='name_1']"));
        //WebElement inputNewCategory = driver.findElement(By.xpath("//input[@name='name_1']"));
      //  WebElement inputNewCategory = driver.findElement(By.xpath("//*[@name='name_1']"));
       // WebElement inputNewCategory = driver.findElement(By.cssSelector("#name_1"));
        //WebElement inputNewCategory = driver.findElement(By.cssSelector(".col-lg-9 >.form-group > .translatable-field.lang-1 >.col-lg-9"));
        WebElement inputNewCategory = driver.findElement(By.xpath("//input[@id='name_1']"));

        inputNewCategory.sendKeys("Test");
        WebElement saveBtn = driver.findElement(By.xpath("//*[@id='category_form_submit_btn']"));
        saveBtn.click();

        //sure that the category is saved
        WebElement element10 = driver.findElement(By.cssSelector("#content > div:nth-child(4) > div"));

        //find the list
        wait.until(ExpectedConditions.visibilityOfElementLocated(subCategoryOrders));
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
