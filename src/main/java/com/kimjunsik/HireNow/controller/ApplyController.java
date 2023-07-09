package com.kimjunsik.HireNow.controller;


import com.kimjunsik.HireNow.dto.ApplyRequest;
import com.kimjunsik.HireNow.dto.JobListRequest;
import com.kimjunsik.HireNow.service.ApplicationService;
import com.kimjunsik.HireNow.service.JobListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ApplyController {

    private final JobListingService jobListingService;
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public void saveRecruit(@RequestBody ApplyRequest request) {

        applicationService.saveApplication(request);

    }



}
