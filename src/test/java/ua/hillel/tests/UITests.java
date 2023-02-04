package ua.hillel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.hillel.UI.*;

public class UITests extends UIBase {
    @Test
    public void login(){
        String login = "nk_test";
        String password = "123456qQ";
        new BasePage().goToLoginPage()
                .setUsername(login)
                .setPassword(password)
                .clickLoginButton();
    }
    @Test
    public void changeData() {
        String firstName = "Nadia";
        String lastName = "Test";
        login();
        BroadAnnouncementPage broadAnnouncementPage = new BroadAnnouncementPage();

        broadAnnouncementPage.clickIconMenu()
                .selectProfileOption()
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
        login();
        BroadAnnouncementPage broadAnnouncementPage = new BroadAnnouncementPage();

        broadAnnouncementPage.clickIconMenu()
                .selectProfileOption()
                .clickAddJobButton()
                .setJobTitle(titleValue)
                .setJobDescription(descriptionValue)
                .setJobPrice(priceValue)
                .clickCreateJobButton();

        ProfilePage profilePage = new ProfilePage();
        Assert.assertTrue(profilePage.getCreatedJobTitle().contains(titleValue));
        Assert.assertTrue(profilePage.getCreatedJobDescription().contains(descriptionValue));
        Assert.assertTrue(profilePage.getCreatedJobPrice().contains(String.valueOf(priceValue)));
    }
    @Test
    public void addComment() throws ElementIsNotFoundException {
        login();
        BroadAnnouncementPage broadAnnouncementPage = new BroadAnnouncementPage();
        broadAnnouncementPage.clickViewInfoButton()
                .setComment()
                .clickLeaveCommentButton()
                .checkAddedComment();
    }
    @Test
    public void deleteAnnouncement()  throws ElementIsNotFoundException{
        login();
        BroadAnnouncementPage broadAnnouncementPage = new BroadAnnouncementPage();

        Assert.assertTrue(broadAnnouncementPage.clickIconMenu().selectProfileOption()
                .checkAllYouAnnouncements());

/*        broadAnnouncementPage.clickIconMenu().selectProfileOption()
                .clickRemoveJob();
        alert();
 */
    }
}