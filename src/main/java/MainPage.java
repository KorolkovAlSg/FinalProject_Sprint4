import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

// Класс главной страницы
public class MainPage {

    private WebDriver driver;

    // Локатор для элементов списка "Вопросы о важном"
    private By listOfQuestions = By.className("accordion__item");

    // Локатор для кнопки "заказать" в шапке страницы
    private By topButtonOrder = By.className("Button_Button__ra12g");

    // Локатор для кнопки "заказать" по центру страницы
    private By centerButtonOrder = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    // Локатор для кнопки закрытия окна куки "да все привыкли"
    private By cookieButton = By.id("rcc-confirm-button");

    // Локатор для заголовка страницы формы "для кого самокат"
    private By headerTitle = By.xpath(".//div[contains(@class, 'Order_Header__BZXOb') and text()='Для кого самокат']");

    // Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для нажатия на кнопку "заказать" в шапке страницы
    public void clickTopButtonOrder() {
        driver.findElement(topButtonOrder).click();
    }

    // Метод для нажатия на кнопку "заказать" по центру страницы
    public void clickCenterButtonOrder() {
        WebElement button = driver.findElement(centerButtonOrder);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }

    // Метод ожидает загрузки страницы с формой "для кого самокат"
    public void waitLoadHeaderTitleData(){
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(headerTitle).getText() != null
                && !driver.findElement(headerTitle).getText().isEmpty()
        ));
    }

    // Метод закрывает окно подтверждения куки, скроллит страницу до последнего элемента списка,
    // нажимает на каждый пункт списка в разделе "вопросы о важном" и ожидает появления видимого и непустого текста
    public void checkOperDDList() {

        driver.findElement(cookieButton).click();
        List<WebElement> listOfQuElements = driver.findElements(listOfQuestions);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", listOfQuElements.get(listOfQuElements.size()-1));

        int i = 0;
        while (i < listOfQuElements.size()) {
            listOfQuElements.get(i).click();
            String locator = String.format(".//div[contains(@id, 'accordion__panel-%s') and not(@hidden)]", i);
            new WebDriverWait(driver, 1).until(driver -> (driver.findElement(By.xpath(locator)).getText()
                    !=null && !driver.findElement(By.xpath(locator)).getText().isEmpty()
            ));
            i = i + 1;
        }
    }

}
