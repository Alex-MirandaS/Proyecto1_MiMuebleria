/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Cliente;
import CLASES.SalaVenta;
import ClasesPredeterminadas.Conexion;
import Enums.ClienteEnum;
import Enums.SalaVentaEnum;
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
public class ManagerSalaVentas {

    private Connection conexion;
    // QUERYS   
    private String insertarSalaVenta = "INSERT INTO Sala_Ventas (Tipo_Mueble, Precio_Mueble, Existencias) VALUES(?,?,?)";
    private String borrarSalaVenta = "DELETE FROM Sala_Ventas WHERE Id_Sala_Ventas = ?";
    private String seleccionarSalaVenta = "SELECT * FROM Sala_Ventas WHERE Id_Sala_Ventas = ?";
    private String seleccionarPorNombreProducto = "SELECT * FROM Sala_Ventas WHERE Tipo_Mueble = ?";
    private String seleccionarTodo = "SELECT * FROM Sala_Ventas";
    private String updateTipoMueble = "UPDATE Sala_Ventas SET Tipo_Mueble = ? WHERE Id_Sala_Ventas = ?";
    private String updatePrecioMueble = "UPDATE Sala_Ventas SET Precio_Mueble = ? WHERE Id_Sala_Ventas = ?";
    private String updateExistencias = "UPDATE Sala_Ventas SET Existencias = ? WHERE Id_Sala_Ventas = ?";

    public ManagerSalaVentas() {
        this.conexion = Conexion.getConnection();
    }

    public void insetarSalaVenta(String tipoMueble, Double precio, int existencia) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarSalaVenta);
            ps.setString(1, tipoMueble);
            ps.setDouble(2, precio);
            ps.setInt(3, existencia);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarSalaVenta(int idSalaVentas) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarSalaVenta);
            ps.setInt(1, idSalaVentas);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSalaVentas(int idSalaVentas, String datoCambiado, SalaVentaEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case Existencias:
                    ps = conexion.prepareStatement(updateExistencias);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idSalaVentas);
                    break;
                case PrecioMueble:
                    ps = conexion.prepareStatement(updatePrecioMueble);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idSalaVentas);
                    break;
                case TipoMueble:
                    ps = conexion.prepareStatement(updateTipoMueble);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, idSalaVentas);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SalaVenta> seleccionarTodo() {
        ArrayList<SalaVenta> salaVentas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int IdSalaVentas = rs.getInt("Id_Sala_Ventas");
                String tipoMueble = rs.getString("Tipo_Mueble");
                double precioMueble = rs.getDouble("Precio_Mueble");
                int existencias = rs.getInt("Existencias");
                SalaVenta sala = new SalaVenta(IdSalaVentas, tipoMueble, precioMueble);
                sala.sumarExistencias(existencias);
                salaVentas.add(sala);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salaVentas;
    }

    public SalaVenta seleccionarSalaVentas(int Id_Sala_Ventas) {
        SalaVenta salaVenta = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarSalaVenta);
            ps.setInt(1, Id_Sala_Ventas);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int IdSalaVentas = rs.getInt("Id_Sala_Ventas");
                String tipoMueble = rs.getString("Tipo_Mueble");
                double precioMueble = rs.getDouble("Precio_Mueble");
                int existencias = rs.getInt("Existencias");
                SalaVenta sala = new SalaVenta(IdSalaVentas, tipoMueble, precioMueble);
                sala.sumarExistencias(existencias);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salaVenta;
    }

    public SalaVenta seleccionarPorNombreProducto(String producto) {
        SalaVenta salaVenta = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarPorNombreProducto);
            ps.setString(1, producto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int IdSalaVentas = rs.getInt("Id_Sala_Ventas");
                double precioMueble = rs.getDouble("Precio_Mueble");
                int existencias = rs.getInt("Existencias");
                salaVenta = new SalaVenta(IdSalaVentas, producto, precioMueble);
                salaVenta.sumarExistencias(existencias);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salaVenta;
    }
}
