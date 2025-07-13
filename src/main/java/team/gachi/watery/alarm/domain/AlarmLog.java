package team.gachi.watery.alarm.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarm_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class AlarmLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;

    @Column(nullable = false)
    @Comment("전송 타이틀")
    private String title;

    @Column(nullable = false)
    @Comment("전송 바디")
    private String body;

    @Column(nullable = false)
    @Comment("전송 시간")
    private LocalDateTime sentAt;

    @Comment("에러 메시지")
    private String errorMessage;

    @Comment("실패 사유")
    private String failureReason;

    public static AlarmLog of(User user, Alarm alarm, String title, String body, LocalDateTime sentAt, String errorMessage, String failureReason) {
        return AlarmLog.builder()
                .user(user)
                .alarm(alarm)
                .title(title)
                .body(body)
                .sentAt(sentAt)
                .errorMessage(errorMessage)
                .failureReason(failureReason)
                .build();
    }
}
