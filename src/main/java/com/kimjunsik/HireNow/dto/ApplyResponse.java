package com.kimjunsik.HireNow.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 JSON에 포함하지 않음
public class ApplyResponse {

    private Long applicationId;

    private String name ; //지원자 이름

    private Character gender; //지원자 성별 m or w

    private Integer age; // 지원자 나이

    private String answer1; // 1번문항 답변

    private String answer2; // 2번문항 답변

    private String answer3; // 3번문항 답변

    private Long jobListId; //지원하고자 하는 항목

    private String createdTime;

    @Builder
    public ApplyResponse(Long applicationId, String name, Character gender, Integer age, String answer1, String answer2, String answer3, Long jobListId, String createdTime) {
        this.applicationId = applicationId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.jobListId = jobListId;
        this.createdTime = createdTime;
    }


}
