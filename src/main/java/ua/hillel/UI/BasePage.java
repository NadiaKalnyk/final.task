package ua.hillel.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    private SelenideElement loginLink = $(By.linkText("Log in"));
    public  LoginPage goToLoginPage(){
        loginLink.shouldBe(Condition.visible).click();
        return new LoginPage();
    }
}
