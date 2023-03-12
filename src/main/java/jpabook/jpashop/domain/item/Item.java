package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//상속관계 전략을 부모 클래스에 잡아줘야한다. 싱글테이블 전략을 이용
//싱글테이블은 한 테이블에 다 때려박는것.
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//하위 클래스를 구분하는 용도의 컬럼이다
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    //왜 item_id에 대한 거울이 없나 싶은데 내 생각엔 단방향이라 그런거 같다.
    //실제 인프런 질문 답변을 보면, 단방향의 경우 필요 없고, 필요시 양방향으로 변경(@OneToMany)하면 된다.


    //다대다 관계에서는 연관관계 주인을 아무데나 잡아도 된다.
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //데이터를 가지고 있는 쪽에 비즈니스 로직이 있는게 좋다.
    //==비즈니스 로직==//(재고가 늘고 줄고)

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity+=quantity;
    }

    /**
     * stock 감소 0보다 줄어들면 안되는 로직이 포함되야함.
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity=restStock;
    }

}
