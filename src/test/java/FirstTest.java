import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import pages.PageWebBrowser;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class FirstTest {

    private AndroidDriver driver;
    public static final String YANDEX_MARKET_TITLE = "Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов";

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Test API 29");
        capabilities.setCapability("platformVersion", "10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability("chromedriverExecutable", "E:\\GitHub\\MyFirstProjectAppium\\driver\\chromedriver.exe");
//        capabilities.setCapability("appPackage", "org.wikipedia");
//        capabilities.setCapability("appActivity", ".main.MainActivity");
//        capabilities.setCapability("app", "C:/AppiumMobile/apks/org.wikipedia.apk");


        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void firstTest() {
        //Step 1 - Open https://www.google.com/
        driver.get("https://www.google.com/");
        assertEquals("The title does not match the expected, title = [" + driver.getTitle()+"]", "Google", driver.getTitle());

        //Step 2 - Search 'яндекс маркет'
        driver.findElement(By.name("q")).sendKeys("яндекс маркет", Keys.ENTER);
        List<WebElement> list = driver.findElements(By.className("g"));
        WebElement href = list.get(0).findElement(By.tagName("a"));
        assertEquals("the first link refers to ["+ href.getAttribute("href") + "]", "https://market.yandex.ru/", href.getAttribute("href"));

        //Step 3 - Go to link
        href.sendKeys(Keys.ENTER);
        assertEquals("The title does not match the expected, title = [" + driver.getTitle()+"]", YANDEX_MARKET_TITLE, (driver.getTitle()));
    }
}
