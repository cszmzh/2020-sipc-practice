package com.sipc.catalina.controller;

import com.sipc.catalina.entity.Comment;
import com.sipc.catalina.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/getAll")
    public List<Comment> getAll(){
        return commentRepository.findAll();
    }

    @GetMapping("/comment/getCount")
    public long getCount(){
        return commentRepository.getCount();
    }

    @GetMapping("/comment/findIdTwo")
    public Comment findIdTwo(){
        return commentRepository.findIdTwo();
    }
}
