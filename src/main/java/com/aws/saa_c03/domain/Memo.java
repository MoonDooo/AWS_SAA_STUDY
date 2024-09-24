package com.aws.saa_c03.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo {
    @Id
    @Column(name = "question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String memo;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "question_id")
    private Question question;

    public Memo(String memo, Question question) {
        this.memo = memo;
        this.question = question;
    }
    public void updateMemo(String memo) {
        this.memo = memo;
    }
}
