package com.code515.report.report_demo.Service;

import com.code515.report.report_demo.Entity.Report;
import com.code515.report.report_demo.Entity.view.ReportView;
import org.springframework.data.domain.Page;

public interface ReportService {

    /**
     * 1.查询当前所有志愿信息
     * @param page  //当前页数
     * @param pageSize  //一页显示多少条
     * @return Page<ReportView>
     */
    public Page<ReportView> getAll(Integer page, Integer pageSize);

    /**
     * 2.通过学号删除一条志愿信息
     */
    public void deleteByStdId(Integer studentId);

    /**
     * 3.保存一条志愿信息
     */
    public Report save(Report report);
}
