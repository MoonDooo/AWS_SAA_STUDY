package com.aws.saa_c03.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int id;

    @Column(columnDefinition = "TEXT")
    private String english;

    @Column(columnDefinition = "TEXT")
    private String korean;

    @OneToMany(orphanRemoval = true, mappedBy = "question")
    private List<View> view = new ArrayList<>();

    @OneToOne(mappedBy = "question")
    private Memo memo;

    @Builder
    public Question(String english, String korean, List<View> view) {
        this.korean = korean;
        this.english = english;
        this.view = view;
    }

    public void updateQuestion(String english, String korean){
        this.korean = korean;
        this.english = english;
    }

}
