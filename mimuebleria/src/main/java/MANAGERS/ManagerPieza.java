/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Pieza;
import CLASES.Tipo;
import ClasesPredeterminadas.Conexion;
import Enums.PiezaEnum;
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
public class ManagerPieza {

    //Conexion
    private Connection conexion;
    // QUERYS   
    private String insertarPieza = "INSERT INTO Pieza (Costo_Unitario, Tipo_Pieza) VALUES(?,?)";
    private String borrarPieza = "DELETE FROM Pieza WHERE Id_Pieza = ?";
    private String seleccionarPieza = "SELECT * FROM Pieza WHERE Id_Pieza = ?";
    private String seleccionarPiezaTipo = "SELECT * FROM Pieza WHERE Tipo_Pieza = ?";
    private String seleccionarTodo = "SELECT * FROM Pieza";
    private String updateCosto = "UPDATE Pieza SET Costo_Unitario = ? WHERE Id_Pieza = ?";
    private String updateTipoPieza = "UPDATE Pieza SET Tipo_Pieza = ? WHERE Id_Pieza = ?";
    //Managers
    private ManagerTipoPieza managerTipoPieza = new ManagerTipoPieza();

    public ManagerPieza() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarPieza(double costoUnitario, Tipo pieza) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarPieza);
            ps.setDouble(1, costoUnitario);
            ps.setInt(2, pieza.getIdTipoPieza());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarPieza(int id_Pieza) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarPieza);
            ps.setInt(1, id_Pieza);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePieza(int id_Pieza, String datoCambiado, PiezaEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case Costo_Unitario:
                    ps = conexion.prepareStatement(updateCosto);
                    ps.setDouble(1, Double.parseDouble(datoCambiado));
                    ps.setInt(2, id_Pieza);
                    break;
                case Tipo_Pieza:
                    ps = conexion.prepareStatement(updateTipoPieza);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, id_Pieza);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Pieza> seleccionarTodo() {
        ArrayList<Pieza> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPieza = rs.getInt("Id_Pieza");
                double costo = rs.getDouble("Costo_Unitario");
                int tipoPieza = rs.getInt("Tipo_Pieza");
                Pieza pieza = new Pieza(costo, idPieza, managerTipoPieza.seleccionarTipoPieza(tipoPieza));
                piezas.add(pieza);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

    public Pieza seleccionarPieza(int Id_Pieza) {
        Pieza pieza = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarPieza);
            ps.setInt(1, Id_Pieza);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPieza = rs.getInt("Id_Pieza");
                double costo = rs.getDouble("Costo_Unitario");
                int tipoPieza = rs.getInt("Tipo_Pieza");
                pieza = new Pieza(costo, idPieza, managerTipoPieza.seleccionarTipoPieza(tipoPieza));
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pieza;
    }

    public ArrayList<Pieza> seleccionarPorTipo(Tipo tipo) {
        ArrayList<Pieza> piezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarPiezaTipo);
            ps.setInt(1, tipo.getIdTipoPieza());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idPieza = rs.getInt("Id_Pieza");
                double costo = rs.getDouble("Costo_Unitario");
                Pieza pieza = new Pieza(costo, idPieza, managerTipoPieza.seleccionarTipoPieza(tipo.getIdTipoPieza()));
                piezas.add(pieza);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return piezas;
    }

}
