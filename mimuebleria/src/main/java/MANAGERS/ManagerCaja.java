/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Caja;
import ClasesPredeterminadas.Conexion;
import Enums.CajaEnum;
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
public class ManagerCaja {

    private Connection conexion;
    // QUERYS   
    private String insertarCaja = "INSERT INTO Caja (Estado, Valor) VALUES(?,?)";
    private String borrarCaja = "DELETE FROM Caja WHERE id_Caja = ?";
    private String seleccionarCaja = "SELECT * FROM Caja WHERE id_Caja = ?";

     private String seleccionarEstado = "SELECT * FROM Caja WHERE Estado = ?";
    private String seleccionarTodo = "SELECT * FROM Caja";
    private String updateEstado = "UPDATE Caja SET Estado = ? WHERE id_Caja = ?";
    private String updateValor = "UPDATE Caja SET Valor = ? WHERE id_Caja = ?";

    //Managers
    ManagerCliente managerCliente = new ManagerCliente();

    public ManagerCaja() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarCaja(boolean ganancia, double valor) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarCaja);
            ps.setBoolean(1, ganancia);
            ps.setDouble(2, valor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarCaja(int idCaja) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarCaja);
            ps.setInt(1, idCaja);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCaja(int idCaja, String datoCambiado, CajaEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case Estado:
                    ps = conexion.prepareStatement(updateEstado);
                    ps.setBoolean(1, Boolean.parseBoolean(datoCambiado));
                    ps.setInt(2, idCaja);
                    break;
                case Valor:
                    ps = conexion.prepareStatement(updateValor);
                    ps.setDouble(1, Double.parseDouble(datoCambiado));
                    ps.setInt(2, idCaja);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Caja> seleccionarTodo() {
        ArrayList<Caja> cajas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCaja = rs.getInt("id_Caja");
                boolean estado = rs.getBoolean("Estado");
                double valor = rs.getDouble("Valor");
                cajas.add(new Caja(valor, idCaja, estado));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajas;
    }

    public Caja seleccionarCaja(int idCaja) {
        Caja caja = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarCaja);
            ps.setInt(1, idCaja);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                boolean estado = rs.getBoolean("Estado");
                double valor = rs.getDouble("Valor");
                caja = new Caja(valor, idCaja, estado);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return caja;
    }

    public ArrayList<Caja> seleccionarEstado(boolean ganancia) {
        ArrayList<Caja> cajas = new ArrayList<>();
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarEstado);
            ps.setBoolean(1, ganancia);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idCaja = rs.getInt("id_Caja");
                double valor = rs.getDouble("Valor");
                cajas.add(new Caja(valor, idCaja, ganancia));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cajas;
    }
}
