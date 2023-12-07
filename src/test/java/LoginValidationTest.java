import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class LoginValidationTest {
    private WebDriver driver = null;
    private static final By usernameInput = By.id("userName");
    private static final By passwordInput = By.id("password");
    private static final By loginButton = By.id("login");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(dataProvider = "invalidCreds")
    public void testInvalidLogin(String username, String password) {
        driver.get("https://demoqa.com/login");
        waiter(driver,usernameInput).sendKeys(username);
        waiter(driver,passwordInput).sendKeys(password);
        waiter(driver,loginButton).click();
    }

    @DataProvider(name = "invalidCreds")
    public Object[][] invalidCreds() {
        return new Object[][] {
                {"", ""},
                {"user123", "pass123"},
        };
    }

    @Test
    public void testValidLogin() {
        driver.get("https://demoqa.com/login");
        waiter(driver,usernameInput).sendKeys("MashaQa");
        waiter(driver,passwordInput).sendKeys("Qa1234567890*");
        waiter(driver,loginButton).click();
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
