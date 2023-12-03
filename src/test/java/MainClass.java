import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class MainClass {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("L2AGLb")).click(); //выключает попап с принятием условий пользования
        driver.findElement(By.id("APjFqb")).sendKeys("текст для поиска");
        driver.findElement(By.id("APjFqb")).sendKeys(Keys.RETURN);

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        };


        driver.get("https://ru.wikipedia.org/wiki");
//        driver.findElement(By.xpath("//span[text()='Мастер статей']/following-sibling::a")).click(); тут не понимаю
//        почему то не находит
        driver.findElement(By.xpath("//span[text()='Просмотр кода']/ancestor::a")).click();
        driver.findElement(By.xpath("//span[text()='Войти']/ancestor::a")).click();

        driver.quit();
    }
}
