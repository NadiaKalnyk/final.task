package ua.hillel.UI;

import com.codeborne.selenide.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    int deleteJobID = 1;
    final int newCreatedJobID = 1;
    private SelenideElement editButton = $x("//*[text()='Edit Info']");
    private SelenideElement firstNameField =  $x("//input[@formcontrolname='name']");
    private SelenideElement lastNameField = $x("//input[@formcontrolname='lastname']");
    private  SelenideElement updateButton = $x("//*[text()=' Update ']");
    private SelenideElement addJobButton = $x("//*[text()='Add job']");
    private SelenideElement jobTitle = $x("//input[@formcontrolname='title']");
    private SelenideElement jobDescription = $x("//textarea[@formcontrolname='description']");
    private SelenideElement jobPrice = $x("//input[@formcontrolname='price']");
    private SelenideElement createJobButton = $x("//*[text()=' Create job ']");
    private SelenideElement createdJobTitle = $x("//mat-card[1]/mat-card-header/div/mat-card-title");
    private SelenideElement createdJobDescription = $x("//mat-card[1]/mat-card-content");
    private SelenideElement createdJobPrice = $x("//mat-card[1]/mat-card-subtitle/mat-card-subtitle[2]");
    private  SelenideElement cancelButton = $x("//*[text()=' Cancel ']");
    private SelenideElement removeJobButton = $x("//mat-card["+deleteJobID+"]/mat-card-actions/button");
    private ElementsCollection announcements = $$x("//mat-card");

    public ProfilePage clickEditButton(){
        editButton.shouldBe(Condition.visible).click();
        return new ProfilePage();
    }
    public ProfilePage setFirstName(String firstName){
        firstNameField.shouldBe(Condition.visible).clear();
        firstNameField.shouldBe(Condition.visible).sendKeys(firstName);
        return this;
    }
    public ProfilePage setLastName(String lastName){
        lastNameField.shouldBe(Condition.visible).clear();
        lastNameField.shouldBe(Condition.visible).sendKeys(lastName);
        return this;
    }
    public SelenideElement getFirstName(){
        return firstNameField.shouldBe(Condition.visible);
    }
    public String getLastName(){
        return lastNameField.shouldBe(Condition.visible).getValue();
    }
    public ProfilePage clickUpdateButton(){
        updateButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new ProfilePage();
    }
    public ProfilePage clickAddJobButton(){
        addJobButton.shouldBe(Condition.visible).click();
        return new ProfilePage();
    }
    public ProfilePage setJobTitle(String title){
        jobTitle.shouldBe(Condition.visible).sendKeys(title);
        return this;
    }
    public ProfilePage setJobDescription (String description){
        jobDescription.shouldBe(Condition.visible).sendKeys(description);
        return this;
    }
    public ProfilePage setJobPrice (String price){
        jobPrice.shouldBe(Condition.visible).sendKeys(price);
        return this;
    }
    public ProfilePage clickCreateJobButton(){
        createJobButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return this;
    }
    public String getCreatedJobTitle(){
        return createdJobTitle.shouldBe(Condition.visible).getText();
    }
    public String getCreatedJobDescription(){
        return createdJobDescription.shouldBe(Condition.visible).getText();
    }
    public String getCreatedJobPrice(){

        return createdJobPrice.shouldBe(Condition.visible).getText();
    }
    public ProfilePage clickCancelButton(){
        cancelButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new ProfilePage();
    }
    public ProfilePage clickRemoveJob() throws ElementIsNotFoundException {
        boolean findAnnouncement = removeJobButton.exists();
        if(findAnnouncement==true) {
            removeJobButton.shouldBe(Condition.visible).click();
        } else {
            throw new ElementIsNotFoundException("Job is not created by ID" + deleteJobID);
        }
        return this;
    }
    public ProfilePage viewAnnouncements() {
            Selenide.actions(). scrollByAmount(0, 10000).perform();
            return new ProfilePage();
        }
    public ProfilePage checkAllYouAnnouncements() throws ElementIsNotFoundException {
            boolean b = announcements.isEmpty();
            if (b==true) {
                throw new ElementIsNotFoundException("No announcement has been created");
            } else {
                System.out.println("You had " + announcements.size() + " announcements. First of them was deleted");
            }
        return this;
    }
}

