/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 * @author Lokisley Oliveira <lokisley at hotmail.com>
 */
public class Paciente {
    
    private String nome;
    private int bpm;
    private int id;
    private String pressao;
    private boolean movimento;

    public Paciente() {
    }

    /**
     * Construtor com todos os parametros inicializados
     * @param id
     * @param nome
     * @param bpm
     * @param pressaoMin
     * @param pressaoMax
     * @param movimento 
     */
    public Paciente(int id, String nome, int bpm, int pressaoMin, int pressaoMax, boolean movimento) {
        this.id = id;
        this.nome = nome;
        this.bpm = bpm;
        this.pressao = pressaoMax + "/" + pressaoMin;
        this.movimento = movimento;
    }
    
    /**
     * Construtor com todos os parametros inicializados
     * @param id
     * @param nome
     * @param bpm
     * @param pressao
     * @param movimento 
     */
    public Paciente(int id, String nome, int bpm, String pressao, boolean movimento) {
        this.id = id;
        this.nome = nome;
        this.bpm = bpm;
        this.pressao = pressao;
        this.movimento = movimento;
    }
    
    /**
     * Define todos os parametros do objeto
     * @param id
     * @param nome
     * @param bpm
     * @param pressaoMin
     * @param pressaoMax
     * @param movimento 
     */
    public void setAll (int id, String nome, int bpm, int pressaoMin, int pressaoMax, boolean movimento) {
        this.id = id;
        this.nome = nome;
        this.bpm = bpm;
        this.setPressao(pressaoMax + "/" + pressaoMin);
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
    
    /**
     * @return the pressao
     */
    public String getPressao() {
        return pressao;
    }

    /**
     * @param pressao the pressao to set
     */
    public void setPressao(String pressao) {
        this.pressao = pressao;
    }
}
