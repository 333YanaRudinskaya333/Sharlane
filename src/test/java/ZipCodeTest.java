import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ZipCodeTest {

    /*

    Прекондишен: Открыть браузер!!

    1. открыть страницу https://www.sharelane.com/cgi-bin/register.py!!
    2. ввести в поле zip-code значение 12345
    3. нажать кнопку continue
    ОР: оказались на странице регистрации

    Посткондишен: Закрыть браузер!!
     */

    @Test
    public void checkZipCodeFieldFiveDigits() {
        //инициализируем браузер как Chrome
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //переходим на страницу https://www.sharelane.com/cgi-bin/register.py
        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        // находим элемент zip_code
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        // находим элемент Continue
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        boolean isDisplayed = browser.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        Assert.assertTrue(isDisplayed);

        browser.close();
    }

    @Test
    public void checkZipCodeFieldFourDigits() {
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        browser.get("https://www.sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("1234");
        browser.findElement(By.cssSelector("[value=Continue]")).click();

        boolean isDisplayedErrorMessage = browser.findElement(By.cssSelector("[class=error_message]")).isDisplayed();
        Assert.assertTrue(isDisplayedErrorMessage);

        browser.close();
    }
}
