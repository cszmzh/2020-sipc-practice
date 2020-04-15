package com.code515.report.report_demo.Repository;

import com.code515.report.report_demo.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {

}
