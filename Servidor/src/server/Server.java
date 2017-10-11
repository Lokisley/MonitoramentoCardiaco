/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import classes.Controller;
import classes.MyThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    
    private ServerSocket server;
    private Controller controller = Controller.getInstance();
    
    /**
     * Initialize the server and keep the socket available to receive new connections
     * @throws IOException 
     */
    public Server () throws IOException {
        server = new ServerSocket(12345);
        ArrayList<MyThread> threadList = new ArrayList<MyThread>();
        System.out.println("Server has been conected");
        
        while (true) {
            System.out.println("Waiting for a new client");
            Socket socket = server.accept();
            System.out.println("Socket has been accepted");
            
            MyThread thread = new MyThread(socket);
            thread.start();
            threadList.add(thread);
            System.out.println("New client successfully added: " + threadList.size());
        }
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server serv = new Server();
    }
}
