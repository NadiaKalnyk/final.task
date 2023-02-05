package ua.hillel.UI;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CommentPage {
    private SelenideElement leaveCommentField = $x("//*[@formcontrolname = 'message']");
    private SelenideElement leaveCommentButton = $x("//*[text() = ' Leave comment ']");
    private SelenideElement closeJobDetails = $x("//*[text() = 'Close job details']");
    private SelenideElement createdComment = $x("//div[@class='comments']/div[1]/mat-card/mat-card-content/p");

    String message = "TBD";
    public CommentPage setComment() {
        leaveCommentField.shouldBe(Condition.visible).clear();
        leaveCommentField.shouldBe(Condition.visible).sendKeys(message);
        return this;
    }
    public BroadAnnouncementPage clickCloseJobDetailsButton(){
        closeJobDetails.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new BroadAnnouncementPage();
    }
    public CommentPage clickLeaveCommentButton(){
        leaveCommentButton.shouldBe(Condition.visible).shouldBe(Condition.enabled).click();
        return new CommentPage();
    }
    public CommentPage checkAddedComment() throws ElementIsNotFoundException {
        String comment = createdComment.shouldBe(Condition.visible).getText();
        if (!comment.contains(message)){
            throw new ElementIsNotFoundException("Error. Comment can't be created for this announcement");
        }
        System.out.println("Comment is added successfully");
        return this;
    }
}
