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
import com.mastercard.dis.mids.api.IdVerifyUserApi;
import com.mastercard.dis.mids.model.id.verification.Identity;
import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerification;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.id.verification.TrustScore;
import com.mastercard.dis.mids.model.id.verification.TrustScoreUserInfo;
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
public class IdVerifyUserServiceImplTest {

    @Mock
    private IdVerifyUserApi idVerifyUserApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private IdVerifyUserServiceImpl idVerifyUserService;

    @Test
    public void testCreate() throws ServiceException, ApiException {
        when(idVerifyUserApi.userIdentity(eq(new IdentityPrefill()), eq(true))).thenReturn(getIdentity());

        Identity result = idVerifyUserService.userIdentity(new IdentityPrefill());

        verify(idVerifyUserApi, times(1)).userIdentity(eq(new IdentityPrefill()), eq(true));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testCreateError() throws ApiException {
        when(idVerifyUserApi.userIdentity(eq(new IdentityPrefill()), eq(true))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        IdentityPrefill identityPrefill = new IdentityPrefill();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyUserService.userIdentity(identityPrefill));

        verify(idVerifyUserApi, times(1)).userIdentity(eq(new IdentityPrefill()), eq(true));
    }

    @Test
    public void testCreateUserVerification() throws ServiceException, ApiException {
        when(idVerifyUserApi.identityVerification(eq(new IdentityVerificationUserInfo()), eq(true))).thenReturn(getIdentityVerification());

        IdentityVerification result = idVerifyUserService.identityVerification(new IdentityVerificationUserInfo());

        verify(idVerifyUserApi, times(1)).identityVerification(eq(new IdentityVerificationUserInfo()), eq(true));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testCreateUserVerificationError() throws ApiException {
        when(idVerifyUserApi.identityVerification(eq(new IdentityVerificationUserInfo()), eq(true))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        IdentityVerificationUserInfo identityVerificationUserInfo = new IdentityVerificationUserInfo();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyUserService.identityVerification(identityVerificationUserInfo));

        verify(idVerifyUserApi, times(1)).identityVerification(eq(new IdentityVerificationUserInfo()), eq(true));
    }

    @Test
    public void testTrustScore() throws ServiceException, ApiException {
        when(idVerifyUserApi.userTrustScore(eq(new TrustScoreUserInfo()), eq(true))).thenReturn(new TrustScore());

        TrustScore result = idVerifyUserService.userTrustScore(new TrustScoreUserInfo());

        assertNotNull(result);
        verify(idVerifyUserApi, times(1)).userTrustScore(eq(new TrustScoreUserInfo()), eq(true));
    }

    @Test
    public void testTrustScoreError() throws ServiceException, ApiException {
        when(idVerifyUserApi.userTrustScore(eq(new TrustScoreUserInfo()), eq(true))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        TrustScoreUserInfo trustScoreUserInfo = new TrustScoreUserInfo();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyUserService.userTrustScore(trustScoreUserInfo));

        verify(idVerifyUserApi, times(1)).userTrustScore(eq(new TrustScoreUserInfo()), eq(true));
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
