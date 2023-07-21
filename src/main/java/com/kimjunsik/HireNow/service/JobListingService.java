package com.kimjunsik.HireNow.service;

import com.kimjunsik.HireNow.entity.LocationInfo;
import com.kimjunsik.HireNow.timeformatting.TimeFormatter;
import com.kimjunsik.HireNow.dto.JobListRequest;
import com.kimjunsik.HireNow.dto.JobListResponse;
import com.kimjunsik.HireNow.entity.JobListing;
import com.kimjunsik.HireNow.repository.ApplicationRepository;
import com.kimjunsik.HireNow.repository.JobListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobListingService {
    private final ApplicationRepository applicationRepository;
    private final JobListingRepository jobListingRepository;

    public ResponseEntity<String> saveRecruit(JobListRequest request) {
        if (request.getFile().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is Empty");
        }

        try {

            TimeFormatter timeFormatter = new TimeFormatter();
            // 저장할 경로와 파일명 설정
            String directory = "/root/companylogo";
            String fileName = timeFormatter.getFormattedTime() + request.getFile().getOriginalFilename();
            String filePath = directory + "/" + fileName;

            // 파일 저장
            File dest = new File(filePath);
            request.getFile().transferTo(dest);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String EncodedPW = passwordEncoder.encode(request.getPassword());

            double doubleTypeLongitude = Double.parseDouble(request.getLongitude());
            double doubleTypeLatitude = Double.parseDouble(request.getLatitude());
            LocationInfo doubleTypeLocationInfo = new LocationInfo();
            doubleTypeLocationInfo.setLongitude(doubleTypeLongitude);
            doubleTypeLocationInfo.setLatitude(doubleTypeLatitude);

            JobListing jobListing = JobListing.builder()
                    .companyName(request.getCompanyName())
                    .companyInfo(request.getCompanyInfo())
                    .question1(request.getQuestion1())
                    .question2(request.getQuestion2())
                    .question3(request.getQuestion3())
                    .companyLogoPath(filePath)
                    .password(EncodedPW)
                    .recruitTitle(request.getRecruitTitle())
                    .companyAddress(request.getCompanyAddress())
                    .deadline(request.getDeadline())
                    .wage(request.getWage())
                    .employmentType(request.getEmploymentType())
                    .locationInfo(doubleTypeLocationInfo)
                    .build();

            jobListingRepository.save(jobListing);

            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }


    }


    public JobListResponse getDetailJob(Long jobListId) {

        JobListing jobListing = jobListingRepository.findById(jobListId).get();
        String formattedDate = TimeFormatter.formattingTime(jobListing.getCreatedTime());

        JobListResponse jobListResponse =
                JobListResponse.builder()
                        .jobListId(jobListing.getJobListId())
                        .question1(jobListing.getQuestion1())
                        .question2(jobListing.getQuestion2())
                        .question3(jobListing.getQuestion3())
                        .createdTime(formattedDate)
                        .companyName(jobListing.getCompanyName())
                        .companyInfo(jobListing.getCompanyInfo())
                        .latitude(jobListing.getLocationInfo().getLatitude())
                        .longitude(jobListing.getLocationInfo().getLongitude())
                        .companyAddress(jobListing.getCompanyAddress())
                        .employmentType(jobListing.getEmploymentType())
                        .wage(jobListing.getWage())
                        .deadline(jobListing.getDeadline())
                        .recruitTitle(jobListing.getRecruitTitle())
                        .build();

        return jobListResponse;


    }


    public List<JobListResponse> getJobList() {
        List<JobListing> jobListingList = jobListingRepository.findAllByOrderByJobListIdDesc();
        List<JobListResponse> jobListResponseList = new ArrayList<>();

        for (JobListing jobListing : jobListingList) {

            String formattedDate = TimeFormatter.formattingTime(jobListing.getCreatedTime());


            JobListResponse jobListResponse = JobListResponse.builder()
                    .jobListId(jobListing.getJobListId())
                    .companyName(jobListing.getCompanyName())
                    .companyInfo(jobListing.getCompanyInfo())
                    .companyAddress(jobListing.getCompanyAddress())
                    .employmentType(jobListing.getEmploymentType())
                    .wage(jobListing.getWage())
                    .deadline(jobListing.getDeadline())
                    .recruitTitle(jobListing.getRecruitTitle())
                    .build();
            jobListResponseList.add(jobListResponse);
        }

        return jobListResponseList;
    }

    public List<JobListResponse> getHotJobList() {
        List<JobListing> jobListingList = jobListingRepository.findByOrderByApplicationsDesc();
        List<JobListResponse> jobListResponseList = new ArrayList<>();

        for (JobListing jobListing : jobListingList) {

            String formattedDate = TimeFormatter.formattingTime(jobListing.getCreatedTime());


            JobListResponse jobListResponse = JobListResponse.builder()
                    .jobListId(jobListing.getJobListId())
                    .companyName(jobListing.getCompanyName())
                    .companyInfo(jobListing.getCompanyInfo())
                    .companyAddress(jobListing.getCompanyAddress())
                    .employmentType(jobListing.getEmploymentType())
                    .wage(jobListing.getWage())
                    .deadline(jobListing.getDeadline())
                    .recruitTitle(jobListing.getRecruitTitle())
                    .build();
            jobListResponseList.add(jobListResponse);
        }

        return jobListResponseList;
    }


    public ResponseEntity<Resource> getLogo(Long jobListId) {
        try {
            Path imagePath = Paths.get(jobListingRepository.findById(jobListId).get().getCompanyLogoPath());
            String contentType = Files.probeContentType(imagePath);
            Resource imageResource = new UrlResource(imagePath.toUri());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, contentType)
                    .body(imageResource);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Could not determine file type.");
        }
    }


    public List<JobListResponse> getSearchJobList(String keyword) {
        List<JobListing> jobListings = jobListingRepository.findByCompanyNameOrRecruitTitleContaining(keyword);
        List<JobListResponse> jobListResponseList = new ArrayList<>();

        for (JobListing jobListing : jobListings) {

            String formattedDate = TimeFormatter.formattingTime(jobListing.getCreatedTime());


            JobListResponse jobListResponse = JobListResponse.builder()
                    .jobListId(jobListing.getJobListId())
                    .companyName(jobListing.getCompanyName())
                    .companyInfo(jobListing.getCompanyInfo())
                    .companyAddress(jobListing.getCompanyAddress())
                    .employmentType(jobListing.getEmploymentType())
                    .wage(jobListing.getWage())
                    .deadline(jobListing.getDeadline())
                    .recruitTitle(jobListing.getRecruitTitle())
                    .build();
            jobListResponseList.add(jobListResponse);
        }

        return jobListResponseList;
    }


}
