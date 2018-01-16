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
public class Ticket {
    private int ticketId;
    private Date fechaCompra;
    private float precio;
    private int codigoZoo;

    public Ticket(int ticketId, String fechaCompra, float precio, int codigoZoo) throws ParseException {
        this.ticketId = ticketId;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date date = sdf.parse(fechaCompra);
        Date formatDate = new Date(date.getTime());
        this.fechaCompra = formatDate;
        this.precio = precio;
        this.codigoZoo = codigoZoo;
    }

    @Override
    public String toString() {
        return "TicketId: " + ticketId + ", fechaCompra: " + fechaCompra + ", precio: " + precio + ", codigoZoo: " + codigoZoo + "\n";
    }
    
    
}
