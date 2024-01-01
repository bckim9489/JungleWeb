package com.jungle.jungleweb.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name="BOARD_TBL")
public class Board {
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

    @Column(name ="FIRST_DT")
    private LocalDateTime firstDt;

    @Column(name ="LAST_DT")
    private LocalDateTime lastDt;
}
