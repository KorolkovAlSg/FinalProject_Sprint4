import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPlaced {
    private WebDriver driver;

    // Конструктор класса
    public OrderPlaced(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор текста "заказ оформлен" окна "заказ оформлен"
    private By orderPlaced = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and text()='Заказ оформлен']");

    // Локатор кнопки "да" окна "хотите оформить заказ?"
    private By buttonYes = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Да']");

    // Метод для нажатия на кнопку "да" окна "хотите оформить заказ?"
    public void clickButtonOrder(){
        driver.findElement(buttonYes).click();
    }

    // метод ожидания загрузки окна "заказ оформлен"
    public void waitForLoadProfileData() {
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(orderPlaced).getText() != null
                && !driver.findElement(orderPlaced).getText().isEmpty()
        ));
    }
    // общий метод
    public void placedOrder(){
        clickButtonOrder();
        waitForLoadProfileData();
    }
}
