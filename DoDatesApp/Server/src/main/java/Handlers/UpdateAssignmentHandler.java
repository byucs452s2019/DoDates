package Handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import DAO.AssignmentDAO;
import DumbDataHolders.AssignmentDumbData;

public class UpdateAssignmentHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("In UpdateAssignmentHandler handle");
        boolean success = false;
        AssignmentDAO assignmentDAO = new AssignmentDAO();
        try{
            //check request type
            if (exchange.getRequestMethod().toLowerCase().equals("get") || exchange.getRequestMethod().toLowerCase().equals("post")){
                //get request data
                InputStreamReader reqBody = new InputStreamReader(exchange.getRequestBody());
                Gson gson = new Gson();
                AssignmentDumbData dumbData = gson.fromJson(reqBody, AssignmentDumbData.class);
                //call service
                Boolean response = assignmentDAO.updateAssignment(dumbData.assignmentID, dumbData.AssignmentName, dumbData.DueDate, dumbData.DoDate);
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
                String jsonStr = gson.toJson("update assignment failed");
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
