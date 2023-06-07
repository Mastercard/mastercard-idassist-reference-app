package com.mastercard.developer.example;

import com.mastercard.dis.mids.model.id.verification.EmailOtp;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailOtpExample {

    public static EmailOtp getEmailOtp() {
        EmailOtp emailOtp = new EmailOtp();
        emailOtp.setEmailAddress(""); // Your e-mail
        emailOtp.setCountryCode("BR");
        emailOtp.setUserConsent(EmailOtp.UserConsentEnum.ACCEPT);
        emailOtp.locale("pt-BR");

        return emailOtp;
    }
}
