/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03_uf6_practica_dsantamarta;

import Conexion.MSQLConexion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Diego
 */
public class M03_UF6_Practica_DSantamarta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        // TODO code application logic here

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //SQL
        String mostrarEspecies = "SELECT * FROM especies";//revisar
        String mostrarTicket = "SELECT ticket.*, visitantes.* FROM ticket, visitantes Where ticket.ticketID = visitantes.visitanteID";//falta mostar visitantes
        String insertarEspecie = "INSERT INTO especies VALUES (?,?,?,?); ";
        String insertarVisitante = "INSERT INTO visitantes VALUES (?,?,?,?,?)";
        String insertarTicket = "INSERT INTO ticket VALUES (?,?,?,?)";
        String actualizarEspecie = "UPDATE especies SET habitat= ? WHERE especieID = ?";
        String borrarEspecie = "DELETE FROM especies WHERE nombre = ?";

        //Clases
        int id, id2, ultimaId = 0;
        String nombre;
        String apellido1;
        String apellido2;
        String habitat;
        String especie;
        int codigo;
        String fechaCompra;
        String fechaNacimiento;
        float precio;

        //Variables
        int opcion;
        java.util.Date fechaActual;
        java.util.Date fechaNacimientoDate;
        boolean duplicidad;
        int dias = 1000000;

        do {

            opcion = 0;
            duplicidad = false;

            System.out.println("Menu:");
            System.out.println("1.Mostrar lista de especies");
            System.out.println("2.Mostrar tickets");
            System.out.println("3.Insertar una especie");
            System.out.println("4.Insertar un ticket");
            System.out.println("5.Editar datos de la especie");
            System.out.println("6.Borrar especie");
            System.out.println("7.Salir");
            System.out.println("Escoge una opcion:");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {

                case 1:
                    try (Connection con = MSQLConexion.getInstance();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(mostrarEspecies);) {

                        while (rs.next()) {
                            id = rs.getInt("especieID");
                            nombre = rs.getString("nombre");
                            habitat = rs.getString("habitat");
                            codigo = rs.getInt("codigoZoo");

                            Especie auxEspecie = new Especie(id, nombre, habitat, codigo);

                            System.out.println(auxEspecie.toString());

                        }
                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                case 2:
                    try (Connection con = MSQLConexion.getInstance();
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(mostrarTicket);) {

                        while (rs.next()) {
                            id = rs.getInt("visitanteID");
                            nombre = rs.getString("nombre");
                            apellido1 = rs.getString("apellido1");
                            apellido2 = rs.getString("apellido2");
                            fechaNacimiento = rs.getString("fechaNacimiento");
                            id2 = rs.getInt("ticketID");
                            fechaCompra = rs.getString("fechaCompra");
                            precio = rs.getFloat("precio");
                            codigo = rs.getInt("codigoZoo");

                            Visitante auxVisitante = new Visitante(id, nombre, apellido1, apellido2, fechaNacimiento);
                            Ticket auxTicket = new Ticket(id2, fechaCompra, precio, codigo);

                            System.out.println("Ticket: " + auxTicket.toString() + " del visitante: " + auxVisitante.toString());
                        }
                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } catch (ParseException pe) {
                        System.out.println("Error en la fecha: " + pe);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                case 3:
                    try (Connection con = MSQLConexion.getInstance();
                            PreparedStatement ps = con.prepareStatement(insertarEspecie);
                            Statement stmt = con.createStatement();
                            ResultSet rs2 = stmt.executeQuery(mostrarEspecies);) {

                        System.out.println("Introduce el nombre de la especie (obligatorio)");
                        especie = br.readLine();

                        if (especie.isEmpty()) {
                            System.out.println("El nombre de la especie no se ha introducido");
                            break;
                        }

                        while (rs2.next()) {
                            id = rs2.getInt("especieID");
                            nombre = rs2.getString("nombre");
                            habitat = rs2.getString("habitat");
                            codigo = rs2.getInt("codigoZoo");

                            Especie auxEspecie = new Especie(id, nombre, habitat, codigo);

                            if (nombre.equalsIgnoreCase(especie)) {
                                System.out.println("El animal que quieres introducir ya esta en la base de datos: "
                                        + auxEspecie.toString());
                                duplicidad = true;
                            }
                            ultimaId = id;
                        }

                        if (duplicidad == true) {
                            break;
                        }

                        System.out.println("Introduce el habitat");
                        habitat = br.readLine();

                        ultimaId++;

                        ps.setInt(1, ultimaId);
                        ps.setString(2, especie);
                        ps.setString(3, habitat);
                        ps.setInt(4, 1);

                        ps.executeUpdate();
                        System.out.println("La especie a sido añadida correctamente");

                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                case 4:
                    try (Connection con = MSQLConexion.getInstance();
                            PreparedStatement ps = con.prepareStatement(insertarVisitante);
                            PreparedStatement ps2 = con.prepareStatement(insertarTicket);
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery(mostrarTicket);) {

                        System.out.println("Introduce el nombre del visitante (Obligatorio)");
                        nombre = br.readLine();

                        System.out.println("Introduce el primer apellido del visitante (obligatorio)");
                        apellido1 = br.readLine();

                        if (nombre.isEmpty() || apellido1.isEmpty()) {
                            System.out.println("El nombre o el apellido1 no han sido introducidos");
                            break;
                        }
                        System.out.println("Introduce el segundo apellido del visitante");
                        apellido2 = br.readLine();

                        System.out.println("Introduce la fecha de nacimiento: yyyy-mm-dd (obligatorio)");
                        fechaNacimiento = br.readLine();

                        if (fechaNacimiento.isEmpty()) {
                            System.out.println("No has introducido la fecha de nacimiento");
                            break;
                        }

                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendario = Calendar.getInstance();

                        fechaNacimientoDate = df.parse(fechaNacimiento);
                        fechaActual = calendario.getTime();

                        dias = (int) ((fechaActual.getTime() - fechaNacimientoDate.getTime()) / 86400000);

                        if (dias / 365 < 12) {
                            precio = 5;
                        } else {
                            precio = 10;
                        }

                        while (rs.next()) {
                            id = rs.getInt("ticketID");

                            ultimaId = id;
                        }
                        ultimaId++;

                        ps.setInt(1, ultimaId);
                        ps.setString(2, nombre);
                        ps.setString(3, apellido1);
                        ps.setString(4, apellido2);
                        ps.setString(5, fechaNacimiento);

                        ps2.setInt(1, ultimaId);
                        ps2.setString(2, df.format(calendario.getTime()));
                        ps2.setFloat(3, precio);
                        ps2.setInt(4, 1);

                        ps.executeUpdate();
                        ps2.executeUpdate();

                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                case 5:

                    try (Connection con = MSQLConexion.getInstance();
                            PreparedStatement ps = con.prepareStatement(actualizarEspecie);) {

                        System.out.println("Introduce la id de la especie que quieres actualizar");
                        id = Integer.parseInt(br.readLine());

                        System.out.println("Introduce el habitat del animal que quieres actualizar");
                        habitat = br.readLine();

                        ps.setString(1, habitat);
                        ps.setInt(2, id);

                        ps.executeUpdate();

                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                case 6:
                    try (Connection con = MSQLConexion.getInstance();
                            PreparedStatement ps = con.prepareStatement(borrarEspecie);) {

                        System.out.println("Introduce el nombre de la especie que quieres borrar");
                        nombre = br.readLine();

                        ps.setString(1, nombre);

                        ps.executeUpdate();

                    } catch (SQLException sqle) {
                        System.out.println("Error: " + sqle);
                    } finally {
                        MSQLConexion.cerrarConexion();
                    }
                    break;

                default:
                    System.out.println("La opcion no es valida");
                    break;

            }
        } while (opcion != 7);
    }

}
