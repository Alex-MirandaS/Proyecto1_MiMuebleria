/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Tipo;
import ClasesPredeterminadas.Conexion;
import Enums.TipoPiezaEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ManagerTipoPieza {

    private Connection conexion;
    // QUERYS   
    private String insertarTipoPieza = "INSERT INTO Tipo (Nombre, Cantidad) VALUES(?,?)";
    private String borrarTipoPieza = "DELETE FROM Tipo WHERE Id_Tipo_Pieza = ?";
    private String seleccionarTipoPieza = "SELECT * FROM Tipo WHERE Id_Tipo_Pieza = ?";
    private String seleccionarTipoPiezaNombre = "SELECT * FROM Tipo WHERE Nombre = ?";
    private String seleccionarTodo = "SELECT * FROM Tipo";
    private String seleccionarTodoASC = "SELECT * FROM Tipo ORDER BY Cantidad ASC";
    private String seleccionarTodoDESC = "SELECT * FROM Tipo ORDER BY Cantidad DESC";
    private String seleccionarAPAgotarse = "SELECT * FROM Tipo WHERE Cantidad = 1 OR Cantidad = 2";
    private String seleccionarPAgotadas = "SELECT * FROM Tipo WHERE Cantidad = 0";
    private String updateNombre = "UPDATE Tipo SET Nombre = ? WHERE Id_Tipo_Pieza = ?";
    private String updateCantidad = "UPDATE Tipo SET Cantidad = ? WHERE Id_Tipo_Pieza = ?";

    //C
    public ManagerTipoPieza() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarTipoPieza(String nombre, int cantidad) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarTipoPieza);
            ps.setString(1, nombre);
            ps.setInt(2, cantidad);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarTipoPieza(int id_Tipo_Pieza) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarTipoPieza);
            ps.setInt(1, id_Tipo_Pieza);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTipoPieza(int idTipoPieza, String datoCambiado, TipoPiezaEnum tipoPieza) {

        try {
            PreparedStatement ps = null;
            switch (tipoPieza) {
                case Nombre:
                    ps = conexion.prepareStatement(updateNombre);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, idTipoPieza);
                case Cantidad:
                    ps = conexion.prepareStatement(updateCantidad);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idTipoPieza);
                    break;
            }

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Tipo> seleccionarTodo() {
        ArrayList<Tipo> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                int cantidad = rs.getInt("Cantidad");
                String nombre = rs.getString("Nombre");

                Tipo tipo = new Tipo(id_Tipo_Pieza, nombre);
                tipo.agregarUnidades(cantidad);
                piezas.add(tipo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public ArrayList<Tipo> seleccionarTodoASC() {
        ArrayList<Tipo> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodoASC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                Tipo tipo = new Tipo(id_Tipo_Pieza, nombre);
                tipo.agregarUnidades(cantidad);
                piezas.add(tipo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public ArrayList<Tipo> seleccionarTodoDESC() {
        ArrayList<Tipo> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodoDESC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                Tipo tipo = new Tipo(id_Tipo_Pieza, nombre);
                tipo.agregarUnidades(cantidad);
                piezas.add(tipo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public ArrayList<Tipo> seleccionarAPAgotarse() {
        ArrayList<Tipo> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarAPAgotarse);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                Tipo tipo = new Tipo(id_Tipo_Pieza, nombre);
                tipo.agregarUnidades(cantidad);
                piezas.add(tipo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public ArrayList<Tipo> seleccionarPAgotadas() {
        ArrayList<Tipo> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarPAgotadas);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                String nombre = rs.getString("Nombre");
                Tipo tipo = new Tipo(id_Tipo_Pieza, nombre);
                piezas.add(tipo);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public Tipo seleccionarTipoPieza(int id_Tipo_Pieza) {
        Tipo pieza = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarTipoPieza);
            ps.setInt(1, id_Tipo_Pieza);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                int cantidad = rs.getInt("Cantidad");
                pieza = new Tipo(id_Tipo_Pieza, nombre);
                pieza.agregarUnidades(cantidad);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }

    public Tipo seleccionarTipoPiezaNombre(String nombre) {
        Tipo pieza = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarTipoPiezaNombre);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id_Tipo_Pieza = rs.getInt("Id_Tipo_Pieza");
                int cantidad = rs.getInt("Cantidad");
                pieza = new Tipo(id_Tipo_Pieza, nombre);
                pieza.agregarUnidades(cantidad);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }

}
