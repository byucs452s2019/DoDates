import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import Handlers.AddAssignmentHandler;
import Handlers.AddClassHandler;
import Handlers.AddStudentHandler;
import Handlers.DeleteClassHandler;
import Handlers.GetAssignmentHandler;
import Handlers.GetClassHandler;
import Handlers.LoginHandler;
import Handlers.UpdateClassHandler;

public class Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;
    private HttpServer server;

    //most of the work
    void run(int portNumber){

        //start the server
        System.out.println("Initializing HTTP Server");

        try{

            //Create new HTTP Server object
            server = HttpServer.create(
                    new InetSocketAddress(portNumber),
                    MAX_WAITING_CONNECTIONS);
        }catch(IOException e){
            e.printStackTrace();
            return;
        }

        //using default "executor"
        server.setExecutor(null);

        System.out.println("Creating contexts");

        // create and install the HTTP handler for the given URL path
        server.createContext("/student/add", new AddStudentHandler());
        server.createContext("/student/login", new LoginHandler());
        server.createContext("/assignment/add", new AddAssignmentHandler());
        server.createContext("/assignment/get", new GetAssignmentHandler());
        server.createContext("/class/add", new AddClassHandler());
        server.createContext("/class/update", new UpdateClassHandler());
        server.createContext("/class/delete", new DeleteClassHandler());
        server.createContext("/class/get", new GetClassHandler());

        System.out.println("Starting server");
        server.start();

    }


//main method
    public static void main(String args[]){
        int portNumber = 8080;
        new Server().run(portNumber);
    }
}
