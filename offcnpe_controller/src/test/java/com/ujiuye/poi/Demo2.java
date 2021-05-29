package com.ujiuye.poi;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo2 {
    public static void main(String[] args) throws IOException {
        //新建工作簿     这个工作簿是在内存中创建的
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表
        XSSFSheet sheet = workbook.createSheet("学生信息");
        //创建一个行
        XSSFRow row = sheet.createRow(0);
        //在这个行中创建两个单元格
        row.createCell(0).setCellValue("name");
        row.createCell(1).setCellValue("age");
        //循环创建多行
        for(int i=1;i<=10;i++){
            XSSFRow row2 = sheet.createRow(i);
            //在这个行中创建两个单元格
            row2.createCell(0).setCellValue("name"+1);
            row2.createCell(1).setCellValue(i);
        }
        //创建一个文件  名字可以自定义，但是一定要注意后缀不能写错
        File file=new File("D:/upload/studentdatsa.xlsx");
        //把在内存中创建的工作簿的内容写入到file文件
        workbook.write(new FileOutputStream(file));
        workbook.close();
    }
}
