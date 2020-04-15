package com.sipc.catalina.repository;

import com.sipc.catalina.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(nativeQuery = true, value = "select count(*) from comment")
    public long getCount();

    @Query("select o from Comment o where o.comment_id = 2")
    public Comment findIdTwo();

}
