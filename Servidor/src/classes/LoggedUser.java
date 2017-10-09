/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.ArrayList;

/**
 * 
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class LoggedUser extends User {
    
    private String ip;
    private int gate;
    private ArrayList<Patient> patientList = new ArrayList<>();
    
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
    public String getIp(){
        return ip;
    }
    
    /**
     * @return the patient list 
     */
    public ArrayList<Patient> getPatientList() {
        return patientList;
    }
    
    /**
     * Add a patient on the list
     * @param patient 
     */
    public void addPatient (Patient patient) {
        patientList.add(patient);
    }

}
