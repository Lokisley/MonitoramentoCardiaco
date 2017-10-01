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
import javafx.scene.control.SpinnerValueFactory;

public class FXMLDocumentController implements Initializable {
    
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
    private Comunicacao comun = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnerBPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 80, 1));
        // TODO
    }
    
    @FXML
    public void enviaDados(ActionEvent event) {
        Paciente info = salvaDados();
        
    }
    
    private Paciente salvaDados() {
        int bpm = spinnerBPM.getValue();
        int pressaoMin = Integer.parseInt(textPressaoMin.getText());
        int pressaoMax = Integer.parseInt(textPressaoMax.getText());
        boolean movimento = checkMovimento.isSelected();
        Paciente info = new Paciente(bpm, pressaoMin, pressaoMax, movimento);
        return info;
    }

    @FXML
    public void conectaServidor(ActionEvent event) throws IOException {
        String ip = textIp.getText();
        int porta = Integer.parseInt(textPorta.getText());
        
        comun = new Comunicacao(ip, porta);
        
        textIp.setDisable(true);
        textPorta.setDisable(true);
    }
    
}
