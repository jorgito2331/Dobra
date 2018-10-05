/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class Reportes extends Thread{

    private static final String[] columns = {"NOMBRE_OBRA", "CONTRATISTA", "TIPO", "DIRECCION", "FECHA_INICIO", "FECHA_FIN", "VALOR", "ESTADO", "DESFASES"};
    private String respuesta = "";
    private ArrayList<ObraDTO> obras;

    public Reportes(ArrayList<ObraDTO> obras) {
        this.obras = obras;
    }
    
    @Override
    public void run() {
        try (Workbook workbook = new XSSFWorkbook()) {
            CreationHelper createHelper = workbook.getCreationHelper();
            Sheet sheet = workbook.createSheet("Obras");
            Row headerRow = sheet.createRow(0);
            CellStyle backgroundStyle = workbook.createCellStyle();
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
            for(int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(backgroundStyle);
            }   // Create Cell Style for formatting Date
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            // Create Other rows and cells with employees data
            int rowNum = 1;
            for(ObraDTO obra: obras) {
                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0)
                        .setCellValue(obra.getNombre());
                
                row.createCell(1)
                        .setCellValue(obra.getContratista());
                
                row.createCell(2)
                        .setCellValue(obra.getTipo());
                
                row.createCell(3)
                        .setCellValue(obra.getDireccion().getCompleta());
                
                Cell dateOfBirthCell = row.createCell(4);
                dateOfBirthCell.setCellValue(obra.getFechaInicio());
                dateOfBirthCell.setCellStyle(dateCellStyle);
                
                Cell dateOfBirthCell1 = row.createCell(5);
                dateOfBirthCell1.setCellValue(obra.getFechaFin());
                dateOfBirthCell1.setCellStyle(dateCellStyle);
                
                row.createCell(6)
                        .setCellValue(obra.getValor());
                
                row.createCell(7)
                        .setCellValue(obra.getEstado());
                
                row.createCell(8)
                        .setCellValue(obra.getDesfaces());
            }   // Resize all columns to fit the content size
            for(int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }   // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("C:/Users/Jorge-PC/Desktop/bd_Service_Desl/poi-generated-file.xlsx", false)) {
                System.out.println("generado");
                workbook.write(fileOut);
                // Closing the workbook
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        respuesta = null;
    }

}
