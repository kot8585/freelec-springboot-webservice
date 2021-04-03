package com.jujuidu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킵니다.
//스프링부트 테스트와 JUnit 사이에 연결자 역할을 합니다.
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired //스프링이 관리하는 빈을 주입 받습니다.
    private MockMvc mvc;
    //웹 API를 테스트할 때 사용합니다. //스프링 MVC 테스트의 시작접입니다
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API태스트 가능

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvd를 통해 HTTP GET 요청
            .andExpect(status().isOk()) //Httop Header의 Status를 검증
            .andExpect(content().string("hello")); //응답 본문의 내용 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        //.param의 값은 String만 허용됩니다. 그래서 숫자/날째 등의 데이터도 등록할 때는 문자열로 변경해야만 가능합니다.
        //jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드입니다. $를 기준으로 필드명을 명시힙니다
        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
