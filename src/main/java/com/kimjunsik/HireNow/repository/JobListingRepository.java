package com.kimjunsik.HireNow.repository;

import com.kimjunsik.HireNow.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobListingRepository extends JpaRepository<JobListing,Long> {
    List<JobListing> findAllByOrderByJobListIdDesc();
}
