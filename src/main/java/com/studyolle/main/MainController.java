package com.studyolle.main;

import com.studyolle.account.CurrentUser;
import com.studyolle.domain.Account;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(@CurrentUser Account account, Model model) {
        if(account != null) { //인증을 한 사용자
            model.addAttribute(account);
        }
        return "index";
    }

    /*login*/
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
