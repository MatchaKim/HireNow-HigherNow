package com.kimjunsik.HireNow.dto;

import com.kimjunsik.HireNow.entity.JobListing;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

@Getter
@Setter
public class JobListRequest {

    private String companyName ;//회사명

    private String recruitTitle ; //공고제목

    private String companyAddress ; //회사주소

    private String employmentType ;

    private String wage;

    private String deadline;

    private String latitude;

    private String longitude;

    private String companyInfo; // 회사정보

    private String question1; // 1번문항

    private String question2; // 2번문항

    private String question3; // 3번문항

    private String password; // 비밀번호

    private MultipartFile file;
}
