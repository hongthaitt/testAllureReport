package test.testcase;

import main.util.ExcelHelpers;
import test.common.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.page.LoginPage;


public class LoginTestcase extends BaseTest {
    LoginPage loginPage;
    @BeforeClass
    public void setUpBrower() throws Exception {
        driver = new BaseTest().setupDriver("chrome");
        excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile("src/test/data/Book1.xlsx", "Sheet1");
    }
    @Test
    public void loginTestSuccess() throws Exception {
        loginPage = new LoginPage(driver);
        loginPage.login(excelHelpers.getCellData("username", 2), excelHelpers.getCellData("password", 2));
    }

}
