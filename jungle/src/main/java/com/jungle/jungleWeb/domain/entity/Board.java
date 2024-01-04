package com.jungle.jungleweb.domain.entity;

import com.jungle.jungleweb.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
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

    @Column(name = "CONTENTS")
    private String contents;

    @Column(name ="TITLE")
    private String title;

    @Column(name = "USE_YN")
    private char useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID")
    private User user;
}
