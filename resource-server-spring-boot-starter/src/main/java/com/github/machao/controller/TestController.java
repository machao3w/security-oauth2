package com.github.machao.controller;

import com.github.machao.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: mc
 * date: 2020/7/24 10:44
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user")
    public Student getStudent(@RequestParam(value = "number",required = false) String number){
        Student student = new Student();
        student.setName("mc");
        student.setSex("男");
        student.setNumber("02");
        return student;

    }
}
