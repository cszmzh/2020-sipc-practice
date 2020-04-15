package com.sipc.catalina.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "commentId")
    private Integer comment_id; //评论编号
    @Column(name = "commentName")
    private String comment_name; //评论标题
    @Column(name = "commentContext")
    private String comment_context; //评论内容
    @Column(name = "commentTime")
    private Date comment_time;   //评论时间

}
