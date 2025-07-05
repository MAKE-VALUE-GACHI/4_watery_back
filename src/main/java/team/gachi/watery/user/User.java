package team.gachi.watery.user;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.common.Status;

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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("소셜 타입")
    private SocialType socialType = SocialType.NONE;

    @Column(nullable = false)
    @Comment("소셜 아이디")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("성별")
    private Gender gender;

    @Comment("출생연도")
    private int yearOfBirth;

    @Comment("체중 kg")
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("활동량")
    private ActivityLevel activityLevel;

    @Comment("일일 목표량")
    private int dailyGoal;

    @Comment("상태")
    private Status status = Status.ACTIVE;

    public static User of(SocialType socialType, String socialId) {
        return User.builder()
                .socialType(socialType)
                .socialId(socialId)
                .build();
    }

    public String getRefreshToken() {
        // TODO 수정예정
        return null;
    }

    public void updateTokenInLogin(String refreshToken, String fcmToken) {
        // TODO 수정예정

    }

    public enum SocialType {
        NONE, KAKAO, GOOGLE
    }

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum ActivityLevel {
        LOW, NORMAL, ACTIVE, VERY_ACTIVE
    }


    public record Social(
            SocialType socialType,
            String socialId
    ) {
    }
}
