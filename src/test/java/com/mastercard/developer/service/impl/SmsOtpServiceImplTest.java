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

import com.mastercard.developer.ApiException;
import com.mastercard.developer.api.OtpApi;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.model.SMSOTP;
import com.mastercard.developer.model.SMSOTPGeneration;
import com.mastercard.developer.model.SMSOTPVerification;
import com.mastercard.developer.model.SMSOTPVerificationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SmsOtpServiceImplTest {

    @Mock
    private OtpApi otpApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private SmsOtpServiceImpl smsOtpService;

    @Test
    public void testCreateOtpsSMS() throws ServiceException, ApiException {
        when(otpApi.createOtpsSMS(eq(new SMSOTPGeneration()))).thenReturn(getSMSOTP());

        SMSOTP result = smsOtpService.createOtpsSMS(new SMSOTPGeneration());

        verify(otpApi, times(1)).createOtpsSMS(eq(new SMSOTPGeneration()));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testCreateOtpsSMSError() throws ApiException {
        when(otpApi.createOtpsSMS(eq(new SMSOTPGeneration()))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenThrow(new ServiceException(""));

        Assertions.assertThrows(ServiceException.class, () -> smsOtpService.createOtpsSMS(new SMSOTPGeneration()));

        verify(otpApi, times(1)).createOtpsSMS(eq(new SMSOTPGeneration()));
        verify(exceptionUtil, times(1)).logAndConvertToServiceException(any(ApiException.class));
    }

    @Test
    public void testcreateVerifyOtps() throws ServiceException, ApiException {
        when(otpApi.verifyOtps(eq(new SMSOTPVerification()))).thenReturn(getSMSOTPVerificationResult());

        SMSOTPVerificationResult result = smsOtpService.createVerifyOtps(new SMSOTPVerification());

        verify(otpApi, times(1)).verifyOtps(eq(new SMSOTPVerification()));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId()));
    }

    @Test
    public void testcreateVerifyOtpsError() throws ServiceException, ApiException {
        when(otpApi.verifyOtps(eq(new SMSOTPVerification()))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenThrow(new ServiceException(""));

        Assertions.assertThrows(ServiceException.class, () -> smsOtpService.createVerifyOtps(new SMSOTPVerification()));

        verify(otpApi, times(1)).verifyOtps(eq(new SMSOTPVerification()));
        verify(exceptionUtil, times(1)).logAndConvertToServiceException(any(ApiException.class));
    }

    private SMSOTP getSMSOTP() {
        SMSOTP smsOtp = new SMSOTP();
        smsOtp.setTransactionId("transactionId");
        return smsOtp;
    }

    private SMSOTPVerificationResult getSMSOTPVerificationResult() {
        SMSOTPVerificationResult smsotpVerificationResult = new SMSOTPVerificationResult();
        smsotpVerificationResult.setTransactionId("transactionId");
        return smsotpVerificationResult;
    }


}
