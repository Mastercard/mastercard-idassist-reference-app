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
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityPrefillApi;
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityVerificationApi;
import com.mastercard.dis.mids.api.PhoneNumberTrustScoringApi;
import com.mastercard.dis.mids.model.id.verification.Identity;
import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerification;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.id.verification.TrustScore;
import com.mastercard.dis.mids.model.id.verification.TrustScoreUserInfo;
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
class UserServiceImplTest {

    @Mock
    private PhoneNumberBasedIdentityPrefillApi userApiPrefillMock;
    @Mock
    private PhoneNumberBasedIdentityVerificationApi userApiIdentityMock;
    @Mock
    private PhoneNumberTrustScoringApi trustScoreApiMock;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreate(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(userApiPrefillMock.userIdentity(eq(new IdentityPrefill()), eq(encrypt))).thenReturn(getIdentity());

        Identity result = userService.userIdentity(new IdentityPrefill());

        verify(userApiPrefillMock, times(1)).userIdentity(eq(new IdentityPrefill()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateError(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(userApiPrefillMock.userIdentity(eq(new IdentityPrefill()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        IdentityPrefill identityPrefill = new IdentityPrefill();
        Assertions.assertThrows(ServiceException.class, () -> userService.userIdentity(identityPrefill));

        verify(userApiPrefillMock, times(1)).userIdentity(eq(new IdentityPrefill()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateUserVerification(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(userApiIdentityMock.identityVerificationAPI(eq(new IdentityVerificationUserInfo()), eq(encrypt))).thenReturn(getIdentityVerification());

        IdentityVerification result = userService.identityVerification(new IdentityVerificationUserInfo());

        verify(userApiIdentityMock, times(1)).identityVerificationAPI(eq(new IdentityVerificationUserInfo()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateUserVerificationError(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(userApiIdentityMock.identityVerificationAPI(eq(new IdentityVerificationUserInfo()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        IdentityVerificationUserInfo identityVerificationUserInfo = new IdentityVerificationUserInfo();
        Assertions.assertThrows(ServiceException.class, () -> userService.identityVerification(identityVerificationUserInfo));

        verify(userApiIdentityMock, times(1)).identityVerificationAPI(eq(new IdentityVerificationUserInfo()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testTrustScore(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(trustScoreApiMock.userTrustScore(eq(new TrustScoreUserInfo()), eq(encrypt))).thenReturn(new TrustScore());

        TrustScore result = userService.userTrustScore(new TrustScoreUserInfo());

        assertNotNull(result);
        verify(trustScoreApiMock, times(1)).userTrustScore(eq(new TrustScoreUserInfo()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testTrustScoreError(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(userService, "encryptionEnabled", encrypt);

        when(trustScoreApiMock.userTrustScore(eq(new TrustScoreUserInfo()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));

        TrustScoreUserInfo trustScoreUserInfo = new TrustScoreUserInfo();
        Assertions.assertThrows(ServiceException.class, () -> userService.userTrustScore(trustScoreUserInfo));

        verify(trustScoreApiMock, times(1)).userTrustScore(eq(new TrustScoreUserInfo()), eq(encrypt));
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
