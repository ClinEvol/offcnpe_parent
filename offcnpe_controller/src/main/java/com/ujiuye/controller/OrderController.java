package com.ujiuye.controller;


import com.ujiuye.service.IMemberService;
import com.ujiuye.service.IOrderService;
import com.ujiuye.utils.util.MessageConstant;
import com.ujiuye.utils.util.MyFileUtils;
import com.ujiuye.utils.util.Result;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zs
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private IOrderService orderService;
    @Reference
    private IMemberService memberService;

    @RequestMapping("/getBusiness")
    public Result getBusiness(){
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("todayNewMember",memberService.todayNewMember());//本日新增会员数
            map.put("totalMember",memberService.totalMember());//总会员数
            map.put("thisWeekNewMember",memberService.thisWeekNewMember());//本周新增会员数
            map.put("thisMonthNewMember",memberService.thisMonthNewMember());//本月新增会员数

            map.put("todayOrderNumber",orderService.todayOrderNumber());//今日预约数
            map.put("todayVisitsNumber",orderService.todayVisitsNumber());//今日到诊数
            map.put("thisWeekOrderNumber",orderService.thisWeekOrderNumber());//本周预约数
            map.put("thisWeekVisitsNumber",orderService.thisWeekVisitsNumber());//本周到诊数
            map.put("thisMonthOrderNumber",orderService.thisMonthOrderNumber());//本月预约数
            map.put("thisMonthVisitsNumber",orderService.thisMonthVisitsNumber());//本月到诊数

            map.put("hotSetmeal",orderService.hotSetmeal());//本月到诊数
            return new Result(true, MessageConstant.QUERY_BUSINESS_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_BUSINESS_FAIL);
        }
    }


    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(){
        try{
            Map<String,Object> map=new HashMap<>();
            map.put("todayNewMember",memberService.todayNewMember());//本日新增会员数
            map.put("totalMember",memberService.totalMember());//总会员数
            map.put("thisWeekNewMember",memberService.thisWeekNewMember());//本周新增会员数
            map.put("thisMonthNewMember",memberService.thisMonthNewMember());//本月新增会员数

            map.put("todayOrderNumber",orderService.todayOrderNumber());//今日预约数
            map.put("todayVisitsNumber",orderService.todayVisitsNumber());//今日到诊数
            map.put("thisWeekOrderNumber",orderService.thisWeekOrderNumber());//本周预约数
            map.put("thisWeekVisitsNumber",orderService.thisWeekVisitsNumber());//本周到诊数
            map.put("thisMonthOrderNumber",orderService.thisMonthOrderNumber());//本月预约数
            map.put("thisMonthVisitsNumber",orderService.thisMonthVisitsNumber());//本月到诊数

            map.put("hotSetmeal",orderService.hotSetmeal());//本月到诊数
            File file = mapToExcel(map);
            return MyFileUtils.download(file.getName(),"D:\\a\\");
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

    private File mapToExcel(Map<String,Object> map){
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("运营数据");

        //合并单元格   参数一 第一个单元格所在行.   参数二 第二个单元格所在行, 参数三 第一个单元格所在列,参数四 第二个单元格所在列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        sheet.addMergedRegion(new CellRangeAddress(3,3,0,3));
        sheet.addMergedRegion(new CellRangeAddress(7,7,0,3));


        sheet.createRow(0).createCell(0).setCellValue("会员数据统计");
        sheet.createRow(3).createCell(0).setCellValue("预约到诊数据统计");
        sheet.createRow(7).createCell(0).setCellValue("热门套餐");

        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("新增会员数");
        row2.createCell(1).setCellValue(Double.parseDouble(map.get("todayNewMember").toString()));
        row2.createCell(2).setCellValue("总会员数");
        row2.createCell(3).setCellValue(Double.parseDouble(map.get("totalMember").toString()));

        XSSFRow row3 = sheet.createRow(2);
        row3.createCell(0).setCellValue("本周新增会员数");
        row3.createCell(1).setCellValue(Double.parseDouble(map.get("thisWeekNewMember").toString()));
        row3.createCell(2).setCellValue("本月新增会员数");
        row3.createCell(3).setCellValue(Double.parseDouble(map.get("thisMonthNewMember").toString()));

        XSSFRow row5 = sheet.createRow(4);
        row5.createCell(0).setCellValue("今日预约数");
        row5.createCell(1).setCellValue(Double.parseDouble(map.get("todayOrderNumber").toString()));
        row5.createCell(2).setCellValue("今日到诊数");
        row5.createCell(3).setCellValue(Double.parseDouble(map.get("todayVisitsNumber").toString()));

        XSSFRow row6 = sheet.createRow(5);
        row6.createCell(0).setCellValue("本周预约数");
        row6.createCell(1).setCellValue(Double.parseDouble(map.get("thisWeekOrderNumber").toString()));
        row6.createCell(2).setCellValue("本周到诊数");
        row6.createCell(3).setCellValue(Double.parseDouble(map.get("thisWeekVisitsNumber").toString()));

        XSSFRow row7 = sheet.createRow(6);
        row7.createCell(0).setCellValue("本月预约数");
        row7.createCell(1).setCellValue(Double.parseDouble(map.get("thisMonthOrderNumber").toString()));
        row7.createCell(2).setCellValue("本月到诊数");
        row7.createCell(3).setCellValue(Double.parseDouble(map.get("thisMonthVisitsNumber").toString()));


        XSSFRow row9 = sheet.createRow(8);
        row9.createCell(0).setCellValue("套餐名称");
        row9.createCell(1).setCellValue("预约数量");
        row9.createCell(2).setCellValue("占比");
        row9.createCell(3).setCellValue("备注");

        List<Map<String,Object>> list=(List<Map<String,Object>>) map.get("hotSetmeal");
        for(int i=9,j=0;i<list.size()+9;i++,j++){
            Map<String, Object> objectMap = list.get(j);
            XSSFRow rowi = sheet.createRow(i);
            rowi.createCell(0).setCellValue(objectMap.get("name").toString());
            rowi.createCell(1).setCellValue(Double.parseDouble(objectMap.get("setmeal_count").toString()));
            rowi.createCell(2).setCellValue(Double.parseDouble(objectMap.get("proportion").toString()));
            rowi.createCell(3).setCellValue("");
        }

        File file=new File("D:\\a\\demo.xlsx");
        try {
            workbook.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }


    @RequestMapping("/download2")
    public ResponseEntity<byte[]> download2(){
        try{
            File file=new File("D:\\a\\demo4.xlsx");
            FileInputStream inputStream=new FileInputStream(file);
            XSSFWorkbook workbook2=new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook2.getSheetAt(0);
            sheet.getRow(1).getCell(1).setCellValue(Double.parseDouble(memberService.todayNewMember().toString()));
            sheet.getRow(1).getCell(3).setCellValue(Double.parseDouble(memberService.totalMember().toString()));
            sheet.getRow(2).getCell(1).setCellValue(Double.parseDouble(memberService.thisWeekNewMember().toString()));
            sheet.getRow(2).getCell(3).setCellValue(Double.parseDouble(memberService.thisMonthNewMember().toString()));
            sheet.getRow(4).getCell(1).setCellValue(Double.parseDouble(orderService.todayOrderNumber().toString()));
            sheet.getRow(4).getCell(3).setCellValue(Double.parseDouble(orderService.todayVisitsNumber().toString()));
            sheet.getRow(5).getCell(1).setCellValue(Double.parseDouble(orderService.thisWeekOrderNumber().toString()));
            sheet.getRow(5).getCell(3).setCellValue(Double.parseDouble(orderService.thisWeekVisitsNumber().toString()));
            sheet.getRow(6).getCell(1).setCellValue(Double.parseDouble(orderService.thisMonthOrderNumber().toString()));
            sheet.getRow(6).getCell(3).setCellValue(Double.parseDouble(orderService.thisMonthVisitsNumber().toString()));

            List<Map<String, Object>> list = orderService.hotSetmeal();
            for(int i=9,j=0;i<list.size()+9;i++,j++){
                Map<String, Object> objectMap = list.get(j);
                XSSFRow rowi = sheet.getRow(i);
                rowi.getCell(0).setCellValue(objectMap.get("name").toString());
                rowi.getCell(1).setCellValue(Double.parseDouble(objectMap.get("setmeal_count").toString()));
                rowi.getCell(2).setCellValue(Double.parseDouble(objectMap.get("proportion").toString()));
                rowi.getCell(3).setCellValue("");
            }
            inputStream.close();

            FileOutputStream outputStream=new FileOutputStream(file);
            workbook2.write(outputStream);
            workbook2.close();


            return MyFileUtils.download("demo4.xlsx","D:\\a\\");
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

}

