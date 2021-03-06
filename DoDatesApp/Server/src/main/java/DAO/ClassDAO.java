package DAO;

import java.sql.*;
import java.util.*;

import model.UserClass;


public class ClassDAO extends ParentDAO{
    public boolean addClass(String classID, String className, String colorName, String userID){
        openConnection();
        try{
            stmt = connection.createStatement();

            String addToClassTable = "INSERT INTO Classes\n" +
                    "VALUES (\n" +
                    "\t'" + classID + "',\n" +
                    "\t'" + className + "',\n" +
                    "\t'" + colorName + "'\n" +
                    ");";

            String addToStudentClass = "INSERT INTO StudentClasses\n" +
                    "VALUES (\n" +
                    "\t'" + userID + "',\n" +
                    "\t'" + classID + "'\n" +
                    ");";

            stmt.addBatch(addToClassTable);
            stmt.addBatch(addToStudentClass);
            stmt.executeBatch();

            closeStatement();
            closeConnection(true);
            return true;
        } catch(Exception e){
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            return false;
        }
    }

    public List<UserClass> getClasses(String userID){
        openConnection();
        try{
            stmt = connection.createStatement();

            String query = "SELECT * FROM Classes " +
                    "INNER JOIN StudentClasses " +
                    "ON Classes.ClassID = StudentClasses.ClassID " +
                    "WHERE StudentClasses.UserID = \"" + userID + "\";";

            ResultSet rs = stmt.executeQuery(query);
            List<UserClass> allClasses = new ArrayList<UserClass>();
            while(rs.next()){
                String cID = rs.getString(1);
                String className = rs.getString("ClassName");
                String colorName = rs.getString("ColorName");
                UserClass myClass = new UserClass(cID, className, colorName);
                allClasses.add(myClass);
            }
            closeStatement();
            closeConnection(true);
            return allClasses;
        } catch(Exception e){
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            List<UserClass> badlist = new ArrayList<UserClass>();
            return badlist;
        }
    }

    private void closeStatement() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
