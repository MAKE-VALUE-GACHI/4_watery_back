package team.gachi.watery.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Comment("이메일")
    private String email;

    @Comment("비밀번호")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("소셜 로그인")
    private SocialType socialType = SocialType.NONE;

    @Comment("소셜 아이디")
    private String socialId;

    private String refreshToken;
    private String fcmToken;


    public static User of(SocialType socialType, String socialId, String fcmToken) {
        return User.builder()
                .socialType(socialType)
                .socialId(socialId)
                .build();
    }

    public void updateTokenInLogin(String refreshToken, String fcmToken) {
        this.refreshToken = refreshToken;
        this.fcmToken = fcmToken;
    }

    public enum SocialType {
        NONE, KAKAO, GOOGLE
    }


    public record Social(
            SocialType socialType,
            String socialId
    ) {
    }

}
