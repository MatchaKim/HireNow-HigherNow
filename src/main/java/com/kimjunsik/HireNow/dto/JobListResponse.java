package com.kimjunsik.HireNow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kimjunsik.HireNow.entity.JobListing;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 JSON에 포함하지 않음
public class JobListResponse {

    private Long jobListId ;//회사명

    private String companyName ;//회사명

    private String recruitTitle ; //공고제목

    private String companyAddress ; //회사주소

    private String employmentType ;

    private String wage;

    private String deadline;

    private Double latitude;

    private Double longitude;

    private String companyInfo; // 회사정보

    private String question1; // 1번문항

    private String question2; // 2번문항

    private String question3; // 3번문항

    private String createdTime;





    @Builder
    public JobListResponse(Long jobListId, String companyName, String recruitTitle, String companyAddress, String employmentType, String wage, String deadline, Double latitude, Double longitude, String companyInfo, String question1, String question2, String question3, String createdTime) {
        this.jobListId = jobListId;
        this.companyName = companyName;
        this.recruitTitle = recruitTitle;
        this.companyAddress = companyAddress;
        this.employmentType = employmentType;
        this.wage = wage;
        this.deadline = deadline;
        this.latitude = latitude;
        this.longitude = longitude;
        this.companyInfo = companyInfo;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.createdTime = createdTime;
    }
}
