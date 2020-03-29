package com.sipc.catalina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//此处有错误，请看完什么是Spring Data文章后仔细查找
@Repository
public interface StudentRepository extends JpaRepository {

}
