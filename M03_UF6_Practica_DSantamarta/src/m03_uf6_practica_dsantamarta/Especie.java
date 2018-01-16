/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03_uf6_practica_dsantamarta;

/**
 *
 * @author Diego
 */
public class Especie {
    private int especieId;
    private String nombre;
    private String habitat;
    private int codigoZoo;

    public Especie(int especieId, String nombre, String habitat, int codigoZoo) {
        this.especieId = especieId;
        this.nombre = nombre;
        this.habitat = habitat;
        this.codigoZoo = codigoZoo;
    }

    @Override
    public String toString() {
        return "especieId: " + especieId + ", nombre: " + nombre + ", habitat: " + habitat + ", codigoZoo: " + codigoZoo + "\n";
    }

    
    
    
}
