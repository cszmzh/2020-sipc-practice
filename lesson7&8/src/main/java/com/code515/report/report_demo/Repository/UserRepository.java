package com.code515.report.report_demo.Repository;

import com.code515.report.report_demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String username);

}
