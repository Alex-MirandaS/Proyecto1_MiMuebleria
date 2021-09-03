/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.EnsamblajeMueble;
import CLASES.Usuario;
import ClasesPredeterminadas.Conexion;
import Enums.EnsamblajeMuebleEnum;
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
public class ManagerEnsamblajeMueble {

    private Connection conexion;
    // QUERYS   
    private String insertarEnsamMueble = "INSERT INTO Ensamblaje_Mueble (Fecha_Ensamblaje, Ensamblador, Costo_Ensamblaje, Tipo_Mueble, Sala_Ventas) VALUES(?,?,?,?,?)";
    private String borrarEnsamMueble = "DELETE FROM Ensamblaje_Mueble WHERE Id_Ensamblaje_Mueble = ?";
    private String seleccionarEnsamMueble = "SELECT * FROM Ensamblaje_Mueble WHERE Id_Ensamblaje_Mueble = ?";
    private String seleccionarTodo = "SELECT * FROM Ensamblaje_Mueble";
    private String seleccionarFechaEnsamblaje = "SELECT * FROM Ensamblaje_Mueble WHERE Fecha_Ensamblaje = ?";
    private String seleccionarMueble = "SELECT * FROM Ensamblaje_Mueble WHERE Mueble = ?";
    private String seleccionarEnsamblador = "SELECT * FROM Ensamblaje_Mueble WHERE Ensamblador = ?";
    private String seleccionarTodoASC = "SELECT * FROM Ensamblaje_Mueble ORDER BY Fecha_Ensamblaje ASC";
    private String seleccionarTodoDESC = "SELECT * FROM Ensamblaje_Mueble ORDER BY Fecha_Ensamblaje DESC";

    private String updateFechaEnsamblaje = "UPDATE Ensamblaje_Mueble SET Fecha_Ensamblaje = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updateEnsamblador = "UPDATE Ensamblaje_Mueble SET Ensamblador = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updateCostoEnsamblaje = "UPDATE Ensamblaje_Mueble SET Costo_Ensamblaje = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updateTipoMueble = "UPDATE Ensamblaje_Mueble SET Tipo_Mueble = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updateSalaVentas = "UPDATE Ensamblaje_Mueble SET Sala_Ventas = ? WHERE Id_Ensamblaje_Pieza = ?";
    //Managers
    ManagerSalaVentas managerSV = new ManagerSalaVentas();
    ManagerMueble managerMueble = new ManagerMueble();
    ManagerUsuario managerUsuario = new ManagerUsuario();

    public ManagerEnsamblajeMueble() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarEnsamblajeMueble(LocalDate fechaEnsamblaje, String ensamblador, double costoEnsamblaje, int tipoMueble, int salaVentas) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarEnsamMueble);
            ps.setDate(1, Date.valueOf(fechaEnsamblaje));
            ps.setString(2, ensamblador);
            ps.setDouble(3, costoEnsamblaje);
            ps.setInt(4, tipoMueble);
            ps.setInt(5, salaVentas);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarEnsamblajePieza(int idEnsamblajeMueble) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarEnsamMueble);
            ps.setInt(1, idEnsamblajeMueble);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEnsamblajePieza(int idEnsamblajeMueble, String datoCambiado, EnsamblajeMuebleEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case FechaEnsamblaje:
                    ps = conexion.prepareStatement(updateFechaEnsamblaje);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, idEnsamblajeMueble);
                    break;
                case Ensamblador:
                    ps = conexion.prepareStatement(updateEnsamblador);
                    ps.setString(1, datoCambiado);
                    ps.setInt(2, idEnsamblajeMueble);
                    break;
                case CostoEnsamblaje:
                    ps = conexion.prepareStatement(updateCostoEnsamblaje);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajeMueble);
                    break;
                case TipoMueble:
                    ps = conexion.prepareStatement(updateTipoMueble);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajeMueble);
                    break;
                case SalaVentas:
                    ps = conexion.prepareStatement(updateSalaVentas);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajeMueble);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EnsamblajeMueble> seleccionarTodo() {
        ArrayList<EnsamblajeMueble> ensamblajeMueble = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                String ensamblador = rs.getString("Ensamblador");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");

                ensamblajeMueble.add(new EnsamblajeMueble(idEnsamblajeMueble, fechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajeMueble;
    }

    public ArrayList<EnsamblajeMueble> seleccionarTodoASC() {
        ArrayList<EnsamblajeMueble> ensamblajeMueble = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodoASC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                String ensamblador = rs.getString("Ensamblador");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");

                ensamblajeMueble.add(new EnsamblajeMueble(idEnsamblajeMueble, fechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajeMueble;
    }

    public ArrayList<EnsamblajeMueble> seleccionarTodoDESC() {
        ArrayList<EnsamblajeMueble> ensamblajeMueble = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodoDESC);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                String ensamblador = rs.getString("Ensamblador");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");

                ensamblajeMueble.add(new EnsamblajeMueble(idEnsamblajeMueble, fechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajeMueble;
    }

    public EnsamblajeMueble seleccionarMuebleEnsamblado(int idMuebleEnsamblado) {

        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarMueble);
            ps.setInt(1, idMuebleEnsamblado);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                String ensamblador = rs.getString("Ensamblador");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");
                return new EnsamblajeMueble(idMuebleEnsamblado, fechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<EnsamblajeMueble> seleccionarFechaEnsamblaje(LocalDate fecha) {
        ArrayList<EnsamblajeMueble> ensamblajeMuebles = new ArrayList<>();
        Date datefechaEnsamblaje = Date.valueOf(fecha);
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarFechaEnsamblaje);
            ps.setDate(1, datefechaEnsamblaje);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                String ensamblador = rs.getString("Ensamblador");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");
                ensamblajeMuebles.add(new EnsamblajeMueble(idEnsamblajeMueble, datefechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            //hay error en el localdate
        }
        return ensamblajeMuebles;
    }

    public ArrayList<EnsamblajeMueble> seleccionarEnsamblador(String ensamblador) {
        ArrayList<EnsamblajeMueble> ensamblajeMueble = new ArrayList<>();
        Usuario ensambladorDato = managerUsuario.seleccionarNombre(ensamblador);
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarEnsamblador);
            ps.setString(1, ensamblador);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                int tipoMueble = rs.getInt("Tipo_Mueble");
                int salaVentas = rs.getInt("Sala_Ventas");
                ensamblajeMueble.add(new EnsamblajeMueble(idEnsamblajeMueble, fechaEnsamblaje.toLocalDate(), ensambladorDato, costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(tipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajeMueble;
    }

    public ArrayList<EnsamblajeMueble> seleccionarTipoMueble(int idTipoMueble) {
        ArrayList<EnsamblajeMueble> ensamblajeMueble = new ArrayList<>();

        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarMueble);
            ps.setInt(1, idTipoMueble);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajeMueble = rs.getInt("Id_Ensamblaje_Mueble");
                Date fechaEnsamblaje = rs.getDate("Fecha_Ensamblaje");
                double costoEnsamblaje = rs.getDouble("Costo_Ensamblaje");
                String ensamblador = rs.getString("Ensamblador");
                int salaVentas = rs.getInt("Sala_Ventas");
                ensamblajeMueble.add(new EnsamblajeMueble(idEnsamblajeMueble, fechaEnsamblaje.toLocalDate(), managerUsuario.seleccionarNombre(ensamblador), costoEnsamblaje, managerSV.seleccionarSalaVentas(salaVentas), managerMueble.seleccionarMueble(idTipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajeMueble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajeMueble;
    }

}
