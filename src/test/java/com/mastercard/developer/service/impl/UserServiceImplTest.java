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
import com.mastercard.dis.mids.api.UserApi;
import com.mastercard.dis.mids.model.Identity;
import com.mastercard.dis.mids.model.IdentityPrefill;
import com.mastercard.dis.mids.model.IdentityVerification;
import com.mastercard.dis.mids.model.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.TrustScore;
import com.mastercard.dis.mids.model.TrustScoreUserInfo;
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
public class UserServiceImplTest {

    @Mock
    private UserApi userApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreate() throws ServiceException, ApiException {
        when(userApi.identityAPI(eq(new IdentityPrefill()))).thenReturn(getIdentity());

        Identity result = userService.create(new IdentityPrefill());

        verify(userApi, times(1)).identityAPI(eq(new IdentityPrefill()));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testCreateError() throws ApiException {
        when(userApi.identityAPI(eq(new IdentityPrefill()))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        Assertions.assertThrows(ServiceException.class, () -> userService.create(new IdentityPrefill()));

        verify(userApi, times(1)).identityAPI(eq(new IdentityPrefill()));
    }

    @Test
    public void testCreateUserVerification() throws ServiceException, ApiException {
        when(userApi.identityVerificationAPI(eq(new IdentityVerificationUserInfo()))).thenReturn(getIdentityVerification());

        IdentityVerification result = userService.createUserVerification(new IdentityVerificationUserInfo());

        verify(userApi, times(1)).identityVerificationAPI(eq(new IdentityVerificationUserInfo()));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testCreateUserVerificationError() throws ApiException {
        when(userApi.identityVerificationAPI(eq(new IdentityVerificationUserInfo()))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        Assertions.assertThrows(ServiceException.class, () -> userService.createUserVerification(new IdentityVerificationUserInfo()));

        verify(userApi, times(1)).identityVerificationAPI(eq(new IdentityVerificationUserInfo()));
    }

    @Test
    public void testTrustScore() throws ServiceException, ApiException {
        when(userApi.trustAPI(eq(new TrustScoreUserInfo()))).thenReturn(new TrustScore());

        TrustScore result = userService.trust(new TrustScoreUserInfo());

        assertNotNull(result);
        verify(userApi, times(1)).trustAPI(eq(new TrustScoreUserInfo()));
    }

    @Test
    public void testTrustScoreError() throws ServiceException, ApiException {
        when(userApi.trustAPI(eq(new TrustScoreUserInfo()))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        Assertions.assertThrows(ServiceException.class, () -> userService.trust(new TrustScoreUserInfo()));

        verify(userApi, times(1)).trustAPI(eq(new TrustScoreUserInfo()));
    }

    private Identity getIdentity() {
        Identity identity = new Identity();
        identity.setTransactionId("transactionId");
        return identity;
    }

    private IdentityVerification getIdentityVerification() {
        IdentityVerification IdentityVerification = new IdentityVerification();
        IdentityVerification.setTransactionId("transactionId");
        return IdentityVerification;
    }
}
