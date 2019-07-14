package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Assignment;

public class AssignmentDAO extends ParentDAO {
    public boolean addAssignment(String assignmentID, String AssignmentName, String ClassID, String DueDate, String DoDate) {
        openConnection();
        try {
            stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO Assignments\n" +
                    "VALUES (\n" +
                    "\t'" + assignmentID + "',\n" +
                    "\t'" + AssignmentName + "',\n" +
                    "\t'" + ClassID + "',\n" +
                    "\t'" + DueDate + "',\n" +
                    "\t'" + DoDate + "'\n" +
                    ");");

            closeStatement();
            closeConnection(true);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            return false;
        }
    }

    public List<Assignment> getAssignments(String classID){
        openConnection();
        try{
            stmt = connection.createStatement();

            String query = "SELECT * " +
                    "FROM Assignments " +
                    "WHERE ClassID = \"" + classID + "\"";

            ResultSet rs = stmt.executeQuery(query);
            List<Assignment> allAssignments = new ArrayList<Assignment>();
            while(rs.next()){
                Assignment myAssignment = new Assignment(rs.getString("AssignmentID"), rs.getString("AssignmentName"), rs.getString("DueDate"), rs.getString("DoDate"));
                allAssignments.add(myAssignment);
            }
            closeStatement();
            closeConnection(true);
            return allAssignments;
        } catch (Exception e){
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            List<Assignment> badlist = new ArrayList<Assignment>();
            return badlist;
        }
    }

    public boolean removeAssignment(String assignmentID) {
        openConnection();
        try {
            stmt = connection.createStatement();

            String removeAssignments = "DELETE FROM Assignments WHERE AssignmentID = \'" + assignmentID + "\';";
            stmt.executeUpdate(removeAssignments);

            closeStatement();
            closeConnection(true);
            return true;
        } catch (Exception e) {
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
