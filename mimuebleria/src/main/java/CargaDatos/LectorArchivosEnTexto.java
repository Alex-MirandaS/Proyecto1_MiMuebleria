/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDatos;

import CLASES.Cliente;
import CLASES.EnsamblajeMueble;
import CLASES.EnsamblajePieza;
import CLASES.Mueble;
import CLASES.Pieza;
import CLASES.SalaVenta;
import CLASES.Tipo;
import CLASES.Usuario;
import Enums.CargaDatosEnum;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author richard
 */
public class LectorArchivosEnTexto {

    //Arrays
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Pieza> piezas = new ArrayList<>();
    private ArrayList<Mueble> muebles = new ArrayList<>();
    private ArrayList<EnsamblajeMueble> ensamMuebles = new ArrayList<>();
    private ArrayList<EnsamblajePieza> ensamPiezas = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Tipo> tiposPiezas = new ArrayList<>();
    private ArrayList<SalaVenta> salaVentas = new ArrayList<>();
    private ArrayList<String> errores = new ArrayList<>();
    //Controladores
    ControladorLectorArchivosEnTexto control = new ControladorLectorArchivosEnTexto(this);

    //File=archivo
    //FileReader necesita a un archivo para poder leerlo
    //BufferedReader lee el texto del archivo
    public ArrayList<ArrayList> leerFicheros(File archivo) throws FileNotFoundException, IOException {
        ArrayList<ArrayList> listas = new ArrayList<>();
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
        while ((linea = br.readLine()) != null) {
            //con la linea leida, separamos los campos
            evaluar(linea);
        }
        listas.add(usuarios);
        listas.add(piezas);
        listas.add(muebles);
        listas.add(ensamMuebles);
        listas.add(ensamPiezas);
        listas.add(tiposPiezas);
        listas.add(errores);
        fr.close();
        return listas;
    }

    private void evaluar(String linea) {

        CargaDatosEnum cargaDatosEnum = evaluandoEnum(linea);
        String lineaDeCampos = linea.substring(cargaDatosEnum.getCaracteres(), linea.length() - 1);
        String[] campos = lineaDeCampos.split(",");
        
        control.evaluarCampos(campos, cargaDatosEnum);
    }

    private CargaDatosEnum evaluandoEnum(String linea) {
        char letraInicial = linea.charAt(0);
        switch (letraInicial) {
            case 'U':
                return CargaDatosEnum.USUARIO;
            case 'P':
                return CargaDatosEnum.PIEZA;
            case 'M':
                return CargaDatosEnum.MUEBLE;
            case 'C':
                return CargaDatosEnum.CLIENTE;
            default:
                char letraInter = linea.charAt(11);
                if (letraInter == 'P') {
                    return CargaDatosEnum.ENSAMBLAJE_PIEZA;
                } else if(letraInter == 'M'){
                    return CargaDatosEnum.ENSAMBLAR_MUEBLE;
                }else{
                    return null;
                }
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Pieza> getPiezas() {
        return piezas;
    }

    public ArrayList<Mueble> getMuebles() {
        return muebles;
    }

    public ArrayList<EnsamblajeMueble> getEnsamMuebles() {
        return ensamMuebles;
    }

    public ArrayList<EnsamblajePieza> getEnsamPiezas() {
        return ensamPiezas;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Tipo> getTiposPiezas() {
        return tiposPiezas;
    }

    public ArrayList<String> getErrores() {
        return errores;
    }

    public ArrayList<SalaVenta> getSalaVentas() {
        return salaVentas;
    }
    
    

}
