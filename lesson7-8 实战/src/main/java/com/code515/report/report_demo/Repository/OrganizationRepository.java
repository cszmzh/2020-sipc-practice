package com.code515.report.report_demo.Repository;

import com.code515.report.report_demo.Entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    Organization findByOrgNameAndBranchName(String orgName, String braName);

}
