/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

public class Paciente {
    
    private int id;
    private int bpm;
    private int pressaoMin;
    private int pressaoMax;
    private String nome;
    private boolean movimento;

    public Paciente() {
    }

    /**
     * Construtor com todos os parametros inicializados
     * @param id
     * @param bpm
     * @param nome
     * @param pressaoMin
     * @param pressaoMax
     * @param movimento 
     */
    public Paciente(int id, int bpm, int pressaoMin, int pressaoMax, String nome, boolean movimento) {
        this.id = id;
        this.bpm = bpm;
        this.nome = nome;
        this.pressaoMin = pressaoMin;
        this.pressaoMax = pressaoMax;
        this.movimento = movimento;
    }
    
    /**
     * Define todos os parametros do objeto
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
        this.pressaoMin = pressaoMin;
        this.pressaoMax = pressaoMax;
        this.movimento = movimento;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the bpm
     */
    public int getBpm() {
        return bpm;
    }

    /**
     * @return the pressaoMin
     */
    public int getPressaoMin() {
        return pressaoMin;
    }

    /**
     * @return the pressaoMax
     */
    public int getPressaoMax() {
        return pressaoMax;
    }

    /**
     * @return the movimento
     */
    public boolean isMovimento() {
        return movimento;
    }
}
