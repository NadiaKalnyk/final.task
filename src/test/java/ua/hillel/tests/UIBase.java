package ua.hillel.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeTest;
import ua.hillel.UI.BasePage;

import static com.codeborne.selenide.Selenide.open;

public class UIBase {
    static {
        Configuration.reportsFolder = "test-result/reports/ExtentHtml.html";
    }
    @BeforeTest
    public void setUp() {
        Configuration.browser = "chrome";
        open("https://freelance.lsrv.in.ua");
        Configuration.timeout = 40000;
        Configuration.pageLoadTimeout = 40000;
        Configuration.savePageSource = false;
        Configuration.screenshots = true;
    }
    public void timeSetup(){
        Configuration.timeout = 40000;
        Configuration.pageLoadTimeout = 40000;
    }
    public void login() {
        new BasePage().goToLoginPage()
                .setUsername("nk_test")
                .setPassword("123456qQ")
                .clickLoginButton();
    }
    public void alert(){
        Selenide.switchTo().alert().accept();
    }
}
