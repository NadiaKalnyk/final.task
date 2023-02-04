package ua.hillel.rest;

import com.google.gson.Gson;
import okhttp3.*;
import ua.hillel.rest.entities.Comment;

import java.io.IOException;

public class CommentController {

    public Comment createComment(int jobID, Comment comment) throws IOException, APIException {
        Gson gson = new Gson();

        RequestBody body = RequestBody.create(gson.toJson(comment), MediaType.parse("application/json"));
        AuthController token = new AuthController();

        Request request = new Request.Builder()
                .post(body)
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/comment/"+jobID+"/create")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();

        Comment addComment = gson.fromJson(response.body().string(), Comment.class);
        if (!response.isSuccessful()) {
            throw new APIException(response.code() + "Comment can't be created by ID "+jobID);
        }
        System.out.println(addComment);
        return addComment;
    }
    public int findAllComments(int jobID) throws IOException, APIException {
        AuthController token = new AuthController();
        Request request = new Request.Builder()
                .get()
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/comment/"+jobID+"/all")
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        System.out.println(response.code());
        System.out.println(response.body().string());
        return response.code();
    }
}
