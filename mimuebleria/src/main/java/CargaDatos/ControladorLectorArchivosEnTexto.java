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
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class ControladorLectorArchivosEnTexto {

    private LectorArchivosEnTexto lector;

    public ControladorLectorArchivosEnTexto(LectorArchivosEnTexto lector) {
        this.lector = lector;
    }

    public void evaluarCampos(String[] campos, CargaDatosEnum dato) {
        boolean guardar;
        switch (dato) {
            case USUARIO:
                guardar = verificarDatosUsuario(campos);
                break;
            case PIEZA:
                guardar = verificarDatosPieza(campos);
                break;
            case MUEBLE:
                guardar = verificarDatosMueble(campos);
                break;
            case ENSAMBLAJE_PIEZA:
                guardar = verificarDatosEnsamblarPieza(campos);
                break;
            case ENSAMBLAR_MUEBLE:
                guardar = verificarDatosEnsamblarMueble(campos);
                break;
            case CLIENTE:
                guardar = verificarDatosCliente(campos);
                break;
            default:
                guardar = false;
                break;

        }

        if (guardar) {
            guardarEnArray(dato, crearObjeto(campos, dato));
        } else {
            guardarEnArray(null, crearObjeto(campos, dato));
        }
    }

    private boolean verificarDatosUsuario(String[] campos) {
        try {
            if (campos.length > 3 || campos.length < 1) {
                return false;
            } else {
                String nombreUsuario = campos[0];
                String contraseña = campos[1];
                int tipo = Integer.parseInt(campos[3]);
                String[] lista = new String[lector.getUsuarios().size()];

                for (int i = 0; i < lector.getUsuarios().size(); i++) {
                    lista[i] = lector.getUsuarios().get(i).getNombreUsuario();
                }

                if (!datoRepetido(nombreUsuario, lista)
                        && contraseña.length() > 6
                        && tipo > 0 && tipo < 4) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
//EL FORMATO DEL TIPO DE USUARIO NO ES VALIDO
        }
        return false;
    }

    private boolean verificarDatosPieza(String[] campos) {
        try {
            if (campos.length > 2 || campos.length < 1) {
                return false;
            } else {
                String nombrePieza = campos[0];
                double costo = Double.parseDouble(campos[1]);
                String[] lista = new String[lector.getTiposPiezas().size()];

                for (int i = 0; i < lector.getTiposPiezas().size(); i++) {
                    lista[i] = lector.getTiposPiezas().get(i).getNombre();
                }

                if (!datoRepetido(nombrePieza, lista)) {
                    lector.getTiposPiezas().add(new Tipo(lector.getTiposPiezas().size() + 1, nombrePieza));
                } else {
                    for (int i = 0; i < lista.length; i++) {
                        if (nombrePieza.equalsIgnoreCase(lista[i])) {
                            lector.getTiposPiezas().get(i).agregarUnidades(1);
                        }
                    }
                }
                return true;
            }
        } catch (NumberFormatException e) {
//EL FORMATO DEL TIPO DE USUARIO NO ES VALIDO
        } catch (NullPointerException e) {
            //NO HA INGRESADO NINGUN DATO
        }
        return false;
    }

    private boolean verificarDatosMueble(String[] campos) {
        try {
            if (campos.length > 2 || campos.length < 1) {
                return false;
            } else {
                String nombreMueble = campos[0];
                Double precio = Double.parseDouble(campos[1]);
                String[] lista = new String[lector.getMuebles().size()];

                for (int i = 0; i < lector.getMuebles().size(); i++) {
                    lista[i] = lector.getMuebles().get(i).getNombreMueble();
                }

                if (!datoRepetido(nombreMueble, lista)) {
                    lector.getSalaVentas().add(new SalaVenta(1, nombreMueble, precio));
                    return true;
                }
            }
        } catch (NumberFormatException e) {
//EL FORMATO DEL TIPO DE USUARIO NO ES VALIDO
        } catch (NullPointerException e) {
            //NO HA INGRESADO NINGUN DATO
        }
        return false;
    }

    private boolean verificarDatosCliente(String[] campos) {
        try {
            if (campos.length > 5 || campos.length < 1) {
                return false;
            } else {
                String nombre = campos[0];
                String NIT = campos[1];
                String direccion = campos[2];

                String[] lista = new String[lector.getClientes().size()];

                for (int i = 0; i < lector.getClientes().size(); i++) {
                    lista[i] = lector.getClientes().get(i).getNIT();
                }

                if (!incluyeDato(NIT, '-') && !datoRepetido(NIT, lista)) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
//EL FORMATO DEL TIPO DE USUARIO NO ES VALIDO
        }
        return false;
    }

    private boolean verificarDatosEnsamblarPieza(String[] campos) {
        try {
            if (campos.length > 3 || campos.length < 1) {
                return false;
            } else {
                String mueble = campos[0];
                String pieza = campos[1];
                int cantidad = Integer.parseInt(campos[2]);
                String[] listaMuebles = new String[lector.getMuebles().size()];
                String[] listaTiposPieza = new String[lector.getTiposPiezas().size()];

                for (int i = 0; i < lector.getMuebles().size(); i++) {
                    listaMuebles[i] = lector.getMuebles().get(i).getNombreMueble();
                }

                for (int i = 0; i < lector.getTiposPiezas().size(); i++) {
                    listaTiposPieza[i] = lector.getTiposPiezas().get(i).getNombre();
                }

                if (datoRepetido(mueble, listaMuebles) && datoRepetido(pieza, listaTiposPieza)) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
//NO INGRESO UN NUMERO COMO CANTIDAD DE PIEZAS
        } catch (NullPointerException e) {
            //NO HA INGRESADO NINGUN DATO
        }
        return false;
    }

    private boolean verificarDatosEnsamblarMueble(String[] campos) {
        try {
            if (campos.length > 3 || campos.length < 1) {
                return false;
            } else {
                String mueble = campos[0];
                String ensambladorUs = campos[1];
                Date fecha = Date.valueOf(campos[2]);
                String[] listaMuebles = new String[lector.getMuebles().size()];
                String[] listaEnsambladores = new String[lector.getUsuarios().size()];

                for (int i = 0; i < lector.getMuebles().size(); i++) {
                    listaMuebles[i] = lector.getMuebles().get(i).getNombreMueble();
                }

                for (int i = 0; i < lector.getUsuarios().size(); i++) {
                    listaEnsambladores[i] = lector.getUsuarios().get(i).getNombreUsuario();
                }

                if (datoRepetido(mueble, listaMuebles) && datoRepetido(ensambladorUs, listaEnsambladores) && piezasNecesarias(mueble)) {
                    return true;
                }
            }
        } catch (IllegalArgumentException e) {
//EL FORMATO DE LA FECHA ESTA MAL PUESTO
        }
        return false;
    }

    private Object crearObjeto(String[] campos, CargaDatosEnum dato) {
        switch (dato) {
            case USUARIO:
                return new Usuario(campos[1], campos[0], Integer.parseInt(campos[2]));
            case PIEZA:
                return new Pieza(Double.parseDouble(campos[1]), 0, obtenerTipoPieza(campos[0]));
            case MUEBLE:
                return new Mueble(0, Double.parseDouble(campos[1]), campos[0]);
            case ENSAMBLAJE_PIEZA:
                return new EnsamblajePieza(0, Integer.parseInt(campos[2]), obtenerTipoPieza(campos[1]), obtenerTipoMueble(campos[0]));
            case ENSAMBLAR_MUEBLE:
                SalaVenta sala = obtenerSalaVenta(campos[0]);
                Mueble mueble = obtenerTipoMueble(campos[0]);
                return new EnsamblajeMueble(0, LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/yyyy")), obtenerEnsamblador(campos[1]), descontarPiezas(mueble), sala, mueble);
            case CLIENTE:
                if (campos.length > 3) {
                    return new Cliente(campos[0], campos[1], campos[2], campos[3], campos[4]);
                } else {
                    return new Cliente(campos[0], campos[1], campos[2]);
                }
            default:
                return unirDatos(campos, dato);
        }
    }

    private void guardarEnArray(CargaDatosEnum dato, Object objeto) {
        switch (dato) {
            case USUARIO:
                lector.getUsuarios().add((Usuario) objeto);
                break;
            case PIEZA:
                lector.getPiezas().add((Pieza) objeto);
                break;
            case MUEBLE:
                lector.getMuebles().add((Mueble) objeto);
                break;
            case CLIENTE:
                lector.getClientes().add((Cliente) objeto);
            default:
                lector.getErrores().add((String) objeto);
                break;
        }
    }

    private boolean datoRepetido(String dato, String[] lista) {
        for (int i = 0; i < lista.length; i++) {
            if (dato.equalsIgnoreCase(lista[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean incluyeDato(String linea, char valor) {
        char[] lineaChar = linea.toCharArray();

        for (int i = 0; i < lineaChar.length; i++) {
            if (lineaChar[i] == valor) {
                return true;
            }
        }
        return false;
    }

    private Tipo obtenerTipoPieza(String dato) {
        for (int i = 0; i < lector.getTiposPiezas().size(); i++) {
            if (dato.equalsIgnoreCase(lector.getTiposPiezas().get(i).getNombre())) {
                return lector.getTiposPiezas().get(i);
            }
        }
        return null;
    }

    private Mueble obtenerTipoMueble(String dato) {
        for (int i = 0; i < lector.getMuebles().size(); i++) {
            if (dato.equalsIgnoreCase(lector.getMuebles().get(i).getNombreMueble())) {
                return lector.getMuebles().get(i);
            }
        }
        return null;
    }

    private Usuario obtenerEnsamblador(String dato) {
        for (int i = 0; i < lector.getUsuarios().size(); i++) {
            if (dato.equalsIgnoreCase(lector.getUsuarios().get(i).getNombreUsuario())) {
                return lector.getUsuarios().get(i);
            }
        }
        return null;
    }

    private SalaVenta obtenerSalaVenta(String tipoMueble) {
        for (int i = 0; i < lector.getSalaVentas().size(); i++) {
            if (tipoMueble.equalsIgnoreCase(lector.getSalaVentas().get(i).getTipoMueble())) {
                lector.getSalaVentas().get(i).sumarExistencias(1);
                return lector.getSalaVentas().get(i);
            }
        }
        return null;
    }

    private boolean piezasNecesarias(String mueble) {
        ArrayList<EnsamblajePieza> piezasEnsambladas = verificarPiezasMueble(mueble);
        for (int i = 0; i < piezasEnsambladas.size(); i++) {
            if (!existenciasDisponibles(piezasEnsambladas.get(i).getPieza(), piezasEnsambladas.get(i).getCantidad())) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<EnsamblajePieza> verificarPiezasMueble(String mueble) {
        ArrayList<EnsamblajePieza> piezasEnsambladas = new ArrayList<>();
        for (int i = 0; i < lector.getEnsamPiezas().size(); i++) {
            if (mueble.equalsIgnoreCase(lector.getEnsamPiezas().get(i).getMueble().getNombreMueble())) {
                piezasEnsambladas.add(lector.getEnsamPiezas().get(i));
            }
        }
        return piezasEnsambladas;
    }

    private boolean existenciasDisponibles(Tipo tipo, int cantidad) {
        for (int i = 0; i < lector.getTiposPiezas().size(); i++) {
            if (tipo.equals(lector.getTiposPiezas().get(i))) {
                if (lector.getTiposPiezas().get(i).getCantidad() >= cantidad) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private double descontarPiezas(Mueble mueble) {
        ArrayList<EnsamblajePieza> piezasEnsambladas = verificarPiezasMueble(mueble.getNombreMueble());
        double costo = 0;
        for (int i = 0; i < piezasEnsambladas.size(); i++) {
            costo += removerPiezas(piezasEnsambladas.get(i).getPieza(), piezasEnsambladas.get(i).getCantidad());
        }
        return costo;
    }

    private double removerPiezas(Tipo pieza, int cantidad) {
        for (int i = 0; i < lector.getTiposPiezas().size(); i++) {
            if (lector.getTiposPiezas().get(i).equals(pieza)) {
                lector.getTiposPiezas().get(i).restarUnidades(cantidad);
                return quitarPiezas(pieza);
            }
        }
        return 0;
    }

    private double quitarPiezas(Tipo pieza) {
        for (int i = 0; i < lector.getPiezas().size(); i++) {
            if (lector.getPiezas().equals(pieza)) {
                return lector.getPiezas().get(i).getCostoUnitario();
            }
        }
        return 0;
    }

    private String unirDatos(String[] campos, CargaDatosEnum dato) {
        String error = dato.getNombre();
        for (int i = 0; i < campos.length; i++) {
            error += campos[i];
        }
        error += ")";
        return error;
    }


    /*
    
    public void cargarObjetoBaseDatos() {
        JFileChooser fileChosser = new JFileChooser();
        int seleccion = fileChosser.showOpenDialog(fileChosser);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //aqui selecciono y guardo el FILE que va a utilizar el FileReader
            File fichero = fileChosser.getSelectedFile();
            try {             
                switch(datos){
                    case USUARIO:
                        
                }
                
            
                if (text.equalsIgnoreCase("Vuelo")) {
                    ArrayList<Vuelo> objetos;
                    objetos = prin.getLectorVuelosTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorVuelos().guardarObjeto(objetos.get(i), objetos.get(i).getCodigoVuelo());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Aeropuerto")) {
                    ArrayList<Aereopuerto> objetos;
                    objetos = prin.getLectorAereopuertosTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorAereopuertos().guardarObjeto(objetos.get(i), objetos.get(i).getNombre());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Aereolínea")) {
                    ArrayList<Aereolínea> objetos;
                    objetos = prin.getLectorAereolineasTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorAereolineas().guardarObjeto(objetos.get(i), objetos.get(i).toString());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Avion")) {
                    ArrayList<Avión> objetos;
                    objetos = prin.getLectorAvionesTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorAviones().guardarObjeto(objetos.get(i), objetos.get(i).getCodigoAvión());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Pasaporte")) {
                    ArrayList<Pasaporte> objetos;
                    objetos = prin.getLectorPasaportesTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorPasaportes().guardarObjeto(objetos.get(i), "" + objetos.get(i).getNumPasaporte());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Tarjeta")) {
                    ArrayList<Tarjeta> objetos;
                    objetos = prin.getLectorTarjetasTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorTarjetas().guardarObjeto(objetos.get(i), "" + objetos.get(i).getNumTarjeta());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Reservación")) {
                    ArrayList<Reservación> objetos;
                    objetos = prin.getLectorReservaciónTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorReservación().guardarObjeto(objetos.get(i), objetos.get(i).toString());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else if (text.equalsIgnoreCase("Distancia")) {
                    ArrayList<Distancia> objetos;
                    objetos = prin.getLectorDistanciaTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorDistancia().guardarObjeto(objetos.get(i), objetos.get(i).toString());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                } else {
                    ArrayList<RenovaciónPasaporte> objetos;
                    objetos = prin.getLectorRenovaciónPasaporteTexto().leerFichero(fichero, text);
                    for (int i = 0; i < objetos.size(); i++) {
                        prin.getEscritorRenovaciónPasaporte().guardarObjeto(objetos.get(i), objetos.get(i).toString());
                    }
                    JOptionPane.showMessageDialog(null, "OBJETOS ENCONTRADOS Y CARGADOS");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al leer el archivo");
                ex.printStackTrace();
            }
        }
     */
}
