/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

public class Paciente {
    
    private int id;
    private int bpm;
    private String pressao;
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
        this.pressao = (pressaoMin + "/" + pressaoMax);
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
        this.pressao = (pressaoMin + "/" + pressaoMax);
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
     * @return the pressao
     */
    public String getPressao(){
        return pressao;
    }

    /**
     * @return the movimento
     */
    public boolean isMovimento() {
        return movimento;
    }
}
