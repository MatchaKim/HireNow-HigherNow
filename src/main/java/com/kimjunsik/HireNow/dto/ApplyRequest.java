package com.kimjunsik.HireNow.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kimjunsik.HireNow.entity.JobListing;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ApplyRequest {

    private String name ; //지원자 이름

    private Character gender; //지원자 성별 m or w

    private Integer age; // 지원자 나이

    private String answer1; // 1번문항 답변

    private String answer2; // 2번문항 답변

    private String answer3; // 3번문항 답변

    private Long jobListId; //지원하고자 하는 항목

}
