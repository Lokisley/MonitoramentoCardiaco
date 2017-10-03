/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

public class Patient {
    
    private int id;
    private int bpm;
    private String pressao;
    private boolean movimento;

    public Patient() {
    }

    /**
     * Construtor com todos os parametros inicializados
     * @param id
     * @param bpm
     * @param pressao
     * @param movimento
     */
    public Patient(int id, int bpm, String pressao, boolean movimento) {
        this.id = id;
        this.bpm = bpm;
        this.pressao = pressao;
        this.movimento = movimento;
    }
    
    /**
     * Define todos os parametros do objeto
     * @param id
     * @param bpm
     * @param pressao
     * @param movimento 
     */
    public void setAll (int id, int bpm, String pressao, boolean movimento) {
        this.id = id;
        this.bpm = bpm;
        this.pressao = pressao;
        this.movimento = movimento;
    }
    
    /**
     * @return the id
     */
    public int getId(){
        return id;
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
