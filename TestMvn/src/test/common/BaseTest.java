package test.common;

import main.util.ExcelHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    public WebDriver driver;
    public ExcelHelpers excelHelpers;

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        System.setProperty("webdriver.chrome.driver", "D:\\DoAnLinh\\TestMvn\\src\\test\\WebDriver\\chromedriver.exe"); //Fix warning Connection reset
        setupDriver(browser);
//        excelHelpers = new ExcelHelpers();
    }
    public WebDriver setupDriver(String browserName) {
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }
    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        //WebDriverManager.chromedriver().setup(); //Selenium 4.11.0 về sau không cần dùng WDM
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }


    @AfterMethod
    public void closeDriver() {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); //Reset timeout
        if (driver != null) {
            driver.quit();
        }
    }
}
