package com.kimjunsik.HireNow.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "application")
@NoArgsConstructor
public class Application extends BaseEntity {

    @Id // pk 컬럼 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long appicationId;

    @ManyToOne(fetch = FetchType.EAGER) // Eager Loading 설정
    @JoinColumn(name="jobList")
    @JsonBackReference
    private JobListing jobListing;

    @Column( nullable = false)
    private String name ; //지원자 이름

    @Column( nullable = false)
    private Character gender; //지원자 성별 m or w

    @Column(nullable = false) // not null
    private Integer age; // 지원자 나이

    @Column(nullable = false) // not null
    private String answer1; // 1번문항 답변

    @Column(nullable = false) // not null
    private String answer2; // 2번문항 답변

    @Column(nullable = false) // not null
    private String answer3; // 3번문항 답변

    @Builder
    public Application(Long appicationId, JobListing jobListing, String name, Character gender, Integer age, String answer1, String answer2, String answer3) {
        this.appicationId = appicationId;
        this.jobListing = jobListing;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
    }
}
