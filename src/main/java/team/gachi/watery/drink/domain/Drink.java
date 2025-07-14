package team.gachi.watery.drink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.common.BaseEntity;
import team.gachi.watery.common.Status;
import team.gachi.watery.user.domain.User;

@Entity
@Table(name = "drink")
@Builder(access = AccessLevel.PRIVATE)
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

    @Column(nullable = false)
    @Comment("일일 수분 섭취 목표 포함 여부")
    private Boolean includesDailyHydrationGoal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public static Drink createDrinkOfUser(User user, String name, ColorTemplate colorTemplate,
                                          boolean includesDailyHydrationGoal) {
        return Drink.builder()
                .user(user)
                .name(name)
                .colorTemplate(colorTemplate)
                .includesDailyHydrationGoal(includesDailyHydrationGoal)
                .status(Status.ACTIVE)
                .build();
    }

    public boolean isMyDrink(Long userId) {
        return this.user.getId().equals(userId);
    }

    public void update(String name, ColorTemplate colorTemplate, Boolean includesDailyHydrationGoal) {
        this.name = name;
        this.colorTemplate = colorTemplate;
        this.includesDailyHydrationGoal = includesDailyHydrationGoal;
    }

    public void delete() {
        this.status = Status.DELETED;
    }
}
