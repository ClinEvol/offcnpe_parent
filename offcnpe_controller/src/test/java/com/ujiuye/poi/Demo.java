package com.ujiuye.poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo {
    public static void main(String[] args) throws IOException {
        //获取整个表格
        XSSFWorkbook sheets = new XSSFWorkbook(new FileInputStream("D:/upload/demo.xlsx"));
        //获取第一个工作表
        XSSFSheet sheet = sheets.getSheetAt(0);
        //获取数据有几行
        int lastRowNum = sheet.getLastRowNum();
        //一行一行的来读取表格
        for (int i=1;i<=lastRowNum;i++){
            XSSFRow row = sheet.getRow(i);
            //第一行有好多个单元格
            String name = row.getCell(0).getStringCellValue();
            double age = row.getCell(1).getNumericCellValue();
            System.out.println("name="+name+",age="+age);
        }
        sheets.close();
    }
}
