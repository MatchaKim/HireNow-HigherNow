package com.kimjunsik.HireNow.controller;


import com.kimjunsik.HireNow.dto.ApplyRequest;
import com.kimjunsik.HireNow.dto.ApplyResponse;
import com.kimjunsik.HireNow.dto.JobListRequest;
import com.kimjunsik.HireNow.dto.Password;
import com.kimjunsik.HireNow.service.ApplicationService;
import com.kimjunsik.HireNow.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ApplyController {

    private final JobListingService jobListingService;
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<String> saveApply(@RequestBody ApplyRequest request) {

        return applicationService.saveApplication(request);

    }

    @PostMapping("/apply/{jobListId}/{applicationId}")
    public ApplyResponse getDetail(@PathVariable Long jobListId,
                                   @PathVariable Long applicationId,
                                   @RequestBody Password password) {

        ApplyResponse applyResponse = applicationService.getApplicationDetail(jobListId, applicationId,password.getPassword());


        return applyResponse;
    }

    @PostMapping("/apply/{jobListId}") //전체리스트 받아오기
    public List<ApplyResponse> getApplicationList(@PathVariable Long jobListId,
                                                  @RequestBody Password password) {

        List<ApplyResponse> applyResponse = applicationService.getApplicationList(jobListId,password.getPassword());


        return applyResponse;
    }
}
