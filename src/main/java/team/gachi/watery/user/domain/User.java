package team.gachi.watery.user.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.alarm.domain.Alarm;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.common.Status;

import java.util.List;

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
    @Builder.Default
    private SocialType socialType = SocialType.NONE;

    @Column(nullable = false)
    @Comment("소셜 아이디")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("성별")
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Comment("출생연도")
    private int yearOfBirth;

    @Comment("체중 kg")
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("활동량")
    @Builder.Default
    private ActivityLevel activityLevel = ActivityLevel.NONE;

    @Comment("일일 수분 섭취 목표량")
    private int dailyHydrationGoal;

    @Comment("상태")
    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Token token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarms;

    public static User of(SocialType socialType, String socialId) {
        return User.builder()
                .socialType(socialType)
                .socialId(socialId)
                .build();
    }


    public void setToken(Token token) {
        this.token = token;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public String getRefreshToken() {
        return this.token == null ? null : this.token.getRefreshToken();
    }

    public void updateProfile(Gender gender, ActivityLevel activityLevel, int weight, int yearOfBirth) {
        this.gender = gender;
        this.activityLevel = activityLevel;
        this.weight = weight;
        this.yearOfBirth = yearOfBirth;
    }

    public void updateDailyHydrationGoal(int dailyHydrationGoal) {
        this.dailyHydrationGoal = dailyHydrationGoal;
    }

    public enum SocialType {
        NONE, KAKAO, GOOGLE
    }

    public enum Gender {
        NONE, MALE, FEMALE, OTHER
    }

    public enum ActivityLevel {
        NONE, LOW, NORMAL, ACTIVE, VERY_ACTIVE
    }


    public record Social(
            SocialType socialType,
            String socialId
    ) {
    }
}
