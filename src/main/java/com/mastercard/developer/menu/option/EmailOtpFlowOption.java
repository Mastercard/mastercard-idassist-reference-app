package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.EmailOtpExample;
import com.mastercard.developer.service.EmailOtpService;
import com.mastercard.dis.mids.model.id.verification.EmailOtp;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailOtpFlowOption implements MenuOption {

    private final EmailOtpService emailOtpService;

    @Override
    public void choose(Scanner scanner) {
        EmailOtp emailOtp = EmailOtpExample.getEmailOtp();
        Otp otp = emailOtpService.createEmailOtp(emailOtp);
        log.info(" <--- Enter you VERIFICATION CODE and press ENTER: ");
        String emailCode = scanner.nextLine();
        OtpVerification verification = new OtpVerification()
                .code(emailCode)
                .otpId(otp.getOtpId())
                .countryCode(emailOtp.getCountryCode())
                .userConsent(OtpVerification.UserConsentEnum.fromValue(emailOtp.getUserConsent().getValue()));
        emailOtpService.createVerifyOtp(verification);
    }

    @Override
    public String getOptionName() {
        return "EMAIL OTP";
    }
}
