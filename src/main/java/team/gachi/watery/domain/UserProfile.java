package team.gachi.watery.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;

import java.time.LocalTime;

@Entity
@Table(name = "user_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("성별")
    private Gender gender;

    @Column(nullable = false)
    @Comment("나이")
    private int age;

    @Column(nullable = false)
    @Comment("체중 kg")
    private float weight;

    @Column(nullable = false)
    @Comment("키 cm")
    private float height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("활동량")
    private ActivityLevel activityLevel;

    @Column(nullable = false)
    @Comment("기상시간")
    private LocalTime wakeUpTime;

    @Column(nullable = false)
    @Comment("취침시간")
    private LocalTime sleepTime;

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum ActivityLevel {
        LOW, MEDIUM, HIGH
    }
}