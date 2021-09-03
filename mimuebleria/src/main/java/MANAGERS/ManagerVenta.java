/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Caja;
import CLASES.Cliente;
import CLASES.EnsamblajeMueble;
import CLASES.Factura;
import CLASES.SalaVenta;
import CLASES.Venta;
import ClasesPredeterminadas.Conexion;
import Enums.VentaEnum;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ManagerVenta {

    private Connection conexion;
    // QUERYS   
    private String insertarVenta = "INSERT INTO Venta (Fecha_Venta, Precio_Mueble_Vendido, Mueble_Ensamblado, Sala_Venta, Cliente, Factura, Caja) VALUES(?,?,?,?,?,?,?,?)";
    private String borrarVenta = "DELETE FROM Venta WHERE Id_Venta = ?";
    private String borrarFactura = "DELETE FROM Venta WHERE Factura = ?";
    private String seleccionarVenta = "SELECT * FROM Venta WHERE Id_Venta = ?";
    private String seleccionarTodo = "SELECT * FROM Venta";
    private String seleccionarFechaVenta = "SELECT * FROM Venta WHERE Fecha_Venta = ?";
    private String seleccionarMueble = "SELECT * FROM Venta WHERE Mueble_Ensamblado = ?";
    private String seleccionarSalaVenta = "SELECT * FROM Venta WHERE Sala_Venta = ?";
    private String seleccionarCliente = "SELECT * FROM Venta WHERE Cliente = ?";
    private String seleccionarFactura = "SELECT * FROM Venta WHERE Factura = ?";
    private String seleccionarCaja = "SELECT * FROM Venta WHERE Caja = ?";

    private String updateFacturaCliente = "UPDATE Venta SET Factura = ?, Cliente = ? WHERE Cliente = ?";
    private String updatePrecioMuebleFactura = "UPDATE Venta SET Precio_Mueble_Vendido = ? WHERE (Factura = ?) AND (Mueble_Ensamblado = ?)";

    //Managers
    ManagerCliente managerCliente = new ManagerCliente();
    ManagerCaja managerCaja = new ManagerCaja();
    ManagerFactura managerFactura = new ManagerFactura();
    ManagerSalaVentas managerSalaVentas = new ManagerSalaVentas();
    ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();

    public ManagerVenta() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarVenta(LocalDate fechaVenta, double precioMuebleVendido, EnsamblajeMueble muebleEnsamblado, SalaVenta salaVenta, Cliente cliente, Factura factura, Caja caja) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarVenta);
            ps.setDate(1, Date.valueOf(fechaVenta));
            ps.setDouble(2, precioMuebleVendido);
            ps.setInt(3, muebleEnsamblado.getIdEnsamblajeMueble());
            ps.setInt(4, salaVenta.getIdSalaVenta());
            ps.setString(5, cliente.getNIT());
            ps.setInt(6, factura.getIdFactura());
            ps.setInt(7, caja.getIdCaja());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarVenta(int idVenta) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarVenta);
            ps.setInt(1, idVenta);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarFactura(int factura) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarFactura);
            ps.setInt(1, factura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateVentas(String datoCambiado1, String datoCambiado2, String datoCambiado3, VentaEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case FacturaCliente:
                    ps = conexion.prepareStatement(updateFacturaCliente);
                    ps.setInt(1, Integer.parseInt(datoCambiado1));
                    ps.setString(2, datoCambiado2);
                    ps.setString(3, datoCambiado3);
                    break;
                case PrecioMuebleFactura:
                    ps = conexion.prepareStatement(updatePrecioMuebleFactura);
                    ps.setDouble(1, Double.parseDouble(datoCambiado1));
                    ps.setInt(2, Integer.parseInt(datoCambiado2));
                    ps.setInt(3, Integer.parseInt(datoCambiado3));
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Venta> seleccionarTodo() {
        ArrayList<Venta> ventas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                int salaVenta = rs.getInt("Sala_Venta");
                String nitCliente = rs.getString("Cliente");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                ventas.add(new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventas;
    }

    public Venta seleccionarVenta(int idVenta) {
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarVenta);
            ps.setInt(1, idVenta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                int salaVenta = rs.getInt("Sala_Venta");
                String nitCliente = rs.getString("Cliente");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                return new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //hay error en el localdate
        }
        return null;
    }

    public ArrayList<Venta> seleccionarFechaVenta(LocalDate fecha) {
        ArrayList<Venta> ventasFecha = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarFechaVenta);
            ps.setDate(1, Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                int salaVenta = rs.getInt("Sala_Venta");
                String nitCliente = rs.getString("Cliente");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                ventasFecha.add(new Venta(idVenta, fecha, precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //hay error en el localdate
        }
        return ventasFecha;
    }
    
        public Venta seleccionarMuebleEnsambladoVendido(int idMuebleEnsamblado) {
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarMueble);
            ps.setInt(1, idMuebleEnsamblado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                  int salaVenta = rs.getInt("Sala_Venta");
                String nitCliente = rs.getString("Cliente");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                return new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(idMuebleEnsamblado));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //hay error en el localdate
        }
        return null;
    }
        
            public ArrayList<Venta> seleccionarMuebleSalaVentaVendido(int salaVenta) {
        ArrayList<Venta> mueblesSalaVentasVendidos = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarSalaVenta);
            ps.setInt(1, salaVenta);
            ResultSet rs = ps.executeQuery();

          while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                String nitCliente = rs.getString("Cliente");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                mueblesSalaVentasVendidos.add(new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //hay error en el localdate
        }
        return mueblesSalaVentasVendidos;
    } 

    public ArrayList<Venta> seleccionarVentasCliente(String nitCliente) {
        ArrayList<Venta> ventasCliente = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ps.setString(1, nitCliente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                int salaVenta = rs.getInt("Sala_Venta");
                int factura = rs.getInt("Factura");
                int caja = rs.getInt("Caja");
                ventasCliente.add(new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventasCliente;
    }
    
        public ArrayList<Venta> seleccionarVentasFactura(int factura) {
        ArrayList<Venta> ventasFactura = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ps.setInt(1, factura);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idVenta = rs.getInt("Id_Venta");
                Date fechaVenta = rs.getDate("Fecha_Venta");
                double precioVenta = rs.getDouble("Precio_Mueble_Vendido");
                int muebleEnsamblado = rs.getInt("Mueble_Ensamblado");
                String nitCliente = rs.getString("Cliente");
                int salaVenta = rs.getInt("Sala_Venta");
                int caja = rs.getInt("Caja");
                ventasFactura.add(new Venta(idVenta, fechaVenta.toLocalDate(), precioVenta, managerCliente.seleccionarCliente(nitCliente), managerCaja.seleccionarCaja(caja), managerFactura.seleccionarFactura(factura), managerSalaVentas.seleccionarSalaVentas(salaVenta), managerEnsamblajeMueble.seleccionarMuebleEnsamblado(muebleEnsamblado)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ventasFactura;
    }

}
