package com.example.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;


import org.json.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.example.demo.login.aoth;
@RestController
public class test {
        @Autowired
        static String result[] = aoth();
        private static final String API_URL = result[0];
        private static final  String API_KEY = result[1];
       static String endpoint="/services/apexrest/leadsagentcoverted?converted";

       @GetMapping(value = "/test")
       public String data() throws Exception
       {
           String res = getResponse().toString();
           String res1 = res;
           return res1;
       }

        @Nullable
        public static JSONArray getResponse() throws Exception {


//            System.out.println(API_URL);
            URL url = new URL(API_URL + endpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setRequestMethod("GET");
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
                res = res.substring(1, res.length()-1);
                    JSONArray json = new JSONArray(res);


                return json;
                }
            else
                    return null;


            }

        }