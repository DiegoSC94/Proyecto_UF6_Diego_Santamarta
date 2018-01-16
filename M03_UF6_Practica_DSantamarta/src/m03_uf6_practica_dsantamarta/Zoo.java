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
public class Zoo {
    private int zooId;
    private String nombre;

    public Zoo(int zooId, String nombre) {
        this.zooId = zooId;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Id: " + zooId + ", nombre: " + nombre + "\n";
    }
    
    
}
