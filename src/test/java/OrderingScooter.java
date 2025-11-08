import constans.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderingScooter {

    private WebDriver driver;

    private final String buttonOrderLocator;
    private final String placeholderName;
    private final String clientName;
    private final String placeholderSurname;
    private final String clientSurname;
    private final String placeholderDeliveryAddress;
    private final String deliveryAddress;
    private final String placeholderPhoneNumber;
    private final String phoneNumber;
    private final String metroSt;
    private final String dateSand;
    private final String daysRent;
    private final String colorScooter;
    private final String commentForCour;

    public OrderingScooter(String buttonOrderLocator, String placeholderName, String clientName, String placeholderSurname, String clientSurname, String placeholderDeliveryAddress, String deliveryAddress, String metroSt, String placeholderPhoneNumber, String phoneNumber, String dateSand, String daysRent, String colorScooter, String commentForCour) {
        this.buttonOrderLocator = buttonOrderLocator;
        this.placeholderName = placeholderName;
        this.clientName = clientName;
        this.placeholderSurname = placeholderSurname;
        this.clientSurname = clientSurname;
        this.placeholderDeliveryAddress = placeholderDeliveryAddress;
        this.deliveryAddress = deliveryAddress;
        this.metroSt = metroSt;
        this.placeholderPhoneNumber = placeholderPhoneNumber;
        this.phoneNumber = phoneNumber;
        this.dateSand = dateSand;
        this.daysRent = daysRent;
        this.colorScooter = colorScooter;
        this.commentForCour = commentForCour;
    }

    @Parameterized.Parameters(name = "Кнопка Заказа: {0}, Имя: {2}, Фамилия: {4}, Адрес: {6}, Станция Метро: {7}, Телефон: {9}, Дата доставки: {10}, Срок аренды: {11}, Цвет самоката: {12}, Комментарий курьеру: {13}")
    public static Object[][] getLabelInfo() {
        return new Object[][]{
                {"topButton","Имя", "Анатолий", "Фамилия", "Анатолиев", "Адрес: куда привезти заказ", "Улица Анатолиева 12",
                        "Андроновка", "Телефон: на него позвонит курьер", "792222222222", "11/11/2011","семеро суток", "Black", ""},
                {"topButton","Имя", "Яна", "Фамилия", "Янович", "Адрес: куда привезти заказ", "Улица Яновая 1",
                        "Площадь Революции", "Телефон: на него позвонит курьер", "792222222222", "07/11/2015","сутки", "Grey", "Есть комментарий"},
                {"centerButton","Имя", "Анатолий", "Фамилия", "Анатолиев", "Адрес: куда привезти заказ", "Улица Анатолиева 12",
                        "Андроновка", "Телефон: на него позвонит курьер", "792222222222", "11/11/2011","семеро суток", "Black", ""},
                {"centerButton","Имя", "Яна", "Фамилия", "Янович", "Адрес: куда привезти заказ", "Улица Яновая 1",
                        "Площадь Революции", "Телефон: на него позвонит курьер", "792222222222", "07/11/2015","сутки", "Grey", "Есть комментарий"},
        };
    }
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

        //driver = new ChromeDriver();
        driver = new FirefoxDriver();

        // переход на страницу тестового приложения
        driver.get(Constants.URLFORTESTS);
    }

    @Test
    public void orderingScooterTest() {

        // Создать экземпляр класса главной страницы
        MainPage mainPage = new MainPage(driver);

        // Нажать на кнопку "заказать" и дождаться загрузки страницы "для кого самокат"
        mainPage.clickButtonOrder(buttonOrderLocator);

        // Создать экземпляр класса страницы с формой "для кого самокат"
        OrderPage orderPage = new OrderPage(driver);

        // Заполнить форму "для кого самокат" данными
        orderPage.fillForm(placeholderName, clientName, placeholderSurname, clientSurname,
                placeholderDeliveryAddress, deliveryAddress, metroSt, placeholderPhoneNumber, phoneNumber);

        // Нажать кнопку "далее"
        orderPage.clickNextButton();

        // Создать экземпляр класса страницы с формой "про аренду"
        RentPage rentPage = new RentPage(driver);

        // Заполнить форму "про аренду" данными
        rentPage.fillForm(dateSand, daysRent, colorScooter, commentForCour);

        // Нажать кнопку "заказать"
        rentPage.clickButtonOrder();

        // Создать экземпляр класса окна подтверждения заказа
        popUpOrderPlaced popUpOrderPlaced = new popUpOrderPlaced(driver);

        // Подтвердить заказ
        popUpOrderPlaced.placedOrder();

    }

    @After
    public void terdown(){
        driver.quit();
    }
}
