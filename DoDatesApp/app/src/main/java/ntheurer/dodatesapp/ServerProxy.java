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

import DumbDataHolders.AssignmentDumbData;
import DumbDataHolders.AssignmentListWrap;
import DumbDataHolders.ClassDumbData;
import DumbDataHolders.ClassListWrap;
import DumbDataHolders.StudentDumbData;
import model.Assignment;
import model.UserClass;

public class ServerProxy {

    protected String host;

    public ServerProxy() {
        host = "10.0.2.2";//modify based on server location //usually 10.0.2.2 or 127.0.0.1 or localhost or ip address maybe
        //If you changed the host value, make sure to change the value in res/xml/network_security_config.xml
    }

    public boolean addStudent(String userID, String userName, String password){
        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/student/add";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            StudentDumbData dumbData = new StudentDumbData(userID, userName, password);
            String reqData = gson.toJson(dumbData);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add student successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
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
                String response = gson.fromJson(respBody, String.class); //response should be the userID
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

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/class/add";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            ClassDumbData dumbData = new ClassDumbData(classID, className, colorName, userID);
            String reqData = gson.toJson(dumbData);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add class successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateClass(String classID, String className, String colorName, String userID) {

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/class/update";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            ClassDumbData dumbData = new ClassDumbData(classID, className, colorName, userID);
            String reqData = gson.toJson(dumbData);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add class successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteClass(String classID){

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/class/delete";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(classID);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add class successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<UserClass> getClasses(String userID){

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/class/get";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(userID);

//            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();//set GET/POST
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.connect();//sends request

            OutputStreamWriter reqBody = new OutputStreamWriter(http.getOutputStream());
            reqBody.write(reqData);
            reqBody.flush();
            reqBody.close();

            //checking response;
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if class retrieval was successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                ClassListWrap response = gson.fromJson(respBody, ClassListWrap.class);
                respBody.close();
                http.disconnect();
                return response.classList;
            }
            else{
                return null;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

        return null;

    }

    public boolean addAssignment(String assignmentID, String assignmentName, String classID, String dueDate, String doDate) {

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/assignment/add";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            AssignmentDumbData dumbData = new AssignmentDumbData(assignmentID, assignmentName, classID, dueDate, doDate);
            String reqData = gson.toJson(dumbData);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add assignment successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Assignment> getAssignments(String classID){

        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/assignment/get";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(classID);

//            HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
            HttpURLConnection http = (HttpURLConnection)url.openConnection();//set GET/POST
            http.setRequestMethod("GET");
            http.setDoOutput(true);
            http.connect();//sends request

            OutputStreamWriter reqBody = new OutputStreamWriter(http.getOutputStream());
            reqBody.write(reqData);
            reqBody.flush();
            reqBody.close();

            //checking response;
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if assignment retrieval was successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                AssignmentListWrap response = gson.fromJson(respBody, AssignmentListWrap.class);
                respBody.close();
                http.disconnect();
                return response.assignmentList;
            }
            else{
                return null;
            }

        }catch(IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateAssignment(String assignmentID, String assignmentName, String classID, String dueDate, String doDate) {
        return true;
    }

    public boolean deleteAssignment(String assignmentID) {
        Gson gson;
        try{
            String urlStr = "http://" + host + ":8080/assignment/delete";
            System.out.println("urlStr = \'" + urlStr + "\'");
            URL url = new URL (urlStr);//sets url
//            URL url = new URL ("https://" + ":8080/student/login");

            gson = new GsonBuilder().setPrettyPrinting().create();//makes request body
            String reqData = gson.toJson(assignmentID);

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
            if(http.getResponseCode() == HttpURLConnection.HTTP_OK){//checks if add class successful
                InputStreamReader respBody = new InputStreamReader(http.getInputStream());
                gson = new Gson();
                Boolean response = gson.fromJson(respBody, Boolean.class);
                respBody.close();
                http.disconnect();
                return response;
            }
            else{
                return false;
            }

        }catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
