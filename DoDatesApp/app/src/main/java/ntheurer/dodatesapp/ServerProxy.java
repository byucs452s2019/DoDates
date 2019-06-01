package ntheurer.dodatesapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import model.Assignment;
import model.UserClass;

public class ServerProxy {

    String host = "192.168.1.26";//modify based on server location

    public boolean addStudent(String userID, String userName, String password){

        return true;
    }

    public String login(String username) {

        Gson gson;
        try{
            URL url = new URL ("http://" + host + ":8080/student/login");//sets url

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(username);

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
