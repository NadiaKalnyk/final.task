package ua.hillel.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import ua.hillel.UI.BasePage;
import ua.hillel.tests.listeners.CustomExtentReportListener;

import static com.codeborne.selenide.Selenide.open;
@Listeners(CustomExtentReportListener.class)
public class UIBase {
    static {
        System.setProperty("extent.reporter.html.start", "true");
        System.setProperty("extent.reporter.html.out", "target/extentReport/ExtentHtml.html");
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
