package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Repository.ReportViewRepository;
import com.code515.report.report_demo.Service.ReportService;
import com.code515.report.report_demo.entity.view.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportViewRepository repository;

    @Override
    public Page<ReportView> getAll(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<ReportView> page = repository.findAll(pageable);
        return page;
    }
}
