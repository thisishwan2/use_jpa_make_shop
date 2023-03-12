package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception{
        //jPA에서 기본적으로 값을 변경할때는 아래처럼 book을 가져오고
        Book book = em.find(Book.class, 1L);

        //TX 안에서 이렇게 이름을 바꾸고
        book.setName("ASdasf");

        //TX commit 이 되 jpa가 변경분에 대 찾아서 업데이트 쿼리를 자동으 생성해서 db에 반영한다.
        //이것이 바로 더티체킹==변경감지라고 한다.
        //이 메커니즘이 기본적으로 JPA Entity를 바꿀 수 있다.
        //즉, flush할때 더티체킹이 일어난다.

    }
}
