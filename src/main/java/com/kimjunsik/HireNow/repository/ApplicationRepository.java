package com.kimjunsik.HireNow.repository;

import com.kimjunsik.HireNow.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application,Long> {


    @Query("SELECT a FROM Application a WHERE a.jobListing.jobListId = :jobListId and a.appicationId = :applicationId")
    Optional<Application> findByAppicationIdAndJobListId(@Param("jobListId") Long jobListId,
                                                         @Param("applicationId") Long applicationId);

    @Query("SELECT a FROM Application a WHERE a.jobListing.jobListId = :jobListId")
    List<Application> findAllByJobListId(@Param("jobListId") Long jobListId);
}
