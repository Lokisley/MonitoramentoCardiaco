/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 * Save the information from the registered user
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class User {
    
    private String login;
    private String password;
    
    private User (){
        
    }
    
    User (String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
