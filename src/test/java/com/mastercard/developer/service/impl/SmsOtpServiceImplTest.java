/*
 Copyright (c) 2021 Mastercard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.SmsOtpApi;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.OtpVerificationResult;
import com.mastercard.dis.mids.model.id.verification.SMSOtp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SmsOtpServiceImplTest {

    @Mock
    private SmsOtpApi otpApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private SmsOtpServiceImpl smsOtpService;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateOtpSMS(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(smsOtpService, "encryptionEnabled", encrypt);

        when(otpApi.sendSmsOtp(eq(new SMSOtp()), eq(encrypt))).thenReturn(getSmsOtp());

        Otp result = smsOtpService.createSMSOtp(new SMSOtp());

        verify(otpApi, times(1)).sendSmsOtp(eq(new SMSOtp()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateOtpSMSError(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(smsOtpService, "encryptionEnabled", encrypt);

        when(otpApi.sendSmsOtp(eq(new SMSOtp()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        SMSOtp smsOTP = new SMSOtp();
        Assertions.assertThrows(ServiceException.class, () -> smsOtpService.createSMSOtp(smsOTP));

        verify(otpApi, times(1)).sendSmsOtp(eq(new SMSOtp()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateVerifyOtp(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(smsOtpService, "encryptionEnabled", encrypt);

        when(otpApi.verifySmsOtp(eq(new OtpVerification()), eq(encrypt))).thenReturn(getSMSOTPVerificationResult());

        OtpVerificationResult result = smsOtpService.createVerifyOtp(new OtpVerification());

        verify(otpApi, times(1)).verifySmsOtp(eq(new OtpVerification()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId()));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateVerifyOtpError(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(smsOtpService, "encryptionEnabled", encrypt);

        when(otpApi.verifySmsOtp(eq(new OtpVerification()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        OtpVerification smsOTPVerification = new OtpVerification();
        Assertions.assertThrows(ServiceException.class, () -> smsOtpService.createVerifyOtp(smsOTPVerification));

        verify(otpApi, times(1)).verifySmsOtp(eq(new OtpVerification()), eq(encrypt));
    }

    private Otp getSmsOtp() {
        Otp smsOtp = new Otp();
        smsOtp.setTransactionId("transactionId");
        return smsOtp;
    }

    private OtpVerificationResult getSMSOTPVerificationResult() {
        OtpVerificationResult smsOtpVerificationResult = new OtpVerificationResult();
        smsOtpVerificationResult.setTransactionId("transactionId");
        return smsOtpVerificationResult;
    }
}
