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
public class LoggedUser extends User {
    
    private String ip;
    private int gate;
    
    /**
     * @param login
     * @param password
     * @param ip
     * @param gate 
     */
    public LoggedUser(String login, String password, String ip, int gate){
        super(login, password);
        this.ip = ip;
        this.gate = gate;
    }

    /**
     * @return the gate
     */
    public int getGate() {
        return gate;
    }
    
    /**
     * @return the ip
     */
    public String ip(){
        return ip;
    }

}
