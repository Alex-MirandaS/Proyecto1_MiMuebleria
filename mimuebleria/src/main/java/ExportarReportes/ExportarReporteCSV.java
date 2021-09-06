/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExportarReportes;

import CLASES.Venta;
import Enums.ReportesEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class ExportarReporteCSV {

    public void generarReporte(HttpServletResponse response, String nombre, ArrayList<Venta>arrayList, ReportesEnum tipoReporte) {

        String columnas = evaluarColumnas(tipoReporte);
        String reporte = "";
        reporte += columnas + "\n";
        for (int i = 0; i < arrayList.size(); i++) {
            Venta datosTemp = arrayList.get(i);
            String fila = evaluarTipoReporte(datosTemp, tipoReporte);
            reporte += fila;
        }
        exportar(response, reporte, nombre);
    }

    private void exportar(HttpServletResponse response, String reporte, String nombre) {
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attatchment; filename=" + nombre + ".csv");
        try (PrintWriter writer = response.getWriter();) {
            writer.println(reporte);
        } catch (IOException e) {
        }
    }

    private String evaluarColumnas(ReportesEnum tipoReporte) {
        switch (tipoReporte) {
            case ReporteGanancias:
                return "ID FACTURA;ID PRODUCTO;PRODUCTO;COSTO;PRECIO VENTA;GANANCIA;NIT CLIENTE;FECHA DE VENTA";
            case ReporteMuebleMasVendido:
                return "ID FACTURA;ID PRODUCTO;PRODUCTO;COSTO;PRECIO VENTA;GANANCIA;NIT CLIENTE;FECHA DE VENTA";
            case ReporteMuebleMenosVendido:
                return "ID FACTURA;ID PRODUCTO;PRODUCTO;COSTO;PRECIO VENTA;GANANCIA;NIT CLIENTE;FECHA DE VENTA";
            case ReporteUsuarioVentas:
                return "ID FACTURA;ID PRODUCTO;PRODUCTO;PRECIO VENTA;CLIENTE;FECHA DE VENTA";
            case ReporteVentas:
                return "ID FACTURA;ID PRODUCTO;PRODUCTO;PRECIO;CLIENTE;FECHA DE VENTA";
            default:
                return "";
        }
    }

    private String evaluarTipoReporte(Venta datosTemp, ReportesEnum tipoReporte) {
        switch (tipoReporte) {
            case ReporteGanancias:
                return datosTemp.getFactura().getIdFactura() + ";" + datosTemp.getMuebleEnsamblado().getIdEnsamblajeMueble() + ";" + datosTemp.getMuebleEnsamblado().getMueble().getNombreMueble() + ";"
                        + datosTemp.getMuebleEnsamblado().getCostoEnsamblaje() + ";" + datosTemp.getPrecioMuebleVendido() + ";" + ((datosTemp.getPrecioMuebleVendido()) - (datosTemp.getMuebleEnsamblado().getCostoEnsamblaje())) + ";"
                        + datosTemp.getCliente().getNIT() + ";" + datosTemp.getFechaVenta() + "\n";
            case ReporteMuebleMasVendido:
                return datosTemp.getFactura().getIdFactura() + ";" + datosTemp.getMuebleEnsamblado().getIdEnsamblajeMueble() + ";" + datosTemp.getMuebleEnsamblado().getMueble().getNombreMueble() + ";"
                        + datosTemp.getMuebleEnsamblado().getCostoEnsamblaje() + ";" + datosTemp.getPrecioMuebleVendido() + ";" + ((datosTemp.getPrecioMuebleVendido()) - (datosTemp.getMuebleEnsamblado().getCostoEnsamblaje())) + ";"
                        + datosTemp.getCliente().getNIT() + ";" + datosTemp.getFechaVenta() + "\n";
            case ReporteMuebleMenosVendido:
                return datosTemp.getFactura().getIdFactura() + ";" + datosTemp.getMuebleEnsamblado().getIdEnsamblajeMueble() + ";" + datosTemp.getMuebleEnsamblado().getMueble().getNombreMueble() + ";"
                        + datosTemp.getMuebleEnsamblado().getCostoEnsamblaje() + ";" + datosTemp.getPrecioMuebleVendido() + ";" + ((datosTemp.getPrecioMuebleVendido()) - (datosTemp.getMuebleEnsamblado().getCostoEnsamblaje())) + ";"
                        + datosTemp.getCliente().getNIT() + ";" + datosTemp.getFechaVenta() + "\n";
            case ReporteUsuarioVentas:
                return datosTemp.getFactura().getIdFactura() + ";" + datosTemp.getMuebleEnsamblado().getIdEnsamblajeMueble() + ";" + datosTemp.getMuebleEnsamblado().getMueble().getNombreMueble() + ";"
                        +  datosTemp.getPrecioMuebleVendido() + ";" + datosTemp.getCliente().getNIT() + ";" + datosTemp.getFechaVenta() + "\n";
            case ReporteVentas:
                return datosTemp.getFactura().getIdFactura() + ";" + datosTemp.getMuebleEnsamblado().getIdEnsamblajeMueble() + ";" + datosTemp.getMuebleEnsamblado().getMueble().getNombreMueble() + ";"
                        + datosTemp.getPrecioMuebleVendido() + ";" + datosTemp.getCliente().getNIT() + ";" + datosTemp.getFechaVenta() + "\n";
            default:
                return "";
        }
    }
}
