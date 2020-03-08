package com.example.demo.sevice;

import com.example.demo.dao.StudentMapper;
import com.example.demo.entity.Student;
import com.example.demo.transcation.MyException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl  implements ITestService{
    @Autowired(required=false)
    private StudentMapper StudentMapper;
    @Override
    public Integer batchImport(String fileName, MultipartFile file) throws Exception {

        boolean notNull = false;
        List<Student> userList = new ArrayList<Student>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        Student Student ;

        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            Student = new Student();

            if( row.getCell(0).getCellType() !=1){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名请设为文本格式)");
            }
            String name = row.getCell(0).getStringCellValue();

            if(name == null || name.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String studentid = row.getCell(1).getStringCellValue();



            Student.setId(String.valueOf(r));
            Student.setName(name);
            Student.setStudentid(studentid);
            userList.add(Student);
            System.out.println(Student.getId());
            System.out.println(Student.getName());
            System.out.println(Student.getStudentid());
        }
        for (Student userResord : userList) {
            try {
                StudentMapper.insert(userResord);
            } catch (Exception e) {
                e.printStackTrace();
            }
                System.out.println(" 插入 "+userResord);
            }
            return 1;
        }

    }



