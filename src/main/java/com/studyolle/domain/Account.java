package com.studyolle.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified; //이메일 인정 여부확인 flag

    private String emailCheckToken; //이메일 검증 토큰

    private LocalDateTime emailCheckTokenGeneratedAt; //이메일 토큰 발급시간

    private LocalDateTime joinedAt; //이메일 인증 시점

    private String bio; //자기소개

    private String url; //웹사이트 url

    private String occupation; //직업

    private String location; //주소 도시이름

    @Lob //String 기본 설정 varchar(255)
    @Basic(fetch = FetchType.EAGER)
    private String profileImage; //프로필 이미지

    private boolean studyCreatedByEmail; //알림 설정 이메일

    private boolean studyCreatedByWeb; //알림 설정 웹

    private boolean studyEnrollmentResultByWeb; //스터디 모임의 가입신청결과 알림 설정

    private boolean studyUpdatedByWeb; //스터디 모임 변경사항 알림 설정

    //이메일인증시 발송할 토큰 생성
    public void generateEmailCheckToken() {
        this.emailCheckToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    //회원가입 이메일인증 확인
    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    //발송한 토큰 확인
    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    //이메일 인증 토큰 발송 시간 제한
    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", emailVerified=" + emailVerified +
                ", emailCheckToken='" + emailCheckToken + '\'' +
                ", emailCheckTokenGeneratedAt=" + emailCheckTokenGeneratedAt +
                ", joinedAt=" + joinedAt +
                ", bio='" + bio + '\'' +
                ", url='" + url + '\'' +
                ", occupation='" + occupation + '\'' +
                ", location='" + location + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", studyCreatedByEmail=" + studyCreatedByEmail +
                ", studyCreatedByWeb=" + studyCreatedByWeb +
                ", studyEnrollmentResultByWeb=" + studyEnrollmentResultByWeb +
                ", studyUpdatedByWeb=" + studyUpdatedByWeb +
                '}';
    }
}
