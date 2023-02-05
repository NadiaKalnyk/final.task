package ua.hillel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.hillel.UI.*;

public class UITests extends UIBase {
    @Test
    public void changeData() {
        String firstName = "Nadia";
        String lastName = "Test";
        LoginPage loginPage = new LoginPage();
        loginPage.userLoggedIn()
                .openProfilePage()
                .clickEditButton()
                .setFirstName(firstName)
                .setLastName(lastName)
                .clickUpdateButton();

        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.clickEditButton().getFirstName().getValue().contains(firstName));
        profilePage.clickCancelButton();
        Assert.assertTrue(profilePage.clickEditButton().getLastName().contains(lastName));
    }
    @Test
    public void createNewAnnouncement() {
        String titleValue = "QA Automation";
        String descriptionValue = "TBD";
        String priceValue = "11111";

        LoginPage loginPage = new LoginPage();
        loginPage.userLoggedIn()
                .openProfilePage()
                .clickAddJobButton()
                .setJobTitle(titleValue)
                .setJobDescription(descriptionValue)
                .setJobPrice(priceValue)
                .clickCreateJobButton();
        timeSetup();
        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.getCreatedJobTitle().contains(titleValue));
        Assert.assertTrue(profilePage.getCreatedJobDescription().contains(descriptionValue));
        Assert.assertTrue(profilePage.getCreatedJobPrice().contains(String.valueOf(priceValue)));
    }
    @Test
    public void addComment() throws ElementIsNotFoundException {
        LoginPage loginPage = new LoginPage();
        loginPage.userLoggedIn()
                .clickViewInfoButton()
                .setComment()
                .clickLeaveCommentButton()
                .checkAddedComment();
    }
    @Test
    public void deleteAnnouncement()  throws ElementIsNotFoundException{
        LoginPage loginPage = new LoginPage();
        loginPage.userLoggedIn()
                .openProfilePage()
                .viewAnnouncements()
                .checkAllYouAnnouncements()
                .clickRemoveJob();
        alert();
    }
}