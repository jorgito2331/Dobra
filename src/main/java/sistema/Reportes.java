/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jorge-PC
 */
public class Reportes extends Thread {

    private static final String[] columns = {"NOMBRE OBRA", "CONTRATISTA", "TIPO", "DIRECCIÓN", "FECHA INICIO", "FECHA FIN", "VALOR", "ESTADO", "FECHA FINALIZACION", "DESFASES DINERO", "DESFASES DIAS"};
    private boolean respuesta = true;
    private ArrayList<ObraDTO> obras;
    private String usuario;
    private int aux;

    public Reportes(ArrayList<ObraDTO> obras, String usuario) {
        this.obras = obras;
        this.usuario = usuario;
        aux = 0;
    }

    @Override
    public void run() {
        try (Workbook workbook = new XSSFWorkbook()) {
            ParametroNegocio parametroNegocio = new ParametroImplementacion();
            SimpleDateFormat ft = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            File archivo = new File(parametroNegocio.obtenerParametro("4").getNombre() + "/" + ft.format(new Date()) + ".xlsx");
            aux = Integer.parseInt(parametroNegocio.obtenerParametro("5").getNombre());
            aux = aux * -1;
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Obras");
            CellStyle backgroundStyle = workbook.createCellStyle();
            backgroundStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            backgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            backgroundStyle.setBorderBottom(BorderStyle.THIN);
            backgroundStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            backgroundStyle.setBorderLeft(BorderStyle.THIN);
            backgroundStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            backgroundStyle.setBorderRight(BorderStyle.THIN);
            backgroundStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            backgroundStyle.setBorderTop(BorderStyle.THIN);
            backgroundStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            borderStyle.setBorderRight(BorderStyle.THIN);
            borderStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

            Cell cell = null;
            Row usuarioGenera = sheet.createRow(0);
            cell = usuarioGenera.createCell(0);
            cell.setCellValue("Usuario");
            cell.setCellStyle(backgroundStyle);
            cell = usuarioGenera.createCell(1);
            cell.setCellValue(usuario);
            cell.setCellStyle(borderStyle);

            Row fecha = sheet.createRow(1);
            cell = fecha.createCell(0);
            cell.setCellValue("Fecha");
            cell.setCellStyle(backgroundStyle);
            cell = fecha.createCell(1);
            cell.setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            cell.setCellStyle(borderStyle);

            Row CantidadObras = sheet.createRow(2);
            cell = CantidadObras.createCell(0);
            cell.setCellValue("Cantidad Obras");
            cell.setCellStyle(backgroundStyle);
            cell = CantidadObras.createCell(1);
            cell.setCellValue(obras.size());
            cell.setCellStyle(borderStyle);

            Row headerRow = sheet.createRow(4);
            for (int i = 0; i < columns.length; i++) {
                cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(backgroundStyle);
            }   // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            // Create Other rows and cells with employees data
            int rowNum = 5;
            for (ObraDTO obra : obras) {
                Row row = sheet.createRow(rowNum++);

                cell = row.createCell(0);
                cell.setCellValue(obra.getNombre());
                cell.setCellStyle(borderStyle);

                cell = row.createCell(1);
                cell.setCellValue(obra.getContratista());
                cell.setCellStyle(borderStyle);

                cell = row.createCell(2);
                cell.setCellValue(obra.getTipo());
                cell.setCellStyle(borderStyle);

                cell = row.createCell(3);
                cell.setCellValue(obra.getDireccion().getCompleta());
                cell.setCellStyle(borderStyle);

                Cell dateOfBirthCell = row.createCell(4);
                dateOfBirthCell.setCellValue(obra.getFechaInicio());
                dateOfBirthCell.setCellStyle(dateCellStyle);
                dateOfBirthCell.setCellStyle(borderStyle);

                Cell dateOfBirthCell1 = row.createCell(5);
                dateOfBirthCell1.setCellValue(obra.getFechaFin());
                dateOfBirthCell1.setCellStyle(dateCellStyle);
                dateOfBirthCell1.setCellStyle(borderStyle);

                cell = row.createCell(6);
                cell.setCellValue(obra.getValor());
                cell.setCellStyle(borderStyle);
                
                cell = row.createCell(7);
                cell.setCellValue((obra.getEstado().equals("1") ? "Finalizada " + ((obra.getDiasDesfase() != null && Integer.parseInt(obra.getDiasDesfase()) > 0 ) ? " con desfases" : "") : "En ejecución "  + ((obra.getDiasDesfase() != null && Integer.parseInt(obra.getDiasDesfase()) > 0 ) ? " con desfases" : ((obra.getDiferenciaDias() >= aux && obra.getDiferenciaDias() <= 0) ? " proxima a desfase" : "")) ));
                cell.setCellStyle(borderStyle);

                cell = row.createCell(8);
                cell.setCellValue(obra.getFinalizado());
                cell.setCellStyle(borderStyle);
                
                cell = row.createCell(9);
                cell.setCellValue(obra.getDesfaseDinero());
                cell.setCellStyle(borderStyle);
                
                cell = row.createCell(10);
                cell.setCellValue(obra.getDiasDesfase());
                cell.setCellStyle(borderStyle);
            }   // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }   // Write the output to a file
            try {
                // Creamos el flujo de salida de datos,
                // apuntando al archivo donde queremos 
                // almacenar el libro de Excel
                FileOutputStream salida = new FileOutputStream(archivo);

                // Almacenamos el libro de 
                // Excel via ese 
                // flujo de datos
                workbook.write(salida);

                // Cerramos el libro para concluir operaciones
                workbook.close();
                salida.close();
            } catch (FileNotFoundException ex) {
            } catch (IOException ex) {
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        respuesta = true;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

}
