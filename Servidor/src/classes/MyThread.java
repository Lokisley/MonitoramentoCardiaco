/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import Exceptions.AddPatientListException;
import Exceptions.LoginFailedException;
import Exceptions.LoginRegisteredException;
import Exceptions.PatientNotFoundException;
import Exceptions.ProtocolSyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class MyThread extends Thread{
    
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private MessageController mController = MessageController.getInstance();
    
    public MyThread (Socket socket) throws IOException{
        this.socket = socket;
        
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }
    
    @Override
    public void run () {
        while (true) {
            try {
                boolean message = mController.getMessage((String)receiveMessage(), this);
                if (message){
                    sendMessage("ioth ok");
                }
            } catch (IOException  | ClassNotFoundException ex) {
                System.out.println("An unexpected error happened");
            } catch (LoginFailedException ex) {
                try {
                    sendMessage("ioth error login");
                } catch (IOException ex1) {
                    System.out.println("An unexpected error during the response");
                }
            } catch (LoginRegisteredException ex) {
                try {
                    sendMessage("ioth error register");
                } catch (IOException ex1) {
                    System.out.println("An unexpected error during the response");
                }
            } catch (AddPatientListException ex) {
                try {
                    sendMessage("ioth error addpatient");
                } catch (IOException ex1) {
                    System.out.println("An unexpected error during the response");
                }
            } catch (ProtocolSyntaxException ex) {
                try {
                    sendMessage("ioth error syntax");
                } catch (IOException ex1) {
                    System.out.println("An unexpected error during the response");
                }
            }
        }
    }
        
    public void sendMessage (Object obj) throws IOException {
        output.writeObject(obj);
    }
    
    public Object receiveMessage () throws IOException, ClassNotFoundException {
        return input.readObject();
    }
    
}
