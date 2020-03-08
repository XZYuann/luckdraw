package com.example.demo.controller;


import com.example.demo.sevice.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {
    @Autowired
    private ITestService testService;
    @RequestMapping("/index")
    public String index(){

        return "index";
    }
    @RequestMapping("/import")
    @ResponseBody
    public String addUser(@RequestParam(value="file") MultipartFile file) {
        boolean a = false;
        int result = 0;
        String fileName = file.getOriginalFilename();
        try {
            result = testService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result > 0){
            return "excel文件数据导入成功！";
        }else{
            return "excel数据导入失败！";
        }
    }


}
