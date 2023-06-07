package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SmsOtpExample;
import com.mastercard.developer.service.SmsOtpService;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.SMSOtp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsOtpFlowOption implements MenuOption {

    private final SmsOtpService smsOtpService;

    @Override
    public void choose(Scanner scanner) {
        SMSOtp smsOtp = SmsOtpExample.getSmsOtp();
        Otp otp = smsOtpService.createSMSOtp(smsOtp);
        log.info(" <--- Enter you VERIFICATION CODE and press ENTER: ");
        String smsCode = scanner.nextLine();
        OtpVerification verification = new OtpVerification()
                .code(smsCode)
                .otpId(otp.getOtpId())
                .countryCode(smsOtp.getCountryCode())
                .userConsent(OtpVerification.UserConsentEnum.fromValue(smsOtp.getUserConsent().getValue()));
        smsOtpService.createVerifyOtp(verification);
    }

    @Override
    public String getOptionName() {
        return "SMS OTP";
    }

}
