package com.example.agora.model;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;

public class Utils {

    private static final Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public static class SignInTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... strings) {

            try{
                final String baseUrlString = "http://agora-rest-api.herokuapp.com";
                final String loginPath = "/api/v1/auth/login";
                String requestType = "POST";
                String urlString = baseUrlString + loginPath;
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("content-type", "application/json");


                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                JSONObject body = new JSONObject();
                body.put("identifier", strings[0]);
                body.put("password", strings[1]);
                String bodyString=body.toString();

//        String bodyString = "{\"identifier\":\"reeshabh\",\"password\":\"ESEe1qM4134r\"}";

                outputStreamWriter.write(bodyString);
                outputStreamWriter.close();
                String resultString = null;

                if (connection.getResponseCode()== HttpURLConnection.HTTP_OK) {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
                    StringBuilder stringBuilder = new StringBuilder("");
                    String currentString = "";

                    while ((currentString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(currentString);
                    }

                    bufferedReader.close();

                    resultString = stringBuilder.toString();
                    Log.v(this.getClass().toString(),resultString);
                    return null;
                }

                System.out.println("sedlyf");
            }

            catch (Exception exception){

            }
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Utils.getInstance().signIn("", "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    User-related information
     */
    private boolean loggedIn;
    private String accessToken;
    private String username;
    private String firstName;
    private String lastName;

    /*
    API-related information
     */
    private final String baseUrlString = "http://agora-rest-api.herokuapp.com";
    private final String loginPath = "/api/v1/auth/login";

    public boolean aSyncSignIn(String username, String password){
        new SignInTask().execute(username,password);
        return true;
    }

    public boolean signIn(String username, String password) throws IOException, JSONException {
        String requestType = "POST";
        String urlString = baseUrlString + loginPath;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("content-type", "application/json");


        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        JSONObject body = new JSONObject();
        body.put("identifier", username);
        body.put("password", password);
        String bodyString=body.toString();

//        String bodyString = "{\"identifier\":\"reeshabh\",\"password\":\"ESEe1qM4134r\"}";

        outputStreamWriter.write(bodyString);
        outputStreamWriter.close();
        String resultString = null;

        if (true) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            StringBuilder stringBuilder = new StringBuilder("");
            String currentString = "";

            while ((currentString = bufferedReader.readLine()) != null) {
                stringBuilder.append(currentString);
            }

            bufferedReader.close();

            resultString = stringBuilder.toString();
            Log.v(this.getClass().toString(),resultString);
            return true;
        }

        System.out.println("sedlyf");
        return false;
    }
}
