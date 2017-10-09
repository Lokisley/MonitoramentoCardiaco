/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import Exceptions.LoginRegisteredException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class Controller {
    
    private static Controller controller;
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<LoggedUser> loggedUserList = new ArrayList<>();
    private ArrayList<Patient> patientList = new ArrayList<>();
    
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
     * Inserts the object on the list of logged users
     * 
     * @param logging the object of a user that is logging
     */
    public void loggingUser(LoggedUser logging) {
        loggedUserList.add(logging);
    }
    
    /**
     * Made the register of a user
     * 
     * @param login the nickname of the user
     * @param password the password of the user
     * @return true if the user gets registered, false if the login is already in use
     */
    public boolean registerUser (String login, String password) throws LoginRegisteredException {
        User user = new User(login, password);
        User checkUser;
        Iterator i = userList.iterator();
        while (i.hasNext()){
            checkUser = (User)i.next();
            if (checkUser.getLogin().equals(login)){
                throw new LoginRegisteredException();
            }
        }
        userList.add(user);
        return true;
    }
    
    /**
     * 
     * @param login the nickname of the user
     * @param password the password of the user
     * @return true if the user is in the list of the registered users, false if isnt
     */
    public boolean verifyLogin(String login, String password){
        Iterator i = userList.iterator();
        User user;
        while(i.hasNext()){
            user = (User)i.next();
            return (user.getLogin().equals(login) && user.getPassword().equals(password));
        }
        return false;
    }
}
