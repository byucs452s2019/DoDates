package ntheurer.dodatesapp.DAO;

import java.sql.*;
import java.util.*;


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
