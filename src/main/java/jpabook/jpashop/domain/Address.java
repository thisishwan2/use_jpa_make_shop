package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//Address가 JPA의 내장 타입이기 때문 @Embeddable 을 붙힌다.
@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //기본 생성자가 없으면, JPA 기본 스펙이 생성할때 리플렉션이나 프록시 같은 기술을 써야하는데 기본생성자가 없으면 그걸 못한다.
    //public으로 열어두면 누구나 쓰니까, protected까지 허용을 해준다.
    //즉, JPA 스펙상 기본 생성자를 달아놓은것.
    protected Address(){
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
