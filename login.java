package com.example.demo;


import java.io.IOException;

import com.github.tsohr.JSONException;
import com.github.tsohr.JSONObject;
import com.github.tsohr.JSONTokener;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;


    public class login {
        static final String USERNAME="darshan.yadav@credextechnology.com";
        static final String PASSWORD="Credex@2023hEJBJK5bVVEeZciIVO9Hs2YsS";
        static final String LOGINURL="https://credextechnology7-dev-ed.develop.my.salesforce.com";
        static final String GRANTSERVICE="/services/oauth2/token?grant_type=password";
        static final String CLIENTID="3MVG9n_HvETGhr3DRMz8SCHXq6cXrYqRYUSJMZIMfRZQ2qBv2NxS0JMphnAT0Nm_2XJ3wuqrM8q8vrztxRFVs";
        static final String CLIENTSECRET= "5E95FCEFCA68552D78C4BF3235F1FC7AE7002C3A2BEC3EFAB135942E6373F850";
        static String loginAccessToken =null;
        static  String loginInstanceUrl= null;

        public static String[] aoth()
        {
            HttpClient httpclient= HttpClientBuilder.create().build();
            String loginurl= LOGINURL+ GRANTSERVICE+"&client_id=" + CLIENTID +"&client_secret="+ CLIENTSECRET+ "&username=" +
                    USERNAME +"&password=" + PASSWORD;
            System.out.println(loginurl);
            HttpPost httpPost= new HttpPost(loginurl);
            HttpResponse response=null;
            try {
                response = httpclient.execute(httpPost);
            }
            catch (ClientProtocolException cpException)
            {
                cpException.printStackTrace();
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            final int statusCode= response.getStatusLine().getStatusCode();
            if(statusCode !=HttpStatus.SC_OK)
            {
                System.out.println("Error authenticating to force.com:"+ statusCode);
                // Error is in EntityUtils.tostring(response.getEntity())

            }
            String getResult=null;
            try
            {
                getResult=EntityUtils.toString(response.getEntity());
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            JSONObject jsonObject=null;
//            String loginAccessToken =null;
//            String loginInstanceUrl= null;
            try{
                jsonObject=(JSONObject) new JSONTokener(getResult).nextValue();
                loginAccessToken = jsonObject.getString("access_token");
                loginInstanceUrl = jsonObject.getString("instance_url");
            }
            catch (JSONException jsonException)
            {
                jsonException.printStackTrace();
            }
            System.out.println(response.getStatusLine());
            System.out.println("Successful login");
            JSONObject root = new JSONObject();
            root.put("loginInstanceUrl", loginInstanceUrl);
            root.put("loginAccessToken", loginAccessToken);
            System.out.println("instance url" + loginInstanceUrl);
            System.out.println("access token/session ID:" +loginAccessToken);
            //release connection
            httpPost.releaseConnection();


            return new String[]{loginInstanceUrl,loginAccessToken};
        }


    }
