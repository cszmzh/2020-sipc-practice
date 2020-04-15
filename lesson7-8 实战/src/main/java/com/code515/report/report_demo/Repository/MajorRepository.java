package com.code515.report.report_demo.Repository;

import com.code515.report.report_demo.Entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorRepository extends JpaRepository<Major, Integer> {

    Major findByMajorName(String name);

}
