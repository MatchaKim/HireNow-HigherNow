package com.kimjunsik.HireNow.repository;

import com.kimjunsik.HireNow.entity.JobListing;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    List<JobListing> findAllByOrderByJobListIdDesc();



    List<JobListing> findByOrderByApplicationsDesc();

    @Query("SELECT j FROM JobListing j WHERE j.companyName LIKE %:keyword% OR j.recruitTitle LIKE %:keyword%")
    List<JobListing> findByCompanyNameOrRecruitTitleContaining(String keyword);


}
