/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Mueble;
import ClasesPredeterminadas.Conexion;
import Enums.MuebleEnum;
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
public class ManagerMueble {

    private Connection conexion;
    // QUERYS   
    private String insertarMueble = "INSERT INTO Mueble (Precio_Venta, Nombre_Mueble) VALUES(?,?)";
    private String borrarMueble = "DELETE FROM Mueble WHERE Id_Mueble = ?";
    private String seleccionarMueble = "SELECT * FROM Mueble WHERE Id_Mueble = ?";
    private String seleccionarMuebleNombre = "SELECT * FROM Mueble WHERE Nombre_Mueble = ?";
    private String seleccionarTodo = "SELECT * FROM Mueble";
    private String updatePrecioVenta = "UPDATE Mueble SET Precio_Venta = ? WHERE Id_Mueble = ?";
    private String updateNombreMueble = "UPDATE Mueble SET Nombre_Mueble = ? WHERE Id_Mueble = ?";

    public ManagerMueble() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarMueble(double precioVenta, String nombreMueble) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarMueble);
            ps.setDouble(1, precioVenta);
            ps.setString(2, nombreMueble);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarMueble(int id_Mueble) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarMueble);
            ps.setInt(1, id_Mueble);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateMueble(int id_Mueble, String datoCambiado, MuebleEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case PrecioVenta:
                    ps = conexion.prepareStatement(updatePrecioVenta);
                    ps.setDouble(1, Double.parseDouble(datoCambiado));
                    ps.setInt(2, id_Mueble);
                    break;
                case NombreMueble:
                    ps = conexion.prepareStatement(updateNombreMueble);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, id_Mueble);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Mueble> seleccionarTodo() {
        ArrayList<Mueble> muebles = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMueble = rs.getInt("Id_Mueble");
                double precioVenta = rs.getDouble("Precio_Venta");
                String nombreMueble = rs.getString("Nombre_Mueble");
                muebles.add(new Mueble(idMueble, precioVenta, nombreMueble));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return muebles;
    }

    public Mueble seleccionarMueble(int id_Mueble) {
        Mueble mueble = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarMueble);
            ps.setInt(1, id_Mueble);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMueble = rs.getInt("Id_Mueble");
                double precioVenta = rs.getDouble("Precio_Venta");
                String nombreMueble = rs.getString("Nombre_Mueble");
                mueble = new Mueble(idMueble, precioVenta, nombreMueble);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mueble;
    }
    
    public Mueble seleccionarMuebleNombre(String nombre) {
        Mueble mueble = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarMuebleNombre);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idMueble = rs.getInt("Id_Mueble");
                double precioVenta = rs.getDouble("Precio_Venta");
                String nombreMueble = rs.getString("Nombre_Mueble");
                mueble = new Mueble(idMueble, precioVenta, nombreMueble);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mueble;
    }

}
