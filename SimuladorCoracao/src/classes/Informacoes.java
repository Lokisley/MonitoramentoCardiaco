/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Informacoes {
    
    private String nome;
    private int bpm;
    private int pressaoMin;
    private int pressaoMax;
    private boolean movimento;

    public Informacoes() {
    }

    public Informacoes(String nome, int bpm, int pressaoMin, int pressaoMax, boolean movimento) {
        this.nome = nome;
        this.bpm = bpm;
        this.pressaoMin = pressaoMin;
        this.pressaoMax = pressaoMax;
        this.movimento = movimento;
    }
    
    public void setAll (String nome, int bpm, int pressaoMin, int pressaoMax, boolean movimento) {
        this.nome = nome;
        this.bpm = bpm;
        this.pressaoMin = pressaoMin;
        this.pressaoMax = pressaoMax;
        this.movimento = movimento;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the bpm
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * @param bpm the bpm to set
     */
    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    /**
     * @return the pressaoMin
     */
    public int getPressaoMin() {
        return pressaoMin;
    }

    /**
     * @param pressaoMin the pressaoMin to set
     */
    public void setPressaoMin(int pressaoMin) {
        this.pressaoMin = pressaoMin;
    }

    /**
     * @return the pressaoMax
     */
    public int getPressaoMax() {
        return pressaoMax;
    }

    /**
     * @param pressaoMax the pressaoMax to set
     */
    public void setPressaoMax(int pressaoMax) {
        this.pressaoMax = pressaoMax;
    }

    /**
     * @return the movimento
     */
    public boolean isMovimento() {
        return movimento;
    }

    /**
     * @param movimento the movimento to set
     */
    public void setMovimento(boolean movimento) {
        this.movimento = movimento;
    }
}
