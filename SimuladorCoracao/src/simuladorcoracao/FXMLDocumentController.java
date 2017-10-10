/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcoracao;

import classes.Comunicacao;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import classes.Paciente;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane panelConexao;
    @FXML
    private AnchorPane panelInformacao;
    @FXML
    private Spinner<Integer> spinnerBPM = new Spinner<Integer>();
    @FXML
    private TextField textPressaoMin;
    @FXML
    private TextField textPressaoMax;
    @FXML
    private CheckBox checkMovimento;
    @FXML
    private TextField textIp;
    @FXML
    private TextField textPorta;
    @FXML
    private TextField textNome;
    @FXML
    private TextField textId;
    @FXML
    private Button buttonEnvio;
    
    private Comunicacao comun = null;
    private Timer timer;
    
    private static FXMLDocumentController controller;
    
    public FXMLDocumentController () {
        
    }
    
    public static FXMLDocumentController getInstance (){
        if (controller == null) {
            controller = new FXMLDocumentController();
        }
        return controller;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnerBPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 80, 2));
        panelInformacao.setDisable(true);
    }
    
    public Paciente salvaDados() {
        String nome = textNome.getText();
        int id = Integer.parseInt(textId.getText());
        int bpm = spinnerBPM.getValue();
        int pressaoMin = Integer.parseInt(textPressaoMin.getText());
        int pressaoMax = Integer.parseInt(textPressaoMax.getText());
        boolean movimento = checkMovimento.isSelected();
        Paciente info = new Paciente(id, bpm, pressaoMax, pressaoMin, nome, movimento);
        return info;
    }

    @FXML
    public void conectaServidor(ActionEvent event) throws IOException {
        String ip = textIp.getText();
        int porta = Integer.parseInt(textPorta.getText());
        
        comun = new Comunicacao(ip, porta);
        
        panelConexao.setDisable(true);
        panelInformacao.setDisable(false);
    }
    
    @FXML
    public void iniciaEnvio(ActionEvent event){
        buttonEnvio.setVisible(false);
        timer = new Timer();
        timer.schedule (new Transmissao(), 0, 5000);
    }
    
    private class Transmissao extends TimerTask{
        Paciente info;

        @Override
        public void run() {
            info = salvaDados();
            String message = "ioth simc senddata " +
                    info.getId() + "," +
                    info.getNome() + "," +
                    info.getBpm() + "," +
                    info.getPressao() + "," +
                    info.isMovimento() + " end";
            try {
                comun.getThread().sendMessage(message);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
