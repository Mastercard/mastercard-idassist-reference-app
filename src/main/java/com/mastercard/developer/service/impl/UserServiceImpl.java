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
import com.mastercard.developer.service.UserService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityPrefillApi;
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityVerificationApi;
import com.mastercard.dis.mids.api.PhoneNumberTrustScoringApi;
import com.mastercard.dis.mids.model.id.verification.Identity;
import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerification;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.id.verification.TrustScore;
import com.mastercard.dis.mids.model.id.verification.TrustScoreUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final PhoneNumberBasedIdentityPrefillApi userApiPrefill;
    private final PhoneNumberBasedIdentityVerificationApi userApiIdentity;
    private final PhoneNumberTrustScoringApi trustScoreApi;
    private final ExceptionUtil exceptionUtil;

    @Value("${mastercard.client.encryption.enable:false}")
    private Boolean encryptionEnabled;

    @Autowired
    public UserServiceImpl(PhoneNumberBasedIdentityPrefillApi userApi, PhoneNumberBasedIdentityVerificationApi verificationApi, PhoneNumberTrustScoringApi trustScoringApi, ExceptionUtil exceptionUtil) {
        this.userApiPrefill = userApi;
        this.userApiIdentity = verificationApi;
        this.trustScoreApi = trustScoringApi;
        this.exceptionUtil = exceptionUtil;
    }

    public Identity userIdentity(IdentityPrefill identityPrefill) {
        try {
            return userApiPrefill.userIdentity(identityPrefill, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public IdentityVerification identityVerification(IdentityVerificationUserInfo identityVerificationUserInfo) {
        try {
            return userApiIdentity.identityVerificationAPI(identityVerificationUserInfo, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    public TrustScore userTrustScore(TrustScoreUserInfo request) {
        try {
            return trustScoreApi.userTrustScore(request, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}