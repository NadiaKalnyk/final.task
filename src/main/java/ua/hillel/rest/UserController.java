package ua.hillel.rest;

import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONObject;
import ua.hillel.rest.entities.User;

import java.io.IOException;

public class UserController{
        public User updateUserWithGSON(User user) throws IOException, APIException {
        Gson gson = new Gson();

        RequestBody body = RequestBody.create(gson.toJson(user), MediaType.parse("application/json"));
        AuthController token = new AuthController();

        Request request = new Request.Builder()
                .post(body)
                .header("Authorization", token.getToken())
                .url("https://freelance.lsrv.in.ua/api/user/update")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        User updateUser = gson.fromJson(response.body().string(), User.class);

        System.out.println(updateUser);
        return updateUser;
        }

        public int findUserById(int userID) throws  IOException, APIException{
                AuthController token = new AuthController();
                Request request = new Request.Builder()
                        .get()
                        .header("Authorization", token.getToken())
                        .url("https://freelance.lsrv.in.ua/api/user/" + userID)
                        .build();

                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();

                System.out.println(response.code());
                System.out.println(response.body().string());
                return response.code();
        }
        public String checkUserInformation() throws  IOException, APIException{
                AuthController token = new AuthController();
                Request request = new Request.Builder()
                        .get()
                        .header("Authorization", token.getToken())
                        .url("https://freelance.lsrv.in.ua/api/user/")
                        .build();

                OkHttpClient client = new OkHttpClient();
                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) {
                        throw new APIException(response.code()+"User is not registered "+response.body().toString());
                }
                JSONObject responseObject = new JSONObject(response.body().string());
                System.out.println(response.code());
                System.out.println(responseObject);
                return responseObject.get("id").toString();
        }
}
