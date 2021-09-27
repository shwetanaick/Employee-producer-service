package com.benzassignment.employee.producer.service.controller;

import com.benzassignment.employee.producer.service.service.FileService;
import com.benzassignment.employee.producer.service.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;
    @Autowired
      RestTemplate restTemplate;

    @Value("${file.read.url}")
    public String url = "";

@PostMapping("/store")
    public Employee storeFile(@RequestHeader("FileType") String fileType, @RequestBody Employee employee){
        employee.setOperation("STORE");
        return fileService.sendFileToKafka(fileType, employee);
    }

    @PostMapping("/update")
    public Employee updateFile(@RequestHeader("FileType") String fileType, @RequestBody Employee employee){
        employee.setOperation("UPDATE");
        return fileService.sendFileToKafka(fileType, employee);
    }

    @GetMapping("/read/{id}")
    public Employee readFile(@PathVariable("id") String id ){

        Employee contents =  restTemplate.getForObject(url+id, Employee.class);
        contents.setOperation("READ");
        contents.setFileType("CSV");
        return contents;
    }

}
