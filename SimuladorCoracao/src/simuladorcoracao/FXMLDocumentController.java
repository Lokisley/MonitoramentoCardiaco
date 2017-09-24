/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorcoracao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import classes.Informacoes;
import javafx.scene.control.SpinnerValueFactory;
/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Spinner<Integer> spinnerBPM = new Spinner<Integer>();
    @FXML
    private TextField textNome;
    @FXML
    private TextField textPressaoMin;
    @FXML
    private TextField textPressaoMax;
    @FXML
    private CheckBox checkMovimento;
    @FXML
    private Informacoes info = new Informacoes();
    private String nome;
    private int bpm, pressaoMin, pressaoMax;
    private boolean movimento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinnerBPM.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500, 80, 1));
        // TODO
    }
    
    @FXML
    public void enviaDados(ActionEvent event) {
        salvaDados();
    }
    
    private void salvaDados() {
        nome = textNome.getText();
        bpm = spinnerBPM.getValue();
        pressaoMin = Integer.parseInt(textPressaoMin.getText());
        pressaoMax = Integer.parseInt(textPressaoMax.getText());
        movimento = checkMovimento.isSelected();
        info.setAll(nome, bpm, pressaoMin, pressaoMax, movimento);
    }
    
}