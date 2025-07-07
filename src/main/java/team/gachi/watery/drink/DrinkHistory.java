package team.gachi.watery.drink;

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
@Table(name = "drink_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DrinkHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Drink drink;

    @Column(nullable = false)
    @Comment("섭취량")
    private int amount;

    @Column(nullable = false)
    @Comment("섭취 시간")
    private LocalDateTime drinkAT;

    /**
     * @return 등록된 음료가 아닌 수동 입력 음료인지 여부
     */
    public boolean isTemplate() {
        return user == null;
    }
}
