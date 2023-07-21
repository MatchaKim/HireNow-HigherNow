package com.kimjunsik.HireNow.controller;


import com.kimjunsik.HireNow.dto.JobListRequest;
import com.kimjunsik.HireNow.dto.JobListResponse;
import com.kimjunsik.HireNow.repository.ApplicationRepository;
import com.kimjunsik.HireNow.repository.JobListingRepository;
import com.kimjunsik.HireNow.service.ApplicationService;
import com.kimjunsik.HireNow.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    private final JobListingService jobListingService;
    private final ApplicationRepository applicationRepository;
    private final JobListingRepository jobListingRepository;


    @PostMapping("/recruit")

    public ResponseEntity<String> saveRecruit(@ModelAttribute JobListRequest jobListRequest) {

        return jobListingService.saveRecruit(jobListRequest);
    }

    @GetMapping("/recruit/hot")
    public List<JobListResponse> getHotList() {
        List<JobListResponse> jobListResponses = jobListingService.getHotJobList();
        return jobListResponses;


    }

    @GetMapping("/recruit/search")
    public List<JobListResponse> getSearchList(@RequestParam String keyword) {
        List<JobListResponse> jobListResponses = jobListingService.getSearchJobList(keyword);
        return jobListResponses;

    }


    @GetMapping("/recruit")
    public List<JobListResponse> getList() {
        List<JobListResponse> applyResponseList = jobListingService.getJobList();
        return applyResponseList;

    }

    @GetMapping("/recruit/{jobListId}")
    public JobListResponse getDetailJob(@PathVariable Long jobListId) {
        JobListResponse jobListResponse = jobListingService.getDetailJob(jobListId);
        return jobListResponse;

    }

    @GetMapping("/logo/{jobListId}")
    public ResponseEntity<Resource> serveImage(@PathVariable Long jobListId) throws MalformedURLException {

        return jobListingService.getLogo(jobListId);
    }


}