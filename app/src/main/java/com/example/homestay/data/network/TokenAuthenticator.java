package com.example.homestay.data.network;


import androidx.annotation.NonNull;

import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.model.Token;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;


public class TokenAuthenticator implements Authenticator {

    private DataManager dataManager;
    private ApiHeader apiHeader;

    public TokenAuthenticator(DataManager dataManager, ApiHeader apiHeader) {
        this.dataManager = dataManager;
        this.apiHeader = apiHeader;
    }

    @Override
    public Request authenticate(@NonNull Route route,@NonNull Response response) throws IOException {

        if(response.code() == 401){
            boolean refreshResult = refreshToken();
            if (refreshResult) {
                return response.request().newBuilder()
                        .header("x-access-token", "Bearer "+ dataManager.getAccessToken())
                        .build();

            } else {
                return null;
            }
        }
        return null;
    }

    private boolean refreshToken() throws IOException{
        URL refreshUrl=new URL(ApiEndPoint.ENDPOINT_REFRESH_TOKEN);

        String data = "{\"refresh_token\":\"" + dataManager.getRefreshToken() + "\"}";

        HttpURLConnection urlConnection = (HttpURLConnection) refreshUrl.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type","application/json");
        urlConnection.setRequestProperty("Accept","application/json");
        urlConnection.setUseCaches(false);

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(data);
        writer.flush();
        writer.close();
        os.close();

        int responseCode = urlConnection.getResponseCode();

        if(responseCode==200){
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            Token token = gson.fromJson(response.toString(), Token.class);
            dataManager.setAccessToken(token.getToken());
            apiHeader.getProtectedApiHeader().setAccessToken(token.getToken());
            return true;

        } else {
            //cannot refresh
            return false;
        }

    }
}
