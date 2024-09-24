package com.aws.saa_c03.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "view_id")
    private int id;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isCollect;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String english;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String korean;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Builder
    public View(Question question, String english, String korean, boolean isCollect) {
        this.question = question;
        this.isCollect = isCollect;
        this.english = english;
        this.korean = korean;
    }

    public void updateView(String english, String korean, boolean isCollect) {
        this.english = english;
        this.korean = korean;
        this.isCollect = isCollect;
    }
}
