/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class Comunicacao {

    private Socket socket;

    public Comunicacao (String ip, int porta) throws IOException {
        this.socket = new Socket(ip, porta);
        System.out.println("Server has been started");
        
        MyThread thread = new MyThread(socket);
        thread.start();
        
    }
}
