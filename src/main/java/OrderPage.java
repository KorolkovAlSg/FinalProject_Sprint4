import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    // Локатор для кнопки "далее" формы "для кого самокат"
    private By buttonNext = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");

    // Общий локатор для списка полей без поля "станция метро"
    String formLocator = ".//div[@class='Input_InputContainer__3NykH']/input[@placeholder = '* %s']";

    // Локатор для поля "станция метро"
    private By inputMetroSt = By.xpath(".//div[@class='select-search__value']/input");

    // Локатор для выпадающего списка поля "стацния метро"
    private By dropDownMetroSt = By.xpath(".//div[contains(@class,'select-search__select')]");

    // Конструктор класса
    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    // Метод для заполнения полей формы "для кого самокат"
    public void setTextLabelName(String placeholderName, String inputText){
        driver.findElement(By.xpath(String.format(formLocator, placeholderName))).sendKeys(inputText);
    }

    // Метод для заполнения полей формы "для кого самокат"
    public void setTextLabelSurname(String placeholderName, String inputText){
        driver.findElement(By.xpath(String.format(formLocator, placeholderName))).sendKeys(inputText);
    }

    // Метод для заполнения полей формы "для кого самокат"
    public void setTextLabelAddress(String placeholderName, String inputText){
        driver.findElement(By.xpath(String.format(formLocator, placeholderName))).sendKeys(inputText);
    }

    // Метод для заполнения полей формы "для кого самокат"
    public void setTextLabelPhoneNumber(String placeholderName, String inputText){
        driver.findElement(By.xpath(String.format(formLocator, placeholderName))).sendKeys(inputText);
    }

    // Метод для заполнения поля "станция метро" формы "для кого самокат"
    public void setMetroSt(String metroSt){
        driver.findElement(inputMetroSt).click();
        driver.findElement(inputMetroSt).sendKeys(metroSt);
        driver.findElement(dropDownMetroSt).click();
    }

    //Общий метод для заполнения формы "для кого самокат"
    public void fillForm(String placeholderName, String clientName, String placeholderSurname, String clientSurname,
                         String placeholderAddress, String clientAddress, String stMetro, String placeholderPhone,
                         String clientPhone){
        setTextLabelName(placeholderName, clientName);
        setTextLabelSurname(placeholderSurname, clientSurname);
        setTextLabelAddress(placeholderAddress, clientAddress);
        setMetroSt(stMetro);
        setTextLabelPhoneNumber(placeholderPhone, clientPhone);
    }

    // Метод для нажатия на кнопку "далее"
    public void clickNextButton(){
        driver.findElement(buttonNext).click();
    }
}
