package com.kimjunsik.HireNow.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "jobListing")
@NoArgsConstructor
public class JobListing extends BaseEntity{
    @Id // pk 컬럼 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long jobListId;

    @Column( nullable = false) // not null
    private String companyName ;//회사명

    @Column( nullable = false) //
    private String recruitTitle ; //공고제목

    @Column( nullable = false) //
    private String companyAddress ; //회사주소

    @Column( nullable = false) //
    private String employmentType ;

    @Column( nullable = false) //
    private String wage;

    @Column
    private String deadline;

    @Column( nullable = true) //
    private String password ;// 비밀번호

    @Column(nullable = false) // not null
    private String companyInfo; //회사정보

    @Column(nullable = false) // not null
    private String question1; //1번문항

    @Column(nullable = false) // not null
    private String question2; //2번문항

    @Column(nullable = false) // not null
    private String question3; //3번문항

    @OneToMany(mappedBy = "jobListing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Application> applications;

    @OneToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private LocationInfo locationInfo;



    @Column(nullable = false) // not null
    private String companyLogoPath; //3번문항


    @Builder

    public JobListing(Long jobListId, String companyName, String recruitTitle, String companyAddress, String employmentType, String wage, String deadline, String password, String companyInfo, String question1, String question2, String question3, List<Application> applications, LocationInfo locationInfo, String companyLogoPath) {
        this.jobListId = jobListId;
        this.companyName = companyName;
        this.recruitTitle = recruitTitle;
        this.companyAddress = companyAddress;
        this.employmentType = employmentType;
        this.wage = wage;
        this.deadline = deadline;
        this.password = password;
        this.companyInfo = companyInfo;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.applications = applications;
        this.locationInfo = locationInfo;
        this.companyLogoPath = companyLogoPath;
    }
}
