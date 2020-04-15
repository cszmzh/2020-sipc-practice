package com.code515.report.report_demo.Repository;

import com.code515.report.report_demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {



}
