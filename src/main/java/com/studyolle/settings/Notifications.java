package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Notifications {

    //스터디 생성 알림
    private boolean studyCreatedByEmail;
    private boolean studyCreatedByWeb;

    //등록 결과 알림
    private boolean studyEnrollmentResultByEmail;
    private boolean studyEnrollmentResultByWeb;

    //스터디 변경 알림
    private boolean studyUpdatedByEmail;
    private boolean studyUpdatedByWeb;

}
