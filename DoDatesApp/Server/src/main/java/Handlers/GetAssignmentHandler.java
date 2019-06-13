package Handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.List;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import DAO.FileAssignmentDAO;
import DumbDataHolders.AssignmentListWrap;
import DumbDataHolders.ClassListWrap;
import model.Assignment;
import model.UserClass;

public class GetAssignmentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In GetAssignmentHandler handle");
        boolean success = false;
        FileAssignmentDAO assignmentDAO = new FileAssignmentDAO();
        try{
            //check request type
//            System.out.println("req method is " + exchange.getRequestMethod().toLowerCase());
            if (exchange.getRequestMethod().toLowerCase().equals("get") || exchange.getRequestMethod().toLowerCase().equals("post")){
                //get request data
                InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
                Gson gson = new Gson();
                String classID = gson.fromJson(reqBody, String.class);
                //call service
                List<Assignment> assignmentList = assignmentDAO.getAssignments(classID);
                AssignmentListWrap response = new AssignmentListWrap(assignmentList);
                //check for non error
                if(response != null){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    OutputStreamWriter respBody = new OutputStreamWriter(exchange.getResponseBody());
                    gson = new GsonBuilder().setPrettyPrinting().create();
                    String jsonStr = gson.toJson(response);
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
