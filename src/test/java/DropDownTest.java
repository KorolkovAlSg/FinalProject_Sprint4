import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.junit.After;
import org.junit.Test;


public class DropDownTest {

    private WebDriver driver = new ChromeDriver();
    //private WebDriver driver = new FirefoxDriver();

    @Test
    public void startActivity(){
        // переход на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");

        // создан объект класса главной страницы
        MainPage mainPage = new MainPage(driver);

        // проверь выпадающий список в разделе "вопросы о важном"
        mainPage.checkOperDDList();
    }

    @After
    public void terdown(){
        driver.quit();
    }
}
