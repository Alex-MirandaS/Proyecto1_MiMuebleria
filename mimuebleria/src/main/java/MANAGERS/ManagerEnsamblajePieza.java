/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MANAGERS;

import CLASES.EnsamblajePieza;
import CLASES.Mueble;
import CLASES.Tipo;
import ClasesPredeterminadas.Conexion;
import Enums.EnsamblajePiezasEnum;
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
public class ManagerEnsamblajePieza {

    private Connection conexion;
    // QUERYS   
    private String insertarEnsamPieza = "INSERT INTO Ensamblaje_Piezas(Cantidad_Piezas, TipoPieza, Mueble) VALUES(?,?,?)";
    private String borrarEnsamPieza = "DELETE FROM Ensamblaje_Piezas WHERE Id_Ensamblaje_Pieza = ?";
    private String seleccionarEnsamPieza = "SELECT * FROM Ensamblaje_Piezas WHERE Id_Ensamblaje_Pieza = ?";
    private String seleccionarTodo = "SELECT * FROM Ensamblaje_Piezas";
    private String seleccionarTipoPieza = "SELECT * FROM Ensamblaje_Piezas WHERE TipoPieza = ?";
    private String seleccionarMueble = "SELECT * FROM Ensamblaje_Piezas WHERE Mueble = ?";
    private String updateCantidadPiezas = "UPDATE Ensamblaje_Piezas SET Cantidad_Piezas = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updatePieza = "UPDATE Ensamblaje_Piezas SET TipoPieza = ? WHERE Id_Ensamblaje_Pieza = ?";
    private String updateMueble = "UPDATE Ensamblaje_Piezas SET Mueble = ? WHERE Id_Ensamblaje_Pieza = ?";
//Managers
    ManagerTipoPieza managerTipoPieza = new ManagerTipoPieza();
    ManagerMueble managerMueble = new ManagerMueble();

    public ManagerEnsamblajePieza() {
        this.conexion = Conexion.getConnection();
    }

    public void insertarEnsamblajePieza(int cantidadPiezas, int idPieza, int idMueble) {

        try {
            PreparedStatement ps = conexion.prepareStatement(insertarEnsamPieza);
            ps.setInt(1, cantidadPiezas);
            ps.setInt(2, idPieza);
            ps.setInt(3, idMueble);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarEnsamblajePieza(int Id_Ensamblaje_Pieza) {

        try {
            PreparedStatement ps = conexion.prepareStatement(borrarEnsamPieza);
            ps.setInt(1, Id_Ensamblaje_Pieza);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEnsamblajePieza(int idEnsamblajePieza, String datoCambiado, EnsamblajePiezasEnum tipoCambio) {

        try {
            PreparedStatement ps = null;

            switch (tipoCambio) {
                case Cantidad_Piezas:
                    ps = conexion.prepareStatement(updateCantidadPiezas);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajePieza);
                    break;
                case Pieza:
                    ps = conexion.prepareStatement(updatePieza);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajePieza);
                    break;
                case Mueble:
                    ps = conexion.prepareStatement(updateMueble);
                    ps.setInt(1, Integer.parseInt(datoCambiado));
                    ps.setInt(2, idEnsamblajePieza);
                    break;
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<EnsamblajePieza> seleccionarTodo() {
        ArrayList<EnsamblajePieza> ensamblajePiezas = new ArrayList<>();
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTodo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajePieza = rs.getInt("Id_Ensamblaje_Pieza");
                int cantidad = rs.getInt("Cantidad_Piezas");
                int idTipoPieza = rs.getInt("TipoPieza");
                int idTipoMueble = rs.getInt("Mueble");

                ensamblajePiezas.add(new EnsamblajePieza(idEnsamblajePieza, cantidad, managerTipoPieza.seleccionarTipoPieza(idTipoPieza), managerMueble.seleccionarMueble(idTipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajePiezas;
    }

    public ArrayList<EnsamblajePieza> seleccionarTipoPieza(int idTipoPieza) {
        ArrayList<EnsamblajePieza> ensamblajePiezas = new ArrayList<>();
        Tipo tipo = managerTipoPieza.seleccionarTipoPieza(idTipoPieza);
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarTipoPieza);
            ps.setInt(1, idTipoPieza);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idEnsamblajePieza = rs.getInt("Id_Ensamblaje_Pieza");
                int cantidad = rs.getInt("Cantidad_Piezas");
                int idTipoMueble = rs.getInt("Mueble");
                ensamblajePiezas.add(new EnsamblajePieza(idEnsamblajePieza, cantidad, tipo, managerMueble.seleccionarMueble(idTipoMueble)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajePiezas;
    }

    public ArrayList<EnsamblajePieza> seleccionarMueble(int idMueble) {
        ArrayList<EnsamblajePieza> ensamblajePiezas = new ArrayList<>();
        Mueble mueble = managerMueble.seleccionarMueble(idMueble);
        try {
            PreparedStatement ps = conexion.prepareStatement(seleccionarMueble);
            ps.setInt(1, idMueble);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajePieza = rs.getInt("Id_Ensamblaje_Pieza");
                int cantidad = rs.getInt("Cantidad_Piezas");
                int idTipoPieza = rs.getInt("TipoPieza");
                ensamblajePiezas.add(new EnsamblajePieza(idEnsamblajePieza, cantidad, managerTipoPieza.seleccionarTipoPieza(idTipoPieza), mueble));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensamblajePiezas;
    }

    public EnsamblajePieza seleccionarEnsamblajePieza(int idEnsamblajePieza) {
        EnsamblajePieza ensam = null;
        try {

            PreparedStatement ps = conexion.prepareStatement(seleccionarEnsamPieza);
            ps.setInt(1, idEnsamblajePieza);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idEnsamblajePieza1 = rs.getInt("Id_Ensamblaje_Pieza");
                int cantidad = rs.getInt("Cantidad_Piezas");
                int idTipoPieza = rs.getInt("TipoPieza");
                int idTipoMueble = rs.getInt("Mueble");
                ensam = new EnsamblajePieza(idEnsamblajePieza1, cantidad, managerTipoPieza.seleccionarTipoPieza(idTipoPieza), managerMueble.seleccionarMueble(idTipoMueble));
                break;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManagerEnsamblajePieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ensam;
    }

}
