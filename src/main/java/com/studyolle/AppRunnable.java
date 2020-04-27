package com.studyolle;

import com.studyolle.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunnable implements ApplicationRunner {

    @Autowired
    AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("테스트 실행시 주석");
//        SignUpForm signUpForm = new SignUpForm();
//        signUpForm.setEmail("rn00n@naver.com");
//        signUpForm.setNickname("byungryang");
//        signUpForm.setPassword("asdfasdf");
//        Account account = accountService.processNewAccount(signUpForm);
    }
}
