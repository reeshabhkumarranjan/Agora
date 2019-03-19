package com.example.agora.model;

import android.os.AsyncTask;

import com.example.agora.controller.Notifiable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    /*
    A singleton class
     */

    private static final Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
    }

    public static Utils getOurInstance() {
        return ourInstance;
    }

    /*
    Getters
     */

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBaseUrlString() {
        return baseUrlString;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    /*
    Setters
     */

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    /*
    Various AsyncTasks (because most of our data resides on the servers.
     */

    public static class SignInTask extends AsyncTask<String, Void, Void>{

        Notifiable controller;
        private String jsonString=null;
        private int responseCode=0;
        private String responseMessage =null;

        public SignInTask(Notifiable controller) {
            this.controller = controller;
        }

        @Override
        protected Void doInBackground(String... strings) {

            try{
                /*
                Setting up the URL
                 */
                final String baseUrlString = "http://agora-rest-api.herokuapp.com";
                final String loginPath = "/api/v1/auth/login";
                String requestType = "POST";
                String urlString = baseUrlString + loginPath;
                URL url = new URL(urlString);

                /*
                Setting the connection
                 */
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("content-type", "application/json");
                // Writing raw data to the connection
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                JSONObject body = new JSONObject();
                body.put("identifier", strings[0]);
                body.put("password", strings[1]);
                String bodyString=body.toString();
                outputStreamWriter.write(bodyString);
                outputStreamWriter.close();


                /*
                Setting the response-code and response-message
                 */
                responseCode=connection.getResponseCode();
                responseMessage =connection.getResponseMessage();

                /*
                Setting the json-string
                 */
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
                    jsonString=resultString;
                }
                else {
                    jsonString="";
                }
            } catch (IOException e) {
                e.printStackTrace(); // for debugging purposes
            } catch (JSONException e) {
                e.printStackTrace(); // for debugging purposes
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(responseCode==HttpURLConnection.HTTP_OK){
                /*
                Extracting information from JSON and opening Dasboard
                 */
                try {

                    // Parsing JSON received from the server
                    JSONObject root=new JSONObject(jsonString);
                    String username=root.getString("username");
                    String email=root.getString("email");
                    String firstName=root.getString("firstName");
                    String lastName=root.getString("lastName");
                    String avatarURL=root.getString("avatarURL");
                    String token=root.getString("token");

                    // Setting fields in the Utils class
                    Utils utils=Utils.getInstance();
                    utils.setUsername(username);
                    utils.setEmail(email);
                    utils.setFirstName(firstName);
                    utils.setLastName(lastName);
                    utils.setAvatarURL(avatarURL);
                    utils.setToken(token);

                    controller.makeToast("Login successful!");
                    controller.notify(true,"Login successful!");

                    //TODO use shared preferences to save the credential and logged-in data

                } catch (JSONException e) {
                    // Ofcourse, the JSON parsing failed
                    controller.notify(false,"Internal error, try again later.");
                }
            }
            else if (responseCode==HttpURLConnection.HTTP_FORBIDDEN){
                /*
                A toast should be generated about improper credentials
                 */
                controller.notify(false,"Invalid credentials!");
            }

            else {
                /*
                A toast message should be generated about internal-error
                 */
                controller.notify(false,"Internal error, try again later.");
            }
        }
    }

    /*
    User-related information
     */
    private boolean loggedIn;
    private String token;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String avatarURL;

    /*
    API-related information
     */
    private final String baseUrlString = "http://agora-rest-api.herokuapp.com";
    private final String loginPath = "/api/v1/auth/login";

    // can be called from the controller
    public void signInRequest(String username, String password, Notifiable controller){
        new SignInTask(controller).execute(username,password);
    }
}
