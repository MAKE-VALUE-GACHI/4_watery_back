package team.gachi.watery.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;
import team.gachi.watery.domain.enums.BeverageCategory;

import java.time.LocalDateTime;

@Entity
@Table(name = "beverage_intakes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BeverageIntake extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beverage_id")
    private Beverage beverage;

    @Column(nullable = false)
    @Comment("섭취량 ml")
    private float amountMl;

    @Column(nullable = false)
    @Comment("섭취 시간")
    private LocalDateTime intakeTime;

    private String customName;

    @Enumerated(EnumType.STRING)
    private BeverageCategory category;

    /**
     * @return 등록된 음료가 아닌 수동 입력 음료인지 여부
     */
    public boolean isManualEntry() {
        return beverage == null;
    }

}
