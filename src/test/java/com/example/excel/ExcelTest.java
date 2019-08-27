package com.example.excel;

import junit.framework.TestCase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author zw
 * @date 2019-8-23
 * <p>
 * Java  POI 和 JExcelAPI 、 EasyPOI
 */
public class ExcelTest extends TestCase {


    /**
     * HSSF提供读写Microsoft Excel XLS格式档案的功能。
     * <p>
     * XSSF提供读写Microsoft Excel OOXML XLSX格式档案的功能。
     * <p>
     * HWPF提供读写Microsoft Word DOC格式档案的功能。
     * <p>
     * HSLF提供读写Microsoft PowerPoint格式档案的功能。
     * <p>
     * HDGF提供读Microsoft Visio格式档案的功能。
     * <p>
     * HPBF提供读Microsoft Publisher格式档案的功能。
     * <p>
     * HSMF提供读Microsoft Outlook格式档案的功能。
     */
    public void testPOI() throws Exception {
//        HSSFWorkbook wb = new HSSFWorkbook();
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet test = wb.createSheet("Test");
        for (int i = 1; i <= 9; i++) {
            XSSFRow row = test.createRow(i - 1);
            for (int j = 1; j <= i; j++) {
                XSSFCell cell = row.createCell(j - 1);
                cell.setCellValue(String.format("%d * %d = %d", i, j, i * j));
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./test.xlsx");
            wb.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("=================生成九九乘法表成功====================");

        XSSFWorkbook workbook = new XSSFWorkbook(new File("./test.xlsx"));
//        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFSheet sheet = workbook.getSheet("Test");
        int rowNum = sheet.getLastRowNum();
        XSSFRow row;
        for (int i = 0; i <= rowNum; i++) {
            row = sheet.getRow(i);
            short cellNum = row.getLastCellNum();
            for (int j = 0; j < cellNum; j++) {
                XSSFCell cell = row.getCell(j);
                System.out.print(cell + "\t");
//                String rawValue = cell.getRawValue();
//                System.out.println(rawValue);
            }
            System.out.println();
        }

    }

    public void testJExcelAPI() {

    }

    /**
     * http://easypoi.mydoc.io/
     */
    public void testEasyPOI() {

    }
}
