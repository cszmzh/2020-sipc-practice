package com.code515.report.report_demo.Service.Impl;

import com.code515.report.report_demo.Repository.OrganizationRepository;
import com.code515.report.report_demo.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository repository;

    @Override
    public Integer findIdByOrgNameAndBraName(String orgName, String braName) {
        return repository.findByOrgNameAndBranchName(orgName, braName).getOrgId();
    }
}
