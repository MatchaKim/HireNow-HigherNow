package com.kimjunsik.HireNow.service;

import com.kimjunsik.HireNow.dto.ApplyRequest;
import com.kimjunsik.HireNow.dto.ApplyResponse;
import com.kimjunsik.HireNow.entity.Application;
import com.kimjunsik.HireNow.entity.JobListing;
import com.kimjunsik.HireNow.repository.ApplicationRepository;
import com.kimjunsik.HireNow.repository.JobListingRepository;
import com.kimjunsik.HireNow.timeformatting.TimeFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final JobListingRepository jobListingRepository;


    public ResponseEntity<String> saveApplication(ApplyRequest request){
        try {
            Optional<JobListing> optionalJobListing = jobListingRepository.findById(request.getJobListId());

            if (!optionalJobListing.isPresent()) {
                // JobListing을 찾지 못한 경우의 처리
                return ResponseEntity.badRequest().body("JobListing not found");
            }

            JobListing jobListing = optionalJobListing.get();
            String deadLine = jobListing.getDeadline();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime deadLineTime;

            try {
                deadLineTime = LocalDateTime.parse(deadLine, formatter);
            } catch (DateTimeParseException e) {
                // 올바르지 않은 날짜/시간 형식의 문자열을 파싱하려고 했을 때의 처리
                return ResponseEntity.badRequest().body("Invalid date format");
            }

            LocalDateTime now = LocalDateTime.now();

            if (now.isBefore(deadLineTime)) {
                Application application = Application.builder()
                        .name(request.getName())
                        .age(request.getAge())
                        .gender(request.getGender())
                        .answer1(request.getAnswer1())
                        .answer2(request.getAnswer2())
                        .answer3(request.getAnswer3())
                        .jobListing(jobListing)
                        .build();

                applicationRepository.save(application);
                return ResponseEntity.ok("Application saved successfully");
            } else {
                return ResponseEntity.badRequest().body("Current time is after the deadline");
            }

        } catch (Exception e) {
            // 그 외 예외를 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

    public ApplyResponse getApplicationDetail(Long jobListId ,
                                   Long applicationId,
                                   String password) {


        JobListing jobListing = jobListingRepository.findById(jobListId).orElse(null);
        if (jobListing != null) {
            String savedPassword = jobListing.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(password, savedPassword)) {
                Optional<Application> optionalApplication = applicationRepository
                        .findByAppicationIdAndJobListId(jobListId, applicationId);

                Application application =
                        optionalApplication.get();
                String formattedDate = TimeFormatter.formattingTime(application.getCreatedTime());

                ApplyResponse applyResponse = ApplyResponse.builder()
                        .jobListId(application.getJobListing().getJobListId())
                        .applicationId(application.getAppicationId())
                        .name(application.getName())
                        .age(application.getAge())
                        .gender(application.getGender())
                        .answer1(application.getAnswer1())
                        .answer2(application.getAnswer2())
                        .answer3(application.getAnswer3())
                        .createdTime(formattedDate)
                        .build();

                return applyResponse;
            }
        }
        return null;
    }

    public List<ApplyResponse> getApplicationList(Long jobListId, String password) {
        JobListing jobListing = jobListingRepository.findById(jobListId).orElse(null);

        if (jobListing != null) {
            String savedPassword = jobListing.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if (passwordEncoder.matches(password, savedPassword)) {
                List<Application> applicationList = applicationRepository.findAllByJobListId(jobListId);
                List<ApplyResponse> applyResponseList = new ArrayList<>();

                for (Application application : applicationList) {
                    String formattedDate = TimeFormatter.formattingTime(application.getCreatedTime());

                    ApplyResponse applyResponse = ApplyResponse.builder()
                            .applicationId(application.getAppicationId())
                            .name(application.getName())
                            .createdTime(formattedDate)
                            .build();

                    applyResponseList.add(applyResponse);
                }
                return applyResponseList;
            }
        }

        return null;
    }



}
