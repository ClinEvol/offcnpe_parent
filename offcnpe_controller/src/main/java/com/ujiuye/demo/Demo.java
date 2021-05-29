package com.ujiuye.demo;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("运营数据");

        //合并单元格   参数一 第一个单元格所在行.   参数二 第二个单元格所在行, 参数三 第一个单元格所在列,参数四 第二个单元格所在列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        sheet.addMergedRegion(new CellRangeAddress(3,3,0,3));
        sheet.addMergedRegion(new CellRangeAddress(7,7,0,3));



        sheet.createRow(0).createCell(0).setCellValue("会员数据统计");
        sheet.createRow(3).createCell(0).setCellValue("会员数据统计");
        sheet.createRow(7).createCell(0).setCellValue("会员数据统计");

        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("新增会员数");
        row2.createCell(1).setCellValue("新增会员数");


        File file=new File("D:\\a\\demo.xlsx");
        workbook.write(new FileOutputStream(file));
        workbook.close();
    }
}
