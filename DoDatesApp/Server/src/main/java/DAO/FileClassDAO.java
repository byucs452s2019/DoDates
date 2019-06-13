package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.UserClass;

public class FileClassDAO {

    private File classDir;

    public FileClassDAO() {
        classDir = new File("classes");
        classDir.mkdir();
    }

    protected void write(String data, String filename) throws IOException {
        try(PrintWriter out = new PrintWriter(filename)){
            out.println(data);
        }
    }

    protected String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }

        stringBuilder.deleteCharAt(stringBuilder.length() -1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public boolean addClass(String classID, String className, String colorName, String userID){
        String data = classID + "\n" + className + "\n" + colorName + "\n" + userID;
        try{
            write(data, classDir + File.separator + className);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<UserClass> getClasses(String userID){
        File folder = new File("\\DoDates CS452\\DoDates\\DoDatesApp\\classes");

        File[] listOfFiles = folder.listFiles();
        List<UserClass> classList = new ArrayList<>();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                try{
                    String tempClass[] = readFile(file.toString()).split("\\r?\\n");
                    UserClass myClass = new UserClass(tempClass[0], tempClass[1], tempClass[2]);
                    if(tempClass[3].equals(userID)){
                        classList.add(myClass);
                    }
                 } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return classList;
    }
}
