package com.example.homestay.data.network;

import androidx.annotation.NonNull;

import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.response.TokenResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private DataManager dataManager;

    public TokenInterceptor(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public synchronized Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request authenticationRequest = originalRequest.newBuilder()
                .addHeader("x-access-token","Bearer " + dataManager.getAccessToken()).build();
        Response initialResponse = chain.proceed(authenticationRequest);

        if (initialResponse.code() == 401) {
            String data = "{\"refresh_token\":\"" + dataManager.getRefreshToken() + "\"}";
            boolean refreshResult = refreshToken(data);
            if (refreshResult) {
                Request newAuthenticationRequest = originalRequest.newBuilder().addHeader("x-access-token", "Bearer " + dataManager.getAccessToken()).build();
                return chain.proceed(newAuthenticationRequest);
            } else {
                return null;
            }
        } else {
            return initialResponse;
        }
    }

    private boolean refreshToken(String data) throws IOException {
        URL refreshUrl = new URL("token");
        HttpURLConnection urlConnection = (HttpURLConnection) refreshUrl.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
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

        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            TokenResponse tokenResponse = gson.fromJson(response.toString(), TokenResponse.class);
            dataManager.setAccessToken(tokenResponse.getToken());

            return true;

        } else {
            //cannot refresh
            return false;
        }

    }

}
