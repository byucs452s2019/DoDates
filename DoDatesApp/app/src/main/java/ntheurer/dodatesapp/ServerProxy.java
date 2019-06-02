package ntheurer.dodatesapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import model.Assignment;
import model.UserClass;

public class ServerProxy {

    protected String host;

    public ServerProxy() {
        host = "10.0.2.2";//modify based on server location //usually 10.0.2.2 or 127.0.0.1 or localhost or ip address maybe
        //If you changed the host value, make sure to change the value in res/xml/network_security_config.xml
    }

    public boolean addStudent(String userID, String userName, String password){

        return true;
    }

    public String login(String username) {

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/student/login";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(username);

//            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();//set GET/POST
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.connect();//sends request

            OutputStreamWriter reqBody = new OutputStreamWriter(http.getOutputStream());
            reqBody.write(reqData);
            reqBody.flush();
            reqBody.close();

            //checking response;
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if login successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                String response = gson.fromJson(respBody, String.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return null;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addClass(String classID, String className, String colorName, String userID) {

        return true;
    }

    public List<UserClass> getClasses(String userID){

        return null;
    }

    public boolean addAssignment(String assignmentID, String AssignmentName, String ClassID, String DueDate, String DoDate) {

        return true;
    }

    public List<Assignment> getAssignments(String classID){

        return null;
    }
}
