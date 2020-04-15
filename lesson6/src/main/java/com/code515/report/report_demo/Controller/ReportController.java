package com.code515.report.report_demo.Controller;

import com.code515.report.report_demo.Service.ReportService;
import com.code515.report.report_demo.VO.ResultVO;
import com.code515.report.report_demo.VO.reportVO.ReportDataVO;
import com.code515.report.report_demo.VO.reportVO.ReportInfoVO;
import com.code515.report.report_demo.VO.reportVO.ReportViewListVO;
import com.code515.report.report_demo.entity.view.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getAll")
    public ResultVO getAll(@RequestParam("page")Integer page, @RequestParam("pageSize")Integer pageSize){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //2020-04-12 21:40

        Page<ReportView> reportViewPage = reportService.getAll(page, pageSize);
        List<ReportView> reportViewList = reportViewPage.getContent();

        //封装ResultVO
        //第一层
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("suceess");

        //第二层
        ReportDataVO reportDataVO = new ReportDataVO();
        reportDataVO.setReportTotal((int) reportViewPage.getTotalElements());

        //第三层
        ReportViewListVO reportViewListVO = new ReportViewListVO();
        List<ReportViewListVO> reportViewListVOList = new ArrayList();
        for(ReportView report: reportViewList){
            reportViewListVO.setStudentId(report.getStuId());
            reportViewListVO.setStudentName(report.getStuName());
            reportViewListVO.setMajorName(report.getMajorName());
            reportViewListVO.setClassNumber(report.getStuClass());
            reportViewListVO.setStudentQQ(report.getStuQq());
            reportViewListVO.setStudentPhone(report.getStuPhone());

            ReportInfoVO firstWill = new ReportInfoVO();
            ReportInfoVO secondWill = new ReportInfoVO();

            //填充第一志愿信息 第四层
            firstWill.setOrganization(report.getOrgFirst());
            firstWill.setBranch(report.getBraFirst());
            firstWill.setReason(report.getReasonFirst());
            //填充第二志愿信息
            secondWill.setOrganization(report.getOrgSecond());
            secondWill.setBranch(report.getBraSecond());
            secondWill.setReason(report.getReasonSecond());

            reportViewListVO.setFirstWill(firstWill);
            reportViewListVO.setSecondWill(secondWill);

            reportViewListVO.setIsDispensing(report.getIsDispensing());
            reportViewListVO.setIsEnroll(report.getStuStatus());
            reportViewListVO.setUpdateTime(sdf.format(report.getUpdateTime()));
            reportViewListVO.setCreateTime(sdf.format(report.getCreateTime()));

            reportViewListVOList.add(reportViewListVO); //注意这里
        }

        //封装数据
        reportDataVO.setReportViewListVO(reportViewListVOList);
        resultVO.setData(reportDataVO);

        return resultVO;
    }

}
