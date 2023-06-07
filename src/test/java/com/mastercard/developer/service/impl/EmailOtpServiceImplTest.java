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
import com.mastercard.dis.mids.api.EmailOtpApi;
import com.mastercard.dis.mids.model.id.verification.EmailOtp;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.OtpVerificationResult;
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
class EmailOtpServiceImplTest {

    @Mock
    private EmailOtpApi otpApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private EmailOtpServiceImpl emailOtpService;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateOtpEmail(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(emailOtpService, "encryptionEnabled", encrypt);

        when(otpApi.sendEmailOtp(eq(new EmailOtp()), eq(encrypt))).thenReturn(getEmailOtp());

        Otp result = emailOtpService.createEmailOtp(new EmailOtp());

        verify(otpApi, times(1)).sendEmailOtp(eq(new EmailOtp()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateOtpEmailError(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(emailOtpService, "encryptionEnabled", encrypt);

        when(otpApi.sendEmailOtp(eq(new EmailOtp()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        EmailOtp emailOTP = new EmailOtp();
        Assertions.assertThrows(ServiceException.class, () -> emailOtpService.createEmailOtp(emailOTP));

        verify(otpApi, times(1)).sendEmailOtp(eq(new EmailOtp()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateVerifyOtp(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(emailOtpService, "encryptionEnabled", encrypt);

        when(otpApi.verifyEmailOtp(eq(new OtpVerification()), eq(encrypt))).thenReturn(getEmailOTPVerificationResult());

        OtpVerificationResult result = emailOtpService.createVerifyOtp(new OtpVerification());

        verify(otpApi, times(1)).verifyEmailOtp(eq(new OtpVerification()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId()));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateVerifyOtpError(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(emailOtpService, "encryptionEnabled", encrypt);

        when(otpApi.verifyEmailOtp(eq(new OtpVerification()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        OtpVerification emailOTPVerification = new OtpVerification();
        Assertions.assertThrows(ServiceException.class, () -> emailOtpService.createVerifyOtp(emailOTPVerification));

        verify(otpApi, times(1)).verifyEmailOtp(eq(new OtpVerification()), eq(encrypt));
    }

    private Otp getEmailOtp() {
        Otp emailOtp = new Otp();
        emailOtp.setTransactionId("transactionId");
        return emailOtp;
    }

    private OtpVerificationResult getEmailOTPVerificationResult() {
        OtpVerificationResult emailOtpVerificationResult = new OtpVerificationResult();
        emailOtpVerificationResult.setTransactionId("transactionId");
        return emailOtpVerificationResult;
    }


}
