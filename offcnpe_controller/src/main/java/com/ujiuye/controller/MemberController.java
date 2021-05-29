package com.ujiuye.controller;


import com.ujiuye.pojo.Member;
import com.ujiuye.service.IMemberService;
import com.ujiuye.utils.util.*;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author encan
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
    private IMemberService iMemberService;

    @RequestMapping("/listPage")
    public PageResult listPage(@RequestBody MemberQueryPageBean queryPageBean) {
        try {
            return iMemberService.listPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageResult(0L, null);
        }
    }

    @RequestMapping("/save")
    public Result save(@RequestBody Member member) {
        try {
            boolean save = iMemberService.save(member);
            return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }
    }

    @RequestMapping("/remove")
    public Result remove(Integer id) {
        try {
            iMemberService.removeById(id);
            return new Result(true, MessageConstant.DELETE_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_MEMBER_FAIL);
        }
    }

    @RequestMapping("/getById")
    public Result getById(Integer id) {
        try {
            Member member = iMemberService.getById(id);
            if (member != null) {
                return new Result(true, MessageConstant.QUERY_MEMBER_SUCCESS, member);
            }
            return new Result(false, MessageConstant.QUERY_MEMBER_FAIL);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_MEMBER_FAIL);
        }
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Member member) {
        try {
            iMemberService.updateById(member);
            return new Result(true, MessageConstant.EDIT_MEMBER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_MEMBER_FAIL);
        }
    }


    @RequestMapping("/yearlist")
    public List<Object> yearlist() {
        try {
            List<Object> allYear = iMemberService.getAllYear();
            return allYear;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(MemberQueryPageBean queryPageBean){
        if(queryPageBean.getQueryString().equals("null")){
            queryPageBean.setQueryString("");
        }
        //查询到当前这个一页的数据
        PageResult pageResult = iMemberService.listPage(queryPageBean);
        //把数据写到一个excel表中
        File file = ListToExcel(pageResult.getRows(), queryPageBean.getCurrentPage());
        System.out.println(pageResult.getRows());
        //下载到客户端
        return MyFileUtils.download(file.getName(),"D:/upload/");
    }




    private File ListToExcel(List<Member> list,int pageNum){
        File file=new File("D:/upload/第"+pageNum+"页会员数据.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("第" + pageNum + "页会员数据");
        //设置表头
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("姓名");
        row.createCell(2).setCellValue("性别");
        row.createCell(3).setCellValue("身份证号码");
        row.createCell(4).setCellValue("电话号码");
        row.createCell(5).setCellValue("注册时间");
        row.createCell(6).setCellValue("邮箱");
        row.createCell(7).setCellValue("生日");
        //遍历数据
        for(int i=1;i<=list.size();i++){
            Member member = list.get(i-1);
            XSSFRow row2 = sheet.createRow(i);
            row2.createCell(0).setCellValue(member.getId());
            row2.createCell(1).setCellValue(member.getName());
            row2.createCell(2).setCellValue(member.getSex());
            row2.createCell(3).setCellValue(member.getIdcard());
            row2.createCell(4).setCellValue(member.getPhonenumber());
            row2.createCell(5).setCellValue(member.getRegtime().toString());
            row2.createCell(6).setCellValue(member.getEmail());
            row2.createCell(7).setCellValue(member.getBirthday().toString());
        }
        try {
            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    @RequestMapping("/memberEcharts")
    public Result memberEcharts(){
        try {
            Map<String,Object> map=iMemberService.memberEcharts();
            return new Result(true,MessageConstant.QUERY_MEMBER_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_MEMBER_FAIL);
        }
    }
}

