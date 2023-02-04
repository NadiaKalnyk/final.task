package ua.hillel.rest;

import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;

public class AuthController {
    private final String username = "nk_user0402";
    private final String password = "123456qQ";
    private final String confirmPassword = "123456qQ";
    public String userSignsUp() throws IOException, APIException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("confirmPassword", confirmPassword);


        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/auth/signup")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();

        JSONObject responseObject = new JSONObject(response.body().string());
        if (response.code()==500){
            throw new APIException(response.code() + "User already has registered with this username" + response.body());
        }
        System.out.println(response.code());
        System.out.printf(responseObject.getString("message").toString());
        System.out.println(responseObject);
        return responseObject.getString("message").toString();
    }
    public boolean userSignsIn() throws IOException, APIException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("username", username);
        jsonObject.put("password", password);

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/auth/signin")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();

        JSONObject responseObject = new JSONObject(response.body().string());
        boolean flag = response.isSuccessful();
        if (flag == false){
            throw new APIException(response.code() + "User is not registered" + response.body());
        }

        System.out.println(response.code());
        System.out.printf(String.valueOf(response.isSuccessful()));
        System.out.println(responseObject);
        return response.isSuccessful();

    }
    public String getToken() throws IOException, APIException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .post(body)
                .url("https://freelance.lsrv.in.ua/api/auth/signin")
                .build();
        Response response = new OkHttpClient().newCall(request).execute();

        JSONObject responseObject = new JSONObject(response.body().string());
        boolean flag = response.isSuccessful();
        if (flag == false){
            throw new APIException(response.code() + "User is not registered" + response.body());
        }

        System.out.println(response.code());
        System.out.printf(String.valueOf(response.isSuccessful()));
        return responseObject.get("token").toString();
    }
    public String getUsername(){
        return this.username;
    }
}
