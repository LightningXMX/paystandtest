package poi.excel.readexcel1;

import com.cmcc.pay.util.ExcelUtil;
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
        XSSFWorkbook xssfWorkbook = ExcelUtil.readTestData(fileName);
        ExcelUtil.convertXLS(xssfWorkbook,"pay_order");
    }

}
