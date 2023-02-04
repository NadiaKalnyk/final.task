package ua.hillel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.hillel.rest.*;
import ua.hillel.rest.entities.Comment;
import ua.hillel.rest.entities.Job;
import ua.hillel.rest.entities.User;

import java.io.IOException;
import java.time.LocalDateTime;

public class RestTests {
    private final int ID = 0;
    private int jobID = 61;
    private String userFirstName = "Nadia";
    private String userLastName = "Test";
    private String commentDescription = "TBD";
    private String jobTitle = "QA Automation";
    private String jobDescription = "Automation Engineer jobs now available";
    private int jobPrice = 111111111;

    @Test (priority = 10)
    public void SingUp() throws IOException, APIException {
        AuthController authController = new AuthController();

        String message = String.valueOf(authController.userSignsUp());

        Assert.assertTrue(message.equals("User registered successfully"));
    }
    @Test (priority = 120)
    public void SingIn() throws IOException,APIException {
        AuthController authController = new AuthController();
        String token = String.valueOf(authController.userSignsIn());
        Assert.assertTrue(token.equals("true"));
    }
    @Test (priority = 40)
    public void updateUser() throws IOException, APIException {
        AuthController authController = new AuthController();
        UserController userController = new UserController();

        User user = new User();
        user.setId(ID);
        user.setUsername(authController.getUsername());
        user.setName(userFirstName);
        user.setLastname(userLastName);

        User updateUser = userController.updateUserWithGSON(user);

        Assert.assertTrue((updateUser.getUsername()+updateUser.getName()+updateUser.getLastname())
                .equals(user.getUsername()+user.getName()+user.getLastname()));
    }
    @Test (priority = 30)
    public void findUserByID() throws IOException, APIException {

        UserController userController = new UserController();
        int ID = Integer.parseInt(userController.checkUserInformation());
        String responseCode = String.valueOf(userController.findUserById(ID));
        Assert.assertTrue(responseCode.equals("200"));
    }
    @Test (priority = 20)
    public void userInformation() throws IOException,APIException {
        UserController userController = new UserController();
        userController.checkUserInformation();
    }
    @Test (priority = 60)
    public void findJobByID() throws IOException, APIException {
        JobController jobController = new JobController();

        String responseCode = String.valueOf(jobController.findJobById(jobID));
        Assert.assertTrue(responseCode.equals("200"));
    }
    @Test (priority = 70)
    public void findJobsCreatedByMe() throws IOException, APIException {

        JobController jobController = new JobController();
        String responseCode = String.valueOf(jobController.findJobsCreatedByMe());
        Assert.assertTrue(responseCode.equals("200"));
    }
    @Test (priority = 80)
    public void findAlJobs() throws IOException, APIException {

        JobController jobController = new JobController();
        String responseCode = String.valueOf(jobController.findAllJobs());
        Assert.assertTrue(responseCode.equals("200"));
    }
    @Test (priority = 50)
    public void createNewJob() throws IOException, APIException {
        JobController jobController = new JobController();
        Job job = new Job();
        job.setId(jobID);
        job.setTitle(jobTitle);
        job.setDescription(jobDescription);
        job.setPrice(jobPrice);
        job.setUser(userFirstName+" "+userLastName);
        job.setNoOfComments(0);

        Job newJob = jobController.createJob(job);
        Assert.assertTrue((newJob.getTitle()+newJob.getDescription()+newJob.getPrice()+newJob.getUser()+newJob.getNoOfComments()).
                equals(job.getTitle()+job.getDescription()+job.getPrice()+job.getUser()+job.getNoOfComments()));
    }

    @Test (priority = 110)
    public void deleteJobByID() throws IOException, APIException {
        JobController jobController = new JobController();
        Job job = new Job();
        String message = jobController.deleteJobByID(jobID);
        Assert.assertEquals(message, "Job is deleted");
    }
    @Test (priority = 90)
    public void createComment()throws IOException, APIException {
        AuthController authController = new AuthController();
        String realDateTime = String.valueOf(LocalDateTime.now());

        CommentController commentController = new CommentController();
        Comment comment = new Comment();
        comment.setId(ID);
        comment.setMessage(commentDescription);
        comment.setUsername(authController.getUsername());
        comment.setCommentDate(realDateTime);
        Comment newComment = commentController.createComment(jobID, comment);
        newComment.setCommentDate(realDateTime);

        Assert.assertTrue(newComment.equals(comment));
    }

        @Test (priority = 100)
        public void findAllCommentsByJobID() throws IOException, APIException{
        CommentController commentController = new CommentController();
        String responseCode = String.valueOf(commentController.findAllComments(jobID));
        Assert.assertTrue(responseCode.equals("200"));
        }
    }
