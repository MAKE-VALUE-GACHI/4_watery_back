package team.gachi.watery.alarm;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.user.domain.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "alarm_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    @Comment("전송 메시지")
    private String message;

    @Column(nullable = false)
    @Comment("전송 시간")
    private LocalDateTime sentAt;

    @Comment("에러 메시지")
    private String errorMessage;

    @Comment("실패 사유")
    private String failureReason;

}
