package ntheurer.dodatesapp.DAO;

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
