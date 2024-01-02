package com.jungle.jungleweb.domain.entity;

import com.jungle.jungleweb.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name="BOARD_TBL")
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BID")
    private int bid;

    @Column(name = "UID")
    private int uid;

    @Column(name = "CONTENTS")
    private String contents;

    @Column(name ="TITLE")
    private String title;

    @Column(name = "USE_YN")
    private char useYn;

    @Builder
    public Board(int uid, String title, String contents, char useYn) {
        this.uid = uid;
        this.title = title;
        this.contents = contents;
        this.useYn = useYn;
    }
}
