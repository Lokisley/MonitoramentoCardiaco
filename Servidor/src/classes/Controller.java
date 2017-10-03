/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class Controller {
    
    private static Controller controller;
    private ArrayList<User> userList = new ArrayList<User>();
    private LoggedUser logged = null;
    private PriorityBlockingQueue patientList = new PriorityBlockingQueue();
    
    private Controller () {
        
    }
    
    public static Controller getInstance () {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    /**
     * Gets the list of the users already registered
     * 
     * @return the list of all users
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public ArrayList<User> getUserList() throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fileInput = new FileInputStream("data.txt");
        ObjectInputStream objInput = new ObjectInputStream(fileInput);
        userList = (ArrayList<User>)objInput.readObject();
        objInput.close();
        return userList;
    }
    
    /**
     * Save the user data in a file
     * 
     * @param userList
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveUsers(ArrayList<User> userList) throws FileNotFoundException, IOException {
        FileOutputStream fileOutput = new FileOutputStream("data.txt");
        ObjectOutputStream objOutput = new ObjectOutputStream(fileOutput);
        objOutput.writeObject(userList);
        objOutput.close();
    }
    
    /**
     * Made the login of a user, and check if it is registered
     * 
     * @param login the nickname of the user 
     * @param password the password
     * @param ip the current ip the user is using
     * @param gate the current gate the user is using
     * @return true if the user is in the list of registered users, false if isnt
     */
    public boolean login (String login, String password, String ip, int gate) {
        logged = new LoggedUser(login, password, ip, gate);
        return userList.contains(logged);
    }
    
    /**
     * Made the register of a user
     * 
     * @param login the nickname of the user
     * @param password the password of the user
     * @return true if the user gets registered, false if the login is already in use
     */
    public boolean registerUser (String login, String password) {
        User user = new User(login, password);
        if (userList.contains(user)){
            return false;
        } else {
            userList.add(user);
            return true;
        }
    }
}
