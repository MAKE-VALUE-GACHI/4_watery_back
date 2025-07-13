package team.gachi.watery.alarm.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.user.domain.User;

@Entity
@Table(name = "alarm")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Comment("on/off 설정")
    private boolean enabled;

    public static Alarm of(User user) {
        return Alarm.builder()
                .user(user)
                .enabled(true)
                .build();
    }
}
