/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

import java.util.ArrayList;

/**
 * Lists current Logged Users, and saves its patient list to be monitored
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class LoggedUser extends User {
    
    private ArrayList<Patient> patientList = new ArrayList<>();
    
    /**
     * @param login
     * @param password
     */
    public LoggedUser(String login, String password){
        super(login, password);
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
    
    public void setPatientList(ArrayList patientList){
        this.patientList = patientList;
    }

}
