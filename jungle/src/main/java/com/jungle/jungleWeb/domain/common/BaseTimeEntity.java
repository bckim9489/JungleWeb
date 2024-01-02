package com.jungle.jungleweb.domain.common;

import lombok.Getter;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "FIRST_DT", updatable = false, nullable = false)
    private LocalDateTime firstDt;   // 생성일시

    @Column(name = "LAST_DT", nullable = false)
    private LocalDateTime lastDt;  // 최종 수정일시

    @PrePersist
    public void prePersist() {
        this.firstDt = LocalDateTime.now();
        this.lastDt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastDt = LocalDateTime.now();
    }
}