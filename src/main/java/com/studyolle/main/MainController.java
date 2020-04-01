package com.studyolle.main;

import com.studyolle.account.AccountRepository;
import com.studyolle.account.CurrentUser;
import com.studyolle.domain.Account;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model) {

        if(account != null) { //인증을 한 사용자
//            Account findAccount = accountRepository.findByEmail(account.getEmail());
            model.addAttribute(account);
        }



        return "index";
    }

    @GetMapping("/test")
    public String test() {
        Account testAccount = accountRepository.findByEmail("rn00n@naver.com");

        if(testAccount != null)
            System.out.println(testAccount.toString());

        return "index";
    }

    /*login*/
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
