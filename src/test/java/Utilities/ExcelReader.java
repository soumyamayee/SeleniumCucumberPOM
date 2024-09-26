package Utilities;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static void excelReader() {
        // Wrap the following code inside a method, e.g., main or another function.
        String excelFilePath = "path/to/your/excel/file.xls"; // Correct the path

        try (FileInputStream fis = new FileInputStream(new File(excelFilePath));
             Workbook workbook = new HSSFWorkbook(fis)) {  // For .xls files

            // Access the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Loop through rows and cells
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print("Unknown Type\t");
                            break;
                    }
                }
                System.out.println(); // Print new line after each row
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}