package com.jungle.jungleweb.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Data
@NoArgsConstructor( access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name="USER_TBL")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private int uid;

    @Column(name ="USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name ="USER_PW")
    private String userPw;

    @Column(name ="ROLE")
    private String role;

    @Column(name ="USER_NM")
    private String userNm;
    @Column(name ="AUTH_PROVIDER")
    private String authProvider;

    @Column(name ="LAST_DT")
    private LocalDate lastDt;

}
