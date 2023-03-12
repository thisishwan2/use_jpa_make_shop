package jpabook.jpashop.controller;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result ){//BindingResult를 이용하면, 오류 발생시 오류 내용을 result에 담아서 아래의 코드가 실행된다.
        //기존에 Vaild의 객체에 오류가 있으면 튕기는데,
        // BindingResult 가 있으면, 오류가 result에 담겨서 코드가 실행된다.

        if(result.hasErrors()){
            return "members/createMemberForm";
        }//스프링과 타임리프가 인티그레이션이 잘되어있다.
        // 이처럼 코드를 짜면, 스프링이 BindingResult를 리턴 페이지 까지 끌고가서 오류를 보여준다.
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
