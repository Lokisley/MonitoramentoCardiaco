/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class MyThread extends Thread{
    
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    
    public MyThread (Socket socket) throws IOException{
        this.socket = socket;
        
        input = new ObjectInputStream(socket.getInputStream());
        output = new ObjectOutputStream(socket.getOutputStream());
    }
    
        
    public void sendMessage (Object obj) throws IOException {
        output.writeObject(obj);
    }
    
    public Object receiveMessage () throws IOException, ClassNotFoundException {
        return input.readObject();
    }
    
}
