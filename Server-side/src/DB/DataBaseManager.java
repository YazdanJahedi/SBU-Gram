package DB;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

import UserAndProfile.*;

public class DataBaseManager {
    private static final DataBase dataBase = DataBase.getInstance();

    public static synchronized void setupDataBase() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/macbookpro/Desktop/University/CE Term2/AP/projects/SBU-Gram/Server-side/src/data.txt");
            ObjectInputStream inFromFile = new ObjectInputStream(fileInputStream);
            dataBase.getData().putAll((ConcurrentHashMap<String, User>) inFromFile.readObject());
            inFromFile.close();
            fileInputStream.close();
        } catch (EOFException | StreamCorruptedException e) {
            System.err.println("~ previous data in dataBase is empty");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void updateDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/macbookpro/Desktop/University/CE Term2/AP/projects/SBU-Gram/Server-side/src/data.txt");
            ObjectOutputStream objToFile = new ObjectOutputStream(fileOutputStream);
            objToFile.writeObject(dataBase.getData()); //writing data
            objToFile.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
