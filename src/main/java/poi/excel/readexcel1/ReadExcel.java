package poi.excel.readexcel1;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Created by YanYingying on 2016/6/19.
 */
public class ReadExcel{
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        String fileName = sc.next();
        String fileName = "testdata.xlsx";
        XSSFWorkbook xssfWorkbook = readTestData(fileName);
        convertXLS(xssfWorkbook);
    }

    private static String getValue(XSSFCell xssfCell) {
        if (xssfCell == null) {
            return "";
        }
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(xssfCell.getNumericCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_STRING){
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BLANK) {
            return String.valueOf(xssfCell.getStringCellValue());
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
    private static void readRowData(XSSFRow xssfRow) {
        XSSFCell cell0 = xssfRow.getCell(0);

        String cell0String = getValue(cell0);
        System.out.println(cell0String);

        XSSFCell cell1 = xssfRow.getCell(1);
        String cell1String = getValue(cell1);
        System.out.println(cell1String);

        XSSFCell cell2 = xssfRow.getCell(2);
        String cell2String = getValue(cell2);
        System.out.println(cell2String);

        XSSFCell cell3 = xssfRow.getCell(3);
        String cell3String = getValue(cell3);
        System.out.println(cell3String);
    }

    private static void convertXLS(XSSFWorkbook xssfWorkbook) {

        if (xssfWorkbook == null) {
            return;
        }
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                readRowData(xssfRow);

            }
        }
    }
    private static XSSFWorkbook readTestData(String fileName) {
        String projectPath = System.getProperty("user.dir");
        StringBuilder filePath = new StringBuilder();
        filePath.append(projectPath).append(File.separator)
                .append("src").append(File.separator)
                .append("main").append(File.separator)
                .append("resources").append(File.separator)
                .append("poi").append(File.separator)
                .append("testdata").append(File.separator)
                .append(fileName);

        System.out.println(filePath);
        XSSFWorkbook xssfWorkbook = null;
        try {
            InputStream is = new FileInputStream(filePath.toString());
            xssfWorkbook = new XSSFWorkbook(is);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return xssfWorkbook;

    }
}
