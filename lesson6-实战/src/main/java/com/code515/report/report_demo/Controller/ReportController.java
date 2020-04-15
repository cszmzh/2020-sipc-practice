package com.code515.report.report_demo.Controller;

import com.code515.report.report_demo.Service.MajorService;
import com.code515.report.report_demo.Service.OrganizationService;
import com.code515.report.report_demo.Service.ReportService;
import com.code515.report.report_demo.Service.StudentService;
import com.code515.report.report_demo.VO.ResultVO;
import com.code515.report.report_demo.VO.reportVO.ReportDataVO;
import com.code515.report.report_demo.VO.reportVO.ReportInfoVO;
import com.code515.report.report_demo.VO.reportVO.ReportViewListVO;
import com.code515.report.report_demo.Entity.Report;
import com.code515.report.report_demo.Entity.Student;
import com.code515.report.report_demo.Entity.view.ReportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public ResultVO getAll_ADMIN(@RequestParam("page")Integer page, @RequestParam("pageSize")Integer pageSize){

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

    @PostMapping("/insert")
    @Transactional
    public ResultVO insert(@RequestParam("stdId")String studentId, @RequestParam("stdName")String studentName, @RequestParam("major")String studentMajor,
                           @RequestParam("classNum")String classNumber,@RequestParam("stdQQ")String studentQQ, @RequestParam("stdPhone") String studentPhone,
                           @RequestParam("firstOrg")String firstOrg, @RequestParam("firstBra")String firstBra, @RequestParam("firstReason")String firstReason,
                           String secondOrg, String secondBra, String secondReason,@RequestParam("isDispensing")Boolean disPensing){

        Student student = new Student();
        Report report = new Report();

        student.setStuId(new Integer(studentId));
        student.setStuName(studentName);
        student.setStuMajor(majorService.findByMajorName(studentMajor).getMajorId());
        student.setStuClass(new Integer(classNumber));
        student.setStuQq(studentQQ);
        student.setStuPhone(studentPhone);
        student.setStuStatus("未录取");

        //保存学生信息
        studentService.save(student);

        report.setStudentId(new Integer(studentId));
        report.setVolFirst(organizationService.findIdByOrgNameAndBraName(firstOrg,firstBra));
        if(secondOrg!=null && secondBra!=null){
            report.setVolSecond(organizationService.findIdByOrgNameAndBraName(secondOrg, secondBra));
            report.setReasonSecond(secondReason);
        }
        report.setReasonFirst(firstReason);
        report.setDispensing(disPensing);

        //保存志愿信息
        reportService.save(report);

        ResultVO resultVO = new ResultVO(0, "success");

        return resultVO;
    }

}
