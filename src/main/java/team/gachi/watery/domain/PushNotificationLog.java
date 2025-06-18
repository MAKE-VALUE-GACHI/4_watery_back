package team.gachi.watery.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "push_notification_logs")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PushNotificationLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Comment("전송 시간")
    private LocalDateTime sentAt;

    @Column(nullable = false)
    @Comment("전송 메시지")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("전송 상태")
    private PushStatus status;

    @Comment("에러 메시지")
    private String errorMessage;

    public enum PushStatus {
        SENT, FAILED, CLICKED, IGNORED
    }
}