/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import Exceptions.LoginFailedException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class MessageController {
    
    private Controller controller = Controller.getInstance();
    
    private static MessageController mControl = null;
    
    private MessageController(){
    
    }
    
    public MessageController getInstance(){
        if (mControl==null){
            mControl = new MessageController();
        }
        return mControl;
    }
        
    public boolean getMessage(String message) throws ClassNotFoundException, IOException, FileNotFoundException, LoginFailedException{
        if (!(message.startsWith("ioth "))){
            return false;
        }
        else {
            message = message.substring(4);
            if (message.startsWith("simc ")){
                message = message.substring(4);
                return protocolSimC(message);
            } else if (message.startsWith("monc ")) {
                message = message.substring(4);
                return protocolMonC(message);
            } else {
                return false;
            }
        }
    }
    
    private boolean protocolSimC(String message){
        if (!message.startsWith("senddata ")) {
            return false;
        } else {
            message = message.substring(message.indexOf(" "));
            
            int id = Integer.parseInt(message.substring(0, message.indexOf(",")));
            message = message.substring(message.indexOf(","));
            
            int bpm = Integer.parseInt(message.substring(0, message.indexOf(",")));
            message = message.substring(message.indexOf(","));
            
            String pressure = message.substring(0, message.indexOf(","));
            message = message.substring(message.indexOf(","));
            
            boolean movement = Boolean.parseBoolean(message.substring(0, message.indexOf(" ")));
            message = message.substring(message.indexOf(" "));
            
            if (!message.equals("end")){
                return false;
            } else {
                Patient patient = new Patient (id, bpm, pressure, movement);
            }
        }
        return false;
    }
    
    private boolean protocolMonC(String message) throws ClassNotFoundException, IOException, FileNotFoundException, LoginFailedException {
        if (message.startsWith("login ")) {
            message = message.substring(0,5);
            return protocolLogin(message);
        } else if (message.startsWith("regis ")) {
            message = message.substring(0,5);
            return protocolRegis(message);
        } else {
            return false;
        }
    }
    
    private boolean protocolLogin(String message) throws IOException, FileNotFoundException, ClassNotFoundException, LoginFailedException{
        String ip = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(","));
        
        int gate = Integer.parseInt(message.substring(message.indexOf(",")));
        message = message.substring(message.indexOf(","));
        
        String login = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(","));
        
        String password = message.substring(message.indexOf(","));
        message = message.substring(message.indexOf(" "));
        
        if (message.equals("end")){
            LoggedUser loggedUser = new LoggedUser(login, password, ip, gate);
            User user = new User(login, password);
            if (controller.getUserList().contains(user)){
                return true;
            } else {
                throw new LoginFailedException();
            }
        } else {
            return false;
        }
    }
    
    private boolean protocolRegis(String message){
        return false;
    }
}
