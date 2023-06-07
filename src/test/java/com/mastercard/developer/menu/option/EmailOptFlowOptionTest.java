package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.EmailOtpExample;
import com.mastercard.developer.service.EmailOtpService;
import com.mastercard.dis.mids.model.id.verification.EmailOtp;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EmailOptFlowOptionTest {

    @InjectMocks
    private EmailOtpFlowOption emailOtpFlowOption;

    @Mock
    private EmailOtpService emailOtpServiceMock;

    @Test
    void return_response_ok() {
        String emailCodeInput = "123456";
        Otp otpMock = mock(Otp.class);

        when(emailOtpServiceMock.createEmailOtp(any(EmailOtp.class))).thenReturn(otpMock);
        when(otpMock.getOtpId()).thenReturn("c24e93a5-a0aa-4873-ad38-50d28b332969");

        emailOtpFlowOption.choose(new Scanner(new ByteArrayInputStream(emailCodeInput.getBytes()), "UTF-8"));

        verify(emailOtpServiceMock, times(1)).createEmailOtp(EmailOtpExample.getEmailOtp());
        verify(emailOtpServiceMock, times(1)).createVerifyOtp(any(OtpVerification.class));
    }

    @Test
    void option_description_matches() {
        assertEquals("EMAIL OTP", emailOtpFlowOption.getOptionName());
    }
}