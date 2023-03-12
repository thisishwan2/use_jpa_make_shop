package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item){
        //item은 jpa저장하기 전까지는 id 값이 없다.
        //id 값이 없다는 것은 새로 생성하는 객체라는 것.
        //save를 호출하면 null일 것이고 em.persist로 새롭게 등록하는 것.
        if (item.getId()==null){
            em.persist(item);
        }else {
            //이것은 update라고 생각하면 된다.
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
