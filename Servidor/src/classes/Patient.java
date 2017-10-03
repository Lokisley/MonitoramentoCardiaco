/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

public class Patient {
    
    private int bpm;
    private String pressao;
    private boolean movimento;

    public Patient() {
    }

    /**
     * Construtor com todos os parametros inicializados
     * @param bpm
     * @param movimento 
     */
    public Patient(int bpm, String pressao, boolean movimento) {
        this.bpm = bpm;
        this.pressao = pressao;
        this.movimento = movimento;
    }
    
    /**
     * Define todos os parametros do objeto
     * @param bpm
     * @param movimento 
     */
    public void setAll (int bpm, String pressao, boolean movimento) {
        this.bpm = bpm;
        this.pressao = pressao;
        this.movimento = movimento;
    }

    /**
     * @return the bpm
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * @return the movimento
     */
    public boolean isMovimento() {
        return movimento;
    }
    
    /**
     * @return the pressao
     */
    public String getPressao() {
        return pressao;
    }
}
