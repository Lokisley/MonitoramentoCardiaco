/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoramentocardiaco;

import classes.Comunicacao;
import classes.Paciente;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private ListView listPatient;
    @FXML
    private Pane paneSelectedPatient;
    @FXML
    private Pane paneConexao;
    @FXML
    private Button buttonInicio;
    @FXML
    private TextField textIp;
    @FXML
    private TextField textPorta;
    
    private Timer timer;
    private Comunicacao comun;
    private ArrayList<Paciente> listaPacientes = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneSelectedPatient.setVisible(false);
    }
    
    @FXML
    public void iniciaEnvio(ActionEvent event){
        buttonInicio.setVisible(false);
        timer = new Timer();
        timer.schedule (new Transmissao(), 0, 5000);
    }
    
    @FXML
    public void conectaServidor(ActionEvent event) throws IOException {
        String ip = textIp.getText();
        int porta = Integer.parseInt(textPorta.getText());
        
        comun = new Comunicacao(ip, porta);
        
        paneConexao.setDisable(true);
    }
    
    private class Transmissao extends TimerTask{

        @Override
        public void run() {
            try {
                comun.getThread().sendMessage("ioth monc ALL end");
                String message = (String)comun.getThread().receiveMessage();
                recebePacientes(message);
                listPatient = new ListView<>(FXCollections.observableArrayList(listaPacientes));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void recebePacientes (String message) {
        Paciente paciente;
        
        message = message.substring(message.indexOf("<") + 1);
        while (!message.startsWith(">")){
            message = message.substring(message.indexOf("(") + 1);
            int id = Integer.parseInt(message.substring(message.indexOf(",")));
            message = message.substring(message.indexOf(",") + 1);
            
            String nome = message.substring(message.indexOf(","));
            message = message.substring(message.indexOf(",") + 1);
            
            int bpm = Integer.parseInt(message.substring(message.indexOf(",")));
            message = message.substring(message.indexOf(",") + 1);
            
            String pressao = message.substring(message.indexOf(","));
            message = message.substring(message.indexOf(",") + 1);
            
            boolean movimento = Boolean.parseBoolean(message.substring(message.indexOf(")")));
            message = message.substring(message.indexOf(")") + 1);
            
            paciente = new Paciente(id, nome, bpm, pressao, movimento);
            
            listaPacientes.add(paciente);
        }
    }
}
