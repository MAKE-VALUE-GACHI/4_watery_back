package team.gachi.watery.domain.common;


import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Comment("최초 생성 시간")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Comment("마지막 수정 시간")
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
