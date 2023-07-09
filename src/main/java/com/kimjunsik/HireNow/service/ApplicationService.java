package com.kimjunsik.HireNow.service;

import com.kimjunsik.HireNow.dto.ApplyRequest;
import com.kimjunsik.HireNow.entity.Application;
import com.kimjunsik.HireNow.repository.ApplicationRepository;
import com.kimjunsik.HireNow.repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final JobListingRepository jobListingRepository;


    public void saveApplication(ApplyRequest request){

        Application application = Application.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .answer1(request.getAnswer1())
                .answer2(request.getAnswer2())
                .answer3(request.getAnswer3())
                .jobListing(jobListingRepository.findById(request.getJobListId()).get())
                .build();

        applicationRepository.save(application);

    }



}
