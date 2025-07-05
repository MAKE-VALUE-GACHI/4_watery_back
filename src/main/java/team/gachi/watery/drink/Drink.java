package team.gachi.watery.drink;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.common.Status;
import team.gachi.watery.user.User;

@Entity
@Table(name = "drink")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Drink extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_template_id")
    private ColorTemplate colorTemplate;

    @Column(nullable = false)
    @Comment("음료 이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

}
