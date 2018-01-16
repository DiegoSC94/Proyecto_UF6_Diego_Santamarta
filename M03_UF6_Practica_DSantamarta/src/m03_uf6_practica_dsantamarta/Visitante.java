/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03_uf6_practica_dsantamarta;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diego
 */
public class Visitante {
    private int visitanteId;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Date fechaNacimiento;

    public Visitante(int visitanteId, String nombre, String apellido1, String apellido2, String fechaNacimiento) throws ParseException {
        this.visitanteId = visitanteId;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(fechaNacimiento);
        Date formatDate = new Date(date.getTime());
        this.fechaNacimiento = formatDate;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", apellido1: " + apellido1 + 
                ", apellido2: " + apellido2 + ", fechaNacimiento: " + fechaNacimiento + "\n";
    }
    
    
}
