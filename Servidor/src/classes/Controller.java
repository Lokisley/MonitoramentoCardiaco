/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import Exceptions.AddPatientListException;
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
    private final ArrayList<LoggedUser> loggedUserList = new ArrayList<>();
    private final ArrayList<Patient> patientList = new ArrayList<>();
    
    private Controller () {   
    }
    
    public static Controller getInstance () {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
    /**
     * @return the patient list
     */
    public ArrayList getPatientList (){
        return patientList;
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
    public boolean loggingUser(LoggedUser logging) {
        return loggedUserList.add(logging);
    }
    
    /**
     * Made the register of a user
     * 
     * @param login the nickname of the user
     * @param password the password of the user
     * @return true if the user gets registered, false if the login is already in use
     * @throws Exceptions.LoginRegisteredException
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
    
    /**
     * Add patients in the object patientList of the logged doctor 
     * @param patientIdList - the patient list to be added
     * @param login - the nickname of the doctor to receive the list of patients
     * @return
     * @throws AddPatientListException 
     */
    public ArrayList<Patient> addPatientToDoctor (ArrayList patientIdList, String login) throws AddPatientListException {
        Iterator iId = patientIdList.iterator();
        Iterator iPatientRegistered = this.patientList.iterator();
        Iterator iUser = loggedUserList.iterator();
        ArrayList<Patient> patientUserList = new ArrayList();
        LoggedUser user;
        Patient patient;
        int id;
        while (iId.hasNext()){
            id = (Integer)iId.next();
            while(iPatientRegistered.hasNext()){
                patient = (Patient)iPatientRegistered.next();
                if (id == patient.getId()) {
                    patientUserList.add(patient);
                }
            }
        }
        while (iUser.hasNext()) {
            user = (LoggedUser)iUser.next();
            if (user.getLogin().equals(login)){
                user.setPatientList(patientUserList);
                return user.getPatientList();
            }
        }
        throw new AddPatientListException();
    }
    
    /**
     * Add a patient on the list or refresh his informations if he is already on the list
     * @param patient
     * @return 
     */
    public boolean addPatient (Patient patient){
        Iterator i = patientList.iterator();
        Patient p;
        while (i.hasNext()){
            p = (Patient)i.next();
            if (p.getId() == patient.getId()){
                patientList.remove(p);
                patientList.add(patient);
                return true;
            }
        }
        return patientList.add(patient);
    }
}
