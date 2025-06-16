package team.gachi.watery.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    private Social social = Social.NONE;

    public enum Social {
        NONE, KAKAO, GOOGLE;
    }
}
