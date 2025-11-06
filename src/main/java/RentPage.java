import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;


public class RentPage {

    private WebDriver driver;

    // Конструктор класса
    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    // Локатор для поля "когда привезти самокат" формы "про аренду"
    private By dateSand = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    // Локатор для раскрытия выпадающего списка поля "срок аренды" формы "про аренду"
    private By rentalPeriodButton = By.xpath(".//div[@class='Dropdown-arrow-wrapper']/span");

    // Общий локатор выпадающего списка поля "срок аренды" формы "про аренду"
    String dropDownRentalPeriod = ".//div[@class='Dropdown-menu']/div[text() = '%s']";

    // Локатор чекбокса "черный жемчуг" поля "цвет самоката" формы "про аренду"
    private By checkBoxBlack = By.xpath(".//input[@id='black']");

    // Локатор чекбокса "серая безысходность" поля "цвет самоката" формы "про аренду"
    private By checkBoxGrey = By.xpath(".//input[@id='grey']");

    // Локатор поля "комментарий для курьера" ормы "про аренду"
    private By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Локатор кнопки "заказать" формы "про аренду"
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");

    // Метод для выбора срока аренды формы "про аренду"
    public void selectRentalPeriod(String daysValue){
        driver.findElement(rentalPeriodButton).click();
        driver.findElement(By.xpath(String.format(dropDownRentalPeriod, daysValue))).click();
    }

    // Метод для назначения даты поля "когда привезти самокат" формы "про аренду"
    public void selectDate(String dateText){
        driver.findElement(dateSand).sendKeys(dateText);
    }

    // Метод для выбора цвета самоката формы "про аренду"
    public void selectColor(String color){
        if (Objects.equals(color, "Grey")){
            driver.findElement(checkBoxGrey).click();
        } else driver.findElement(checkBoxBlack).click();
    }

    // Метод для заполнения поля "комментарий для курьера" формы "про аренду"
    public void setCommForCour(String comment){
        driver.findElement(commentForCourier).sendKeys(comment);
    }

    // Общий метод для заполнения формы "про аренду"
    public void fillForm(String date, String days, String colorScooter, String commentCour){
        selectDate(date);
        selectRentalPeriod(days);
        selectColor(colorScooter);
        setCommForCour(commentCour);
    }

    // Метод для нажатия на кнопку "заказать"
    public void clickButtonOrder(){
        driver.findElement(orderButton).click();
    }

}
