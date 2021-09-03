/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Cliente;
import CLASES.Factura;
import ClasesPredeterminadas.Conexion;
import Enums.FacturaEnum;
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
public class ManagerFactura {

    private Connection conexion;
    // QUERYS   
    private String insertarFactura = "INSERT INTO Factura (Cliente_NIT, Compra_Total) VALUES(?,?)";
    private String borrarFactura = "DELETE FROM Factura WHERE Id_Factura = ?";
    private String seleccionarFactura = "SELECT * FROM Factura WHERE Id_Factura = ?";
    private String seleccionarClienteNIT = "SELECT * FROM Factura WHERE Cliente_NIT = ?";
    private String seleccionarTodo = "SELECT * FROM Factura";
    private String updateClienteNIT = "UPDATE Factura SET Cliente_NIT = ? WHERE Id_Factura = ?";
    private String updateCompraTotal = "UPDATE Factura SET Compra_Total = ? WHERE Id_Factura = ?";

    //Managers
    ManagerCliente managerCliente = new ManagerCliente();

    public ManagerFactura() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarFactura(int nit, double compraTotal) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarFactura);
            ps.setInt(1, nit);
            ps.setDouble(2, compraTotal);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarFactura(int idFactura) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarFactura);
            ps.setInt(1, idFactura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCliente(int idFactura, String datoCambiado, FacturaEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case ClienteNIT:
                    ps = conexion.prepareStatement(updateClienteNIT);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, idFactura);
                    break;
                case CompraTotal:
                    ps = conexion.prepareStatement(updateCompraTotal);
                    ps.setDouble(1, Double.parseDouble(datoCambiado));
                    ps.setInt(2, idFactura);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Factura> seleccionarTodo() {
        ArrayList<Factura> facturas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idFactura = rs.getInt("Id_Factura");
                String nitCliente = rs.getString("Cliente_NIT");
                double compraTotal = rs.getDouble("Compra_Total");
                facturas.add(new Factura(idFactura, compraTotal, managerCliente.seleccionarCliente(nitCliente)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facturas;
    }

    public Factura seleccionarFactura(int idFactura) {
        Factura factura = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarFactura);
            ps.setInt(1, idFactura);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nitCliente = rs.getString("Cliente_NIT");
                double compraTotal = rs.getDouble("Compra_Total");
                factura = new Factura(idFactura, compraTotal, managerCliente.seleccionarCliente(nitCliente));
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return factura;
    }

    public ArrayList<Factura> seleccionarClienteNIT(String clienteNIT) {
        ArrayList<Factura> facturas = new ArrayList<>();
        Cliente cliente = managerCliente.seleccionarCliente(clienteNIT);
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarClienteNIT);
            ps.setString(1, clienteNIT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idFactura = rs.getInt("Id_Factura");
                double compraTotal = rs.getDouble("Compra_Total");
                facturas.add(new Factura(idFactura, compraTotal, cliente));
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return facturas;
    }
}
