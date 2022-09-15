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
import com.mastercard.developer.model.SMSOTP;
import com.mastercard.developer.model.SMSOTPGeneration;
import com.mastercard.developer.model.SMSOTPVerification;
import com.mastercard.developer.model.SMSOTPVerificationResult;
import com.mastercard.developer.service.SmsOtpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsOtpServiceImpl implements SmsOtpService {

    private final OtpApi otpApi;
    private final ExceptionUtil exceptionUtil;

    @Autowired
    public SmsOtpServiceImpl(OtpApi otpApi, ExceptionUtil exceptionUtil) {
        this.otpApi = otpApi;
        this.exceptionUtil = exceptionUtil;
    }

    @Override
    public SMSOTP createOtpsSMS(SMSOTPGeneration smsOtp){
        try {
            return otpApi.createOtpsSMS(smsOtp);
        }catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public SMSOTPVerificationResult createVerifyOtps(SMSOTPVerification otpVerification){
        try {
            return otpApi.verifyOtps(otpVerification);
        }catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }


}
