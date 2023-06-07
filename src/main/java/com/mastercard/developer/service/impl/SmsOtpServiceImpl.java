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
import com.mastercard.developer.service.SmsOtpService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.SmsOtpApi;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.OtpVerificationResult;
import com.mastercard.dis.mids.model.id.verification.SMSOtp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsOtpServiceImpl implements SmsOtpService {

    private final SmsOtpApi otpApi;
    private final ExceptionUtil exceptionUtil;

    @Value("${mastercard.client.encryption.enable:false}")
    private Boolean encryptionEnabled;

    @Autowired
    public SmsOtpServiceImpl(SmsOtpApi otpApi, ExceptionUtil exceptionUtil) {
        this.otpApi = otpApi;
        this.exceptionUtil = exceptionUtil;
    }

    @Override
    public Otp createSMSOtp(SMSOtp smsOtp) {
        try {
            return otpApi.sendSmsOtp(smsOtp, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public OtpVerificationResult createVerifyOtp(OtpVerification otpVerification) {
        try {
            return otpApi.verifySmsOtp(otpVerification, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}
