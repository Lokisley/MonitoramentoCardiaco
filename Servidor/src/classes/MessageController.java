/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import Exceptions.AddPatientListException;
import Exceptions.LoginFailedException;
import Exceptions.LoginRegisteredException;
import Exceptions.ProtocolSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class MessageController {
    
    private Controller controller = Controller.getInstance();
    
    private static MessageController mControl = null;
    
    private MessageController(){
    
    }
    
    public static MessageController getInstance(){
        if (mControl==null){
            mControl = new MessageController();
        }
        return mControl;
    }
    
    /**
     * @param message
     * @param thread
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws LoginFailedException
     * @throws LoginRegisteredException
     * @throws AddPatientListException
     * @throws ProtocolSyntaxException 
     */
    public boolean getMessage(String message, MyThread thread) throws ClassNotFoundException, IOException, FileNotFoundException, LoginFailedException, LoginRegisteredException, AddPatientListException, ProtocolSyntaxException{
        if (!(message.startsWith("ioth "))){
            return false;
        }
        else {
            message = message.substring(5);
            if (message.startsWith("simc ")){
                message = message.substring(5);
                return protocolSimC(message);
            } else if (message.startsWith("monc ")) {
                message = message.substring(5);
                return protocolMonC(message, thread);
            } else {
                throw new ProtocolSyntaxException();
            }
        }
    }
    
    /**
     * @param message
     * @return true if the information of the pacient was successfully updated, false if it wasnt
     * @throws ProtocolSyntaxException
     */
    private boolean protocolSimC(String message) throws ProtocolSyntaxException{
        if (!message.startsWith("senddata ")) {
            throw new ProtocolSyntaxException();
        } else {
            message = message.substring(9);
            
            int id = Integer.parseInt(message.substring(0, message.indexOf(",")));
            message = message.substring(message.indexOf(",") + 1);
            
            String nome = message.substring(0, message.indexOf(","));
            message = message.substring(message.indexOf(",") + 1);
            
            int bpm = Integer.parseInt(message.substring(0, message.indexOf(",")));
            message = message.substring(message.indexOf(",") + 1);
            
            String pressure = message.substring(0, message.indexOf(","));
            message = message.substring(message.indexOf(",") + 1);
            
            boolean movement = Boolean.parseBoolean(message.substring(0, message.indexOf(" ")));
            message = message.substring(message.indexOf(" ") + 1);
            
            if (!message.equals("end")){
                throw new ProtocolSyntaxException();
            } else {
                Patient patient = new Patient(id, bpm, nome, pressure, movement);
                return controller.addPatient(patient);
            }
        }
    }
    
    /**
     * 
     * @param message
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws FileNotFoundException
     * @throws LoginFailedException
     * @throws LoginRegisteredException
     * @throws AddPatientListException
     * @throws ProtocolSyntaxException 
     */
    private boolean protocolMonC(String message, MyThread thread) throws ClassNotFoundException, IOException, FileNotFoundException, LoginFailedException, LoginRegisteredException, AddPatientListException, ProtocolSyntaxException {
        if (message.startsWith("login ")) {
            message = message.substring(0,6);
            return protocolLogin(message);
        } else if (message.startsWith("register ")) {
            message = message.substring(0,9);
            return protocolRegis(message);
        } else if (message.startsWith("userlist ")){
            message = message.substring(0, 9);
            return protocolUserlist(message, thread);
        } else {
            throw new ProtocolSyntaxException();
        }
    }
    
    /**
     * 
     * @param message
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws LoginFailedException if the logging has failed
     */
    private boolean protocolLogin(String message) throws IOException, FileNotFoundException, ClassNotFoundException, LoginFailedException, ProtocolSyntaxException{
        String login = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(",") + 1);
        
        String password = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(" ") + 1);
        
        if (message.equals("end")){
            LoggedUser loggedUser = new LoggedUser(login, password);
            if (controller.verifyLogin(login, password)) {
                return controller.loggingUser(loggedUser);
            } else {
                throw new LoginFailedException();
            }
        } else {
            throw new ProtocolSyntaxException();
        }
    }
    
    /**
     * 
     * @param message 
     * @return true if the user is successfully registered, false if some error has ocured  during the register
     * @throws LoginRegisteredException if the nickname of the login is already in use
     */
    private boolean protocolRegis(String message) throws LoginRegisteredException, ProtocolSyntaxException{
        String login = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(",") + 1);
        
        String password = message.substring(message.indexOf(" "));
        message = message.substring(message.indexOf(" ") + 1);
        
        if (message.equals("end")){
            return controller.registerUser(login, password);
        } else {
            throw new ProtocolSyntaxException();
        }
    }
    
    /**
     * 
     * @param message
     * @throws AddPatientListException if a error has ocurred during the changing of the patient list to the doctor
     * @throws ProtocolSyntaxException if a erorr has ocurred in the syntax of the protocol
     */
    private boolean protocolUserlist(String message, MyThread thread) throws AddPatientListException, ProtocolSyntaxException, IOException{
        if (message.startsWith("ALL")) {
            message = message.substring(message.indexOf(" ") + 1);
            String login = message.substring(message.indexOf(" "));
            message = message.substring(message.indexOf(" ") + 1);
            
            if (message.equals("end")) {
                controller.addPatientToDoctor(controller.getPatientList(), login);
            } else{
                throw new ProtocolSyntaxException();
            }
        } else {
            message = message.substring(message.indexOf("<") + 1);
            
            ArrayList patientsId = new ArrayList();
            while (!message.startsWith(">")){
                patientsId.add(Integer.parseInt(message.substring(message.indexOf(","))));
                message = message.substring(message.indexOf(",") + 1);
            }
            message = message.substring(message.indexOf(" ") + 1);
            
            String login = message.substring(message.indexOf(" "));
            message = message.substring(message.indexOf(" ") + 1);
            
            if (message.equals("end")){
                ArrayList<Patient> patientList = controller.addPatientToDoctor(patientsId, login);
                Iterator i = patientList.iterator();
                String stringPatientList = "<";
                while (i.hasNext()){
                    Patient p = (Patient)i.next();
                    stringPatientList = stringPatientList.concat(
                            "(" + p.getId() + 
                            "," + p.getNome() +
                            "," + p.getBpm() +
                            "," + p.getPressao() +
                            "," + p.isMovimento() + ")");
                }
                stringPatientList = stringPatientList.concat(">");
                
                thread.sendMessage("ioth userlist" + stringPatientList + " end");
            } else {
                throw new ProtocolSyntaxException();
            }
        }
        throw new ProtocolSyntaxException();
    }
}
