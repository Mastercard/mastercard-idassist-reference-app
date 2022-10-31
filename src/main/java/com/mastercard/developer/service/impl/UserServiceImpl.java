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
import com.mastercard.dis.mids.api.UserApi;
import com.mastercard.dis.mids.model.Identity;
import com.mastercard.dis.mids.model.IdentityPrefill;
import com.mastercard.dis.mids.model.IdentityVerification;
import com.mastercard.dis.mids.model.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.TrustScore;
import com.mastercard.dis.mids.model.TrustScoreUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserApi userApi;
    private final ExceptionUtil exceptionUtil;

    @Autowired
    public UserServiceImpl(UserApi userApi, ExceptionUtil exceptionUtil) {
        this.userApi = userApi;
        this.exceptionUtil = exceptionUtil;
    }

    public Identity create(IdentityPrefill identityPrefill) {
        try {
            return userApi.identityAPI(identityPrefill);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public IdentityVerification createUserVerification(IdentityVerificationUserInfo identityVerificationUserInfo) {
        try {
            return userApi.identityVerificationAPI(identityVerificationUserInfo);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    public TrustScore trust(TrustScoreUserInfo request) {
        try {
            return userApi.trustAPI(request);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}