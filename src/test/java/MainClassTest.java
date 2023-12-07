import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainClassTest {
    private static final By acceptPopup = By.id("L2AGLb");
    private static final By typeText = By.id("APjFqb");
    private static final By clickLink =
            By.xpath("//div[@id='rso']/div[1]/div//div[1]//span/a/h3");
    private static final By getText =
            By.xpath("//div[2]/div/div[1]/h1/span");
    private static final By buttonCode = By.xpath("//span[text()='Просмотр кода']/ancestor::a");
    private static final By buttonLogIn = By.xpath("//span[text()='Войти']/ancestor::a");
    private WebDriver driver = null;
    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    void visitGoogleSite() {
        driver.get("https://www.google.com");
        waiter(driver,acceptPopup).click();//выключает попап с принятием условий пользования
        waiter(driver,typeText).sendKeys("текст для поиска");
        waiter(driver,typeText).sendKeys(Keys.RETURN);
        waiter(driver,clickLink).click();
        String text = waiter(driver,getText).getText();
        Assert.assertEquals("Поиск текста", text);

    }
    @Test(alwaysRun = true)
    void visitWikiSite() {
        driver.get("https://ru.wikipedia.org/wiki");
//        driver.findElement(By.xpath("//span[text()='Мастер статей']/following-sibling::a")).click(); тут не понимаю
//        почему то не находит
        waiter(driver,buttonCode).click();
        waiter(driver,buttonLogIn).click();
    }
    @AfterMethod
    void quit(){
        driver.quit();
    }
    public static WebElement waiter (WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
