package Utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

class ExcelUtility {
    public static String[][] getTestDataFromExcel(String testDataExcelFileName, String sheetName) throws IOException {
        FileInputStream stream = new FileInputStream(pathHelpers.returnTestDataPath() + testDataExcelFileName);
        Workbook workbook = WorkbookFactory.create(stream);
        Sheet s = workbook.getSheet(sheetName);
        int rowcount = s.getLastRowNum();
        int cellcount = s.getRow(0).getLastCellNum();
        String[][] data = new String[rowcount][cellcount];
        for (int i = 1; i <= rowcount; i++) {
            Row r = s.getRow(i);
            for (int j = 0; j < cellcount; j++) {
                Cell c = r.getCell(j);
                switch (c.getCellType()) {
                    case STRING:
                        data[i - 1][j] = String.valueOf(c.getRichStringCellValue());
                        break;
                    case _NONE:
                        break;
                    case NUMERIC:
                        data[i - 1][j] = String.valueOf(c.getNumericCellValue());
                        break;
                    case FORMULA:
                        System.out.println("ToDO");
                        break;
                    case BLANK:
                        System.out.println("ToDO");
                        break;
                    case BOOLEAN:
                        data[i - 1][j] = String.valueOf(c.getBooleanCellValue());
                        break;
                    case ERROR:
                        System.out.println("ToDO");
                        break;
                    default:
                        System.out.println("Incorrect data in excel");
                }
                //return data;
            }
        }
        return data;
    }
}