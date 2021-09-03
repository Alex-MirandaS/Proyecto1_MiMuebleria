/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.Usuario;
import ClasesPredeterminadas.Conexion;
import Enums.UsuarioEnum;
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
public class ManagerUsuario {

    private Connection conexion;
    // QUERYS   
    private String insertarUsuario = "INSERT INTO Usuario (Nombre_Usuario, Contraseña, Tipo) VALUES(?,?,?)";
    private String borrarUsuario = "DELETE FROM Usuario WHERE Nombre_Usuario = ? AND Contraseña = ? ";
    private String seleccionarUsuario = "SELECT * FROM Usuario WHERE Nombre_Usuario = ?";
    private String seleccionarTodo = "SELECT * FROM Usuario"; 
    private String updateNombreUsuario = "UPDATE Usuario SET Nombre_Usuario = ? WHERE Nombre_Usuario = ?";
    private String updateContraseña = "UPDATE Usuario SET Contraseña = ? WHERE Nombre_Usuario = ?";
    private String updateTipo = "UPDATE Usuario SET Tipo = ? WHERE Nombre_Usuario = ?";

    public ManagerUsuario() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarUsuario(String nombre, String contraseña, String tipo) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarUsuario);
            
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, tipo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarUsuario(String nombre, String contraseña) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarUsuario);
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUsuario(int nit, String datoCambiado, UsuarioEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case NombreUsuario:
                    ps = conexion.prepareStatement(updateNombreUsuario);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
                case Contraseña:
                    ps = conexion.prepareStatement(updateContraseña);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, nit);
                    break;
                case Tipo:
                    ps = conexion.prepareStatement(updateContraseña);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, nit);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Usuario> seleccionarTodo() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombreUsuario = rs.getString("Nombre_Usuario");
                String contraseña = rs.getString("Contraseña");
                int tipo = rs.getInt("Tipo");
                usuarios.add(new Usuario(contraseña, nombreUsuario, tipo));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public Usuario seleccionarNombre(String nombre) {
        Usuario usuario = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarUsuario);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 String nombreUsuario = rs.getString("Nombre_Usuario");
                String contraseña = rs.getString("Contraseña");
                int tipo = rs.getInt("Tipo");
                usuario = new Usuario(contraseña, nombreUsuario, tipo);
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
