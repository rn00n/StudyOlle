package com.studyolle.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*Spring Security 인증 허용 설정*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/login", "/sign-up", "/check-email-token",
                        "/email-login", "/check-email-login", "/login-link").permitAll()
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login").permitAll();
        http.logout()
                .logoutSuccessUrl("http://www.naver.com/");
    }

    /*Static 리소스 인증 예외 등록*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
//        "/node_modules/**" 요청에는 시큐리티 필터를 적용하지 않도록 설정한다.
                .mvcMatchers("/node_modules/**")
                .requestMatchers(
                        PathRequest
                                .toStaticResources()
                                .atCommonLocations()
                );
    }

}
//CSRF(Cross-Site Request Forgery)
//타 사이트에서 나의 사이트로 form 데이터를 보내는 것
//기능이 활성화되어있다