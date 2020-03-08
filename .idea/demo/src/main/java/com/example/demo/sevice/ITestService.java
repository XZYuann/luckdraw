package com.example.demo.sevice;

import org.springframework.web.multipart.MultipartFile;

public interface ITestService {
    Integer batchImport(String fileName, MultipartFile file) throws Exception;

}
