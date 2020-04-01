package com.studyolle.config;

import com.studyolle.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;
    private final DataSource dataSource;

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
                .logoutSuccessUrl("/");

        //로그인정보 토큰 기억하기 #1
        http.rememberMe()
                .userDetailsService(accountService)
                .tokenRepository(tokenRepository());

    }

    //#1
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
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