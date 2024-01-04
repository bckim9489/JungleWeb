package com.jungle.jungleweb.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUserDTO {
    private int bid;
    private int uid;
    private String title;
    private String contents;
    private char useYn;
    private String userId;
}
