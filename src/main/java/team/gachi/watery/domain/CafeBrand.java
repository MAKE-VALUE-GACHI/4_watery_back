package team.gachi.watery.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import team.gachi.watery.domain.common.BaseEntity;

import java.util.List;

@Entity
@Table(name = "cafe_brands")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CafeBrand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Comment("브랜드 명")
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Beverage> beverages;

}