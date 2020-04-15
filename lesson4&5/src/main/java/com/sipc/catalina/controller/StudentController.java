package com.sipc.catalina.controller;

import com.sipc.catalina.entity.Student;
import com.sipc.catalina.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository repository;

    /**
     * 1.获取学生所有信息
     */
    @GetMapping("/students")
    public List<Student> getAll(){
        return repository.findAll();
    }

    /**
     * 2.创建一条学生信息
     * @return
     */
    @PostMapping("/students")
    public Student create(@RequestParam("name")String name, @RequestParam("sex")String sex, @RequestParam("fee")BigDecimal fee){

        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setFee(fee);

        return repository.save(student);
    }

    /**
     * 3.通过id查找一个学生
     */
    @GetMapping("/students/{id}")
    public Student findById(@PathVariable("id")Integer id){
        return repository.findById(id).orElse(null);
    }

    /**
     * 4.通过id更新学生信息
     */
    @PutMapping("/students/{id}")
    public Student update(@PathVariable("id")Integer id, @RequestParam(value = "name", required = false)String name,
                          @RequestParam(value = "sex", required = false)String sex,@RequestParam(value = "fee", required = false)BigDecimal fee){
        Optional<Student> optional = repository.findById(id);
        Student student;
        if(optional.isPresent()){
            student = optional.get();
        }else{
            return null;
        }
        if(name!=null){
            student.setName(name);
        }
        if(sex != null){
            student.setSex(sex);
        }
        if(fee != null){
            student.setFee(fee);
        }
        return repository.save(student);
    }
}
