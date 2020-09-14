package writers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ToExcelWriter {

    public void WriteToExcel(){
        Workbook workbook = new XSSFWorkbook();
        Sheet newSheet = workbook.createSheet("Совершенно новый лист");

        Row row = newSheet.createRow(0);
        row.createCell(0).setCellValue("gordonWar");
        row.createCell(1).setCellValue("subalhalur");

        Row row1 = newSheet.createRow(1);
        row1.createCell(0).setCellValue("Salozarest");
        row1.createCell(1).setCellValue("eremen");


        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(".\\writed.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Файл writed.xlsx создан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
