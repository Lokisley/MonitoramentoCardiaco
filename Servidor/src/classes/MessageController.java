/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

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
        
    public boolean getMessage(String message){
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
            int bpm = Integer.parseInt(message.substring(0, message.indexOf(",")));
            message = message.substring(message.indexOf(","));
            
            String pressure = message.substring(0, message.indexOf(","));
            message = message.substring(message.indexOf(","));
            
            boolean movement = Boolean.parseBoolean(message.substring(0, message.indexOf(" ")));
            message = message.substring(message.indexOf(" "));
            
            if (!message.equals("end")){
                return false;
            } else {
                Patient patient = new Patient (bpm, pressure, movement);
            }
        }
        return false;
    }
    
    /*
    private boolean protocolOpenc (String message) {
        String ip;
        int gate;
        String protocol = message.substring(0, 3);
        message = message.substring(3);
        if (!protocol.equals("-ip")){
            return false;
        } else {
            ip = message.substring(0, message.indexOf("-"));
            message = message.substring(message.indexOf("-"));
            protocol = message.substring(0, 3);
            message = message.substring(3);
            if (protocol.equals("-gt")){
                return false;
            } else {
                gate = Integer.parseInt(message.substring(0, message.indexOf("-")));
                message = message.substring(message.indexOf("-"));
                if (!message.equals("-end")) {
                    return false;
                } else {
                    
                }
            }
        }
        return false;
    }
    */
    
    private boolean protocolMonC(String message){
        String protocol = message.substring(0, 4);
        message = message.substring(4);
        switch (protocol) {
            case "lgin":
                break;
            case "rgis":
                break;
            default:
                return false;
        }
        return false;
    }
}
