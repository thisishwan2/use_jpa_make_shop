package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String naem;

    //카테고리와 아이템은 다대다관계이다. 따라서 @ManyToMany
    @ManyToMany
    //다대다는 중간테이블이 있어야한다. 고로 중간 테이블을 맵핑해줘야 한다.@JoinTable을 통해
    //실무에서는 거의 못쓴다. 추가나 이런부분이 안되기 때문. 예제니까 해보는것.
    //정리하자면, @JoinTable을 통해 중간 테이블을 맵필해주고,
    //그 안에 joinColumn으로 category_id와 item_id를 맵필해주는 형태이다.
    //왜냐면, category_itme 테이블에는 테이블 명만 들어있기 때문.
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();


    //이부분 다시 생각해보자.
    //카테고리 구조는 계층구조로 된다. 부모 자식을 확인할 수 있어야함.
    //부모는 하나의 카테고리만 가짐
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    //자식은 여러 카테고리를 가질 수 있음
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //==연관관계 메서드==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
