package com.kimjunsik.HireNow.controller;


import com.kimjunsik.HireNow.dto.Password;
import com.kimjunsik.HireNow.repository.JobListingRepository;
import com.kimjunsik.HireNow.timeformatting.TimeFormatter;
import com.kimjunsik.HireNow.dto.ApplyResponse;
import com.kimjunsik.HireNow.dto.JobListRequest;
import com.kimjunsik.HireNow.dto.JobListResponse;
import com.kimjunsik.HireNow.repository.ApplicationRepository;
import com.kimjunsik.HireNow.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    private final JobListingService jobListingService;
    private final ApplicationRepository applicationRepository;
    private final JobListingRepository jobListingRepository;


    @PostMapping("/recruit")
    public String saveRecruit(@ModelAttribute JobListRequest jobListRequest) {

        return jobListingService.saveRecruit(jobListRequest);

    }




    @GetMapping("/recruit/{jobListId}/{applicationId}") //전체리스트 받아오기
    public ApplyResponse getDetail(@PathVariable Long jobListId,
                                   @PathVariable Long applicationId) {

        ApplyResponse applyResponse = jobListingService.getDetail(jobListId, applicationId);


        return applyResponse;
    }




    @PostMapping("/apply/{jobListId}") //전체리스트 받아오기
    public List<ApplyResponse> getApplicationList(@PathVariable Long jobListId,
                                                  @RequestBody Password password) {

        List<ApplyResponse> applyResponse = jobListingService.getApplicationList(jobListId,password.getPassword());


        return applyResponse;
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