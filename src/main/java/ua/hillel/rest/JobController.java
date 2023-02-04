package ua.hillel.rest;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONObject;
import ua.hillel.rest.entities.Job;

import java.io.IOException;

public class JobController {
    private String userFirstName = "Nadia";
    private String userLastName = "Test";

    public Job createJob(Job job) throws IOException, APIException {
        Gson gson = new Gson();

        RequestBody body = RequestBody.create(gson.toJson(job), MediaType.parse("application/json"));

        AuthController token = new AuthController();

        Request request = new Request.Builder()
                .post(body)
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/job/create")
                .build();

        Response response = new OkHttpClient().newCall(request).execute();
        Job createNewJob = gson.fromJson(response.body().string(), Job.class);

        System.out.println(" - new Job is created: "+createNewJob);
        return createNewJob;
    }
    public int findJobById(int id) throws  IOException, APIException{
        AuthController token = new AuthController();
        Request request = new Request.Builder()
                .get()
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/job/" + id)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new APIException(response.code() + "Job couldn't find by ID" + id);
        }
        JSONObject responseObject = new JSONObject(response.body().string());
        System.out.println(response.code());
        System.out.println(responseObject);
        return response.code();
    }
    public int findJobsCreatedByMe() throws IOException, APIException {
        AuthController token = new AuthController();
        Request request = new Request.Builder()
                .get()
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/job/user/jobs")
                .build();

        Response response = new OkHttpClient().newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new APIException(response.code() + "User didn't create any job");
        }
        System.out.println(response.code());
        System.out.println(response.body().string());
        return response.code();
    }
    public int findAllJobs() throws  IOException, APIException{
        AuthController token = new AuthController();
        Request request = new Request.Builder()
                .get()
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/job/all")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        System.out.println(response.code());
        System.out.println(response.body().string());
        return response.code();
    }
    public String deleteJobByID(int jobID) throws IOException, APIException {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(jobID), MediaType.parse("application/json"));

        AuthController token = new AuthController();

        Request request = new Request.Builder()
                .post(body)
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/job/delete/"+jobID)
                .build();

        Response response = new OkHttpClient().newCall(request).execute();

        if (!response.isSuccessful()){
            throw new APIException(response.code() + "Job couldn't delete by ID" + jobID);
        }
        JSONObject responseObject = new JSONObject(response.body().string());

        System.out.println(response.code());
        System.out.println(responseObject);
        System.out.println("Job was deleted: "+response.body().string());
        return responseObject.get("message").toString();
    }
}
