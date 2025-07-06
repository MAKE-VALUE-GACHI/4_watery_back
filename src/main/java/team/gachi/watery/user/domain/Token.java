package team.gachi.watery.user.domain;

import jakarta.persistence.*;
import lombok.*;
import team.gachi.watery.common.BaseEntity;

@Entity
@Table(name = "token")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class Token extends BaseEntity {
    @Id
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String pushToken;
    private String refreshToken;

    public void update(String refreshToken, String fcmToken) {
        this.refreshToken = refreshToken;
        this.pushToken = fcmToken;
    }

    public static Token of(User user, String refreshToken, String fcmToken) {
        return Token.builder()
                .user(user)
                .refreshToken(refreshToken)
                .pushToken(fcmToken)
                .build();
    }
}