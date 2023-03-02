package com.example.demo;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.demo.login.aoth;

public class Connection {
    static String[] result = aoth();
    private static final String API_URL = result[0];
    private static final String API_KEY = result[1];

    @Nullable
    public static JSONArray getResponse(String endpoint) throws Exception {
        URL url = new URL(API_URL + endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLin;
            StringBuffer response = new StringBuffer();

            while ((inputLin = in.readLine()) != null) {
                response.append(inputLin);
            }
            in.close();
            // System.out.println(json.toString());
            String res = response.toString().replace("\\", "");
            res = res.substring(1, res.length() - 1);
            JSONArray json = new JSONArray(res);


            return json;
        } else
            return null;


    }
}
