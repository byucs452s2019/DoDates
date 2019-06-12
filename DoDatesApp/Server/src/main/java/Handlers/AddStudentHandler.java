package Handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import DAO.StudentsDAO;
import DumbDataHolders.StudentDumbData;

public class AddStudentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In AddStudentHandler handle");

        boolean success = false;
        StudentsDAO studentsDAO = new StudentsDAO();
        try{
            //check request type
//            System.out.println("req method is " + exchange.getRequestMethod().toLowerCase());
            if (exchange.getRequestMethod().toLowerCase().equals("post") || exchange.getRequestMethod().toLowerCase().equals("get")){
                //get request data
                InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
                Gson gson = new Gson();
                StudentDumbData student = gson.fromJson(reqBody, StudentDumbData.class);
                //call service
                String studentID = student.userName + "1234";
                Boolean response = studentsDAO.addStudent(studentID, student.userName, student.password);  //response is the userID
                //check for non error
                if(response == true){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonStr = gson.toJson(studentID);
                    respBody.write(jsonStr);
                    respBody.flush();
                    respBody.close();
                    success = true;
                }
                //returns error
                else{
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
                    OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson = new GsonBuilder().setPrettyPrinting().create();
                    //description of error
                    String jsonStr = gson.toJson(response);
                    respBody.write(jsonStr);
                    respBody.flush();
                    respBody.close();
                    success = true;
                }
            }
            //returns for bad input
            if(!success){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonStr = gson.toJson("login in failed");
                respBody.write(jsonStr);
                respBody.flush();
                respBody.close();
            }


        }catch(IOException e){
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}
