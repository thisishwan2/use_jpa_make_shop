package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional // 롤백하기 때문에 insert 쿼리가 로그에 안보임.
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long savedId = memberService.join(member);
        //then
      //em.flush() 하면 insert문 확인 가능(em 주입 된 상태에서)
        assertEquals(member, memberRepository.findOne(savedId));
    }

    //expected 옵션을 사용하면, 아래 주석처리 부분을 지울 수 있다.
    //발생한 예외를 잡을 수 있다.
    @Test(expected = IllegalStateException.class) //지정 예외가 발생하면 테스트 성공, 발생 안하면 실패
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");
        //when
        memberService.join(member1);
        memberService.join(member2);
        /*try {
            memberService.join(member2); //에러가 발생한다.
        }catch (IllegalStateException e){
            return;
        }*/
        //then
        //로직이 여기까지 오면 잘못된것이므로(예외가 진작에 발생해야함.) fail 시킴
        fail("예외가 발생해야 한다.");;
    }
}