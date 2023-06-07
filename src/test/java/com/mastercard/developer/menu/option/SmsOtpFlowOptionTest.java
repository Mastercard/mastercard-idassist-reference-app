package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SmsOtpExample;
import com.mastercard.developer.service.SmsOtpService;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.SMSOtp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class SmsOtpFlowOptionTest {

    @InjectMocks
    private SmsOtpFlowOption smsOtpFlowOption;

    @Mock
    private SmsOtpService smsOtpServiceMock;

    @Test
    void return_response_ok() {
        String smsCodeInput = "123456";
        Otp otpMock = mock(Otp.class);

        when(smsOtpServiceMock.createSMSOtp(any(SMSOtp.class))).thenReturn(otpMock);
        when(otpMock.getOtpId()).thenReturn("c24e93a5-a0aa-4873-ad38-50d28b332969");

        smsOtpFlowOption.choose(new Scanner(new ByteArrayInputStream(smsCodeInput.getBytes()), "UTF-8"));

        verify(smsOtpServiceMock, times(1)).createSMSOtp(SmsOtpExample.getSmsOtp());
        verify(smsOtpServiceMock, times(1)).createVerifyOtp(any(OtpVerification.class));
    }

    @Test
    void option_description_matches() {
        assertEquals("SMS OTP", smsOtpFlowOption.getOptionName());
    }
}