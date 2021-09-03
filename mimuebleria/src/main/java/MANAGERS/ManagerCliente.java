/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Cliente;
import ClasesPredeterminadas.Conexion;
import Enums.ClienteEnum;

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
public class ManagerCliente {

    private Connection conexion;
    // QUERYS   
    private String insertarCliente = "INSERT INTO Cliente (NIT, Direccion, Municipio, Departamento, Nombre_Cliente) VALUES(?,?,?,?,?)";
    private String borrarCliente = "DELETE FROM Cliente WHERE NIT = ?";
    private String seleccionarCliente = "SELECT * FROM Cliente WHERE NIT = ?";
    private String seleccionarTodo = "SELECT * FROM Cliente";
    private String updateNIT = "UPDATE Cliente SET NIT = ? WHERE NIT = ?";
    private String updateNombre = "UPDATE Cliente SET Nombre_Cliente = ? WHERE NIT = ?";
    private String updateDireccion = "UPDATE Cliente SET Direccion = ? WHERE NIT = ?";
    private String updateMunicipio = "UPDATE Cliente SET Municipio = ? WHERE NIT = ?";
    private String updateDepartamento = "UPDATE Cliente SET Departamento = ? WHERE NIT = ?";

    //UPDATE
//request es la info de la pagina web que obtuvimos
    //<%bloque de codigo java%>
    //<%= solo una linea de codigo, sin necesidad de cerrar con ';' %>
    //request es la otra pagina
    //Servlet es un .java con codigo html
    public ManagerCliente() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarCliente(int nit, String nombre, String direccion) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarCliente);
            ps.setInt(1, nit);
            ps.setString(3, nombre);
            ps.setString(2, direccion);
            ps.setString(4, "Municipio");
            ps.setString(5, "Departamento");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarClienteDM(int nit, String nombre, String direccion, String municipio, String departamento) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarCliente);
            ps.setInt(1, nit);
            ps.setString(3, nombre);
            ps.setString(2, direccion);
            ps.setString(4, "Ciudad");
            ps.setString(5, "Departamento");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarCliente(int nit) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarCliente);
            ps.setInt(1, nit);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCliente(int nit, String datoCambiado, ClienteEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case NIT:
                    ps = conexion.prepareStatement(updateNIT);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, nit);
                    break;
                case Nombre_Cliente:
                    ps = conexion.prepareStatement(updateNombre);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
                case Direccion:
                    ps = conexion.prepareStatement(updateDireccion);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
                case Municipio:
                    ps = conexion.prepareStatement(updateMunicipio);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
                case Departamento:
                    ps = conexion.prepareStatement(updateDepartamento);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Cliente> seleccionarTodo() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nit = rs.getString("NIT");
                String nombre = rs.getString("Nombre_Cliente");
                String direccion = rs.getString("Direccion");
                Cliente cliente = new Cliente(nombre, nit, direccion);
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public Cliente seleccionarCliente(String nit) {
        Cliente cliente = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarCliente);
            ps.setString(1, nit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("Nombre_Cliente");
                String direccion = rs.getString("Direccion");
                cliente = new Cliente(nombre, nit, direccion);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;
    }

}
