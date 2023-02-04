package ua.hillel.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BroadAnnouncementPage {
    int announcementID = 1;
    private SelenideElement menuButton = $x("//*[@mattooltip='Profile']");
    private SelenideElement profileOption =$x("//*[text()='Profile']");
    private  SelenideElement viewInfoButton = $x("//mat-card["+announcementID+"]/mat-card-actions/button");

    public BroadAnnouncementPage clickIconMenu(){
        menuButton.shouldBe(visible).click();
        return this;
    }
    public ProfilePage selectProfileOption(){
        profileOption.shouldBe(visible).click();
        return new ProfilePage();
    }
    public CommentPage clickViewInfoButton() throws ElementIsNotFoundException {
        boolean checkViewButton = viewInfoButton.isEnabled();
        if (checkViewButton== false) {
                throw new ElementIsNotFoundException("View button is not find by this ID:" + announcementID);
        } else {
            viewInfoButton.shouldBe(visible).click();
        }
        return new CommentPage();
    }
}
