import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;

import static org.junit.Assert.assertEquals;

// Класс главной страницы
public class MainPage {

    private WebDriver driver;

    // Локатор для элементов списка "Вопросы о важном"
    private By listOfQuestions = By.className("accordion__item");

    // Локатор для кнопки закрытия окна куки "да все привыкли"
    private By cookieButton = By.id("rcc-confirm-button");

    // Локатор для заголовка страницы формы "для кого самокат"
    private By headerTitle = By.xpath(".//div[contains(@class, 'Order_Header__BZXOb') and text()='Для кого самокат']");
    
    // Локатор кнопки заказать в шапке
    private By topButtonOrder = By.xpath(".//button[contains(@class,'Button_Button__ra12') and text()='Заказать']");
    
    // Локатор кнопки заказать по центру
    private By centerButtonOrder = By.xpath(".//button[contains(@class,'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");
    
    // Конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод нажимает на кнопку "заказать" и проверяет загрузку страницы "для кого самокат"
    public void clickButtonOrder(String buttonOrder){
        WebElement button = null;
        
        if (buttonOrder.equals("topButton")){
            button = driver.findElement(topButtonOrder);
        } else if (buttonOrder.equals("centerButton")){
            button = driver.findElement(centerButtonOrder);
        }
        // скроллить до элемента
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);

        button.click();

        WebElement titleOrderPage = driver.findElement(headerTitle);
        waitLoadElements(titleOrderPage);
    }

    // Метод ожидает загрузки элемента
    public void waitLoadElements(WebElement element){
        new WebDriverWait(driver, 10).until(driver -> (element.getText() != null
                && !element.getText().isEmpty()
        ));
    }

    // Метод закрывает окно подтверждения куки, скроллит страницу до нужного элемента списка с вопросами,
    // проверяет наличие текста и сам текст в разделе "вопросы о важном"
    public void checkQuestionBlock(String accordionHeadingId, String questionText, String accordionPanelId,String hiddenText){

        // закрыть куки
        driver.findElement(cookieButton).click();

        // записать элемент "блок с вопросом" (accordion__heading-N) по id в переменную
        WebElement accordionHeading = driver.findElement(By.xpath(String.format(".//div[contains(@id, '%s')]", accordionHeadingId)));

        // скроллить до элемента accordionHeading
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordionHeading);

        // сравнить текст в элементе accordion__heading-N с требуемым
        assertEquals(String.format("Текст в блоке [.//div[contains(@id, '%s')] не совпадает с требуемым текстом - %s",
                accordionHeadingId, questionText), questionText, accordionHeading.getText());

        // нажать на стрелочку/элемент accordion__heading-N/"блок с вопросом"
        accordionHeading.click();

        // записать элемент "блок с ответом" (accordion__panel-N) по n-ому id в переменную
        WebElement accordionPanel = driver.findElement(By.xpath(String.format(".//div[contains(@id, '%s') and not(@hidden)]", accordionPanelId)));

        // проверить загрузился/появился ли текст с ответом под элементом accordion__heading-N/не пустой ли блок
        waitLoadElements(accordionPanel);

        // сравнить текст в элементе accordion__panel-N с требуемым
        assertEquals(String.format("Текст в блоке [.//div[contains(@id, '%s')] не совпадает с требуемым текстом - %s",
                accordionPanelId, hiddenText), hiddenText, accordionPanel.getText());
    }

}
