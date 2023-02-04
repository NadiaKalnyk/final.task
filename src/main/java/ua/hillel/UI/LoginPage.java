package ua.hillel.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginField = $x("//input[@formcontrolname='username']");
    private SelenideElement passwordField = $x("//input[@formcontrolname='password']");
    private  SelenideElement loginButton =$x("//*[@class='mat-button-wrapper']");

    public LoginPage setUsername(String username){
        loginField.shouldBe(Condition.visible).sendKeys(username);
        return  this;
    }
    public LoginPage setPassword(String password){
        passwordField.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }
    public BroadAnnouncementPage clickLoginButton(){
        loginButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new BroadAnnouncementPage();
    }
}

