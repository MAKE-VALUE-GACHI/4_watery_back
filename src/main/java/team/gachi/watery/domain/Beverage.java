package team.gachi.watery.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;
import team.gachi.watery.domain.enums.BeverageCategory;

@Entity
@Table(name = "beverages")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Beverage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private CafeBrand brand;

    @Column(nullable = false)
    @Comment("음료 이름")
    private String name;

    @Comment("사이즈")
    private String size; // 예: Tall, Grande, Regular — optional

    @Column(nullable = false)
    @Comment("카페인 량 mg")
    private float caffeineMg;

    @Column(nullable = false)
    @Comment("음료 량 ml")
    private float volumeMl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Comment("음료 카테고리")
    private BeverageCategory category;

}