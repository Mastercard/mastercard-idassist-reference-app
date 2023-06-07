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

import com.mastercard.developer.example.DeviceAuthenticationExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.DeviceAuthenticationApi;
import com.mastercard.dis.mids.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.dis.mids.model.id.verification.DeviceIpAddress;
import com.mastercard.dis.mids.model.id.verification.DevicePhoneNumber;
import com.mastercard.dis.mids.model.id.verification.DeviceVerificationFingerprint;
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
class DeviceAuthenticationImplTest {

    @Mock
    private DeviceAuthenticationApi idVerifyDeviceAuthenticationApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private DeviceAuthenticationServiceImpl idVerifyDeviceAuthenticationService;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateDeviceAuthentication(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(idVerifyDeviceAuthenticationService, "encryptionEnabled", encrypt);

        when(idVerifyDeviceAuthenticationApi.deviceAuthentication(eq(DeviceAuthenticationExample.getDeviceIpAddress()), eq(encrypt))).thenReturn(getDeviceAuthenticationVerificationUrl());

        DeviceAuthenticationVerificationUrl result = idVerifyDeviceAuthenticationService.createDeviceAuthentication(DeviceAuthenticationExample.getDeviceIpAddress());

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthentication(eq(DeviceAuthenticationExample.getDeviceIpAddress()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateDeviceAuthenticationError(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(idVerifyDeviceAuthenticationService, "encryptionEnabled", encrypt);

        when(idVerifyDeviceAuthenticationApi.deviceAuthentication(eq(new DeviceIpAddress()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));
        DeviceIpAddress deviceIpAddress = new DeviceIpAddress();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyDeviceAuthenticationService.createDeviceAuthentication(deviceIpAddress));

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthentication(eq(new DeviceIpAddress()), eq(encrypt));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateDeviceAuthenticationVerification(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(idVerifyDeviceAuthenticationService, "encryptionEnabled", encrypt);

        when(idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(eq(DeviceAuthenticationExample.getDeviceVerificationFingerprint()), eq(encrypt))).thenReturn(getDevicePhoneNumber());

        DevicePhoneNumber result = idVerifyDeviceAuthenticationService.createDeviceAuthenticationVerification(DeviceAuthenticationExample.getDeviceVerificationFingerprint());

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthenticationVerification(eq(DeviceAuthenticationExample.getDeviceVerificationFingerprint()), eq(encrypt));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCreateDeviceAuthenticationVerificationError(boolean encrypt) throws ServiceException, ApiException {
        ReflectionTestUtils.setField(idVerifyDeviceAuthenticationService, "encryptionEnabled", encrypt);

        when(idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(eq(new DeviceVerificationFingerprint()), eq(encrypt))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));
        DeviceVerificationFingerprint deviceVerificationFingerprint = new DeviceVerificationFingerprint();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyDeviceAuthenticationService.createDeviceAuthenticationVerification(deviceVerificationFingerprint));

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthenticationVerification(eq(new DeviceVerificationFingerprint()), eq(encrypt));
    }

    private DeviceAuthenticationVerificationUrl getDeviceAuthenticationVerificationUrl() {
        DeviceAuthenticationVerificationUrl deviceAuthenticationVerificationUrl = new DeviceAuthenticationVerificationUrl();
        deviceAuthenticationVerificationUrl.setTransactionId("transactionId");
        return deviceAuthenticationVerificationUrl;
    }

    private DevicePhoneNumber getDevicePhoneNumber() {
        DevicePhoneNumber devicePhoneNumber = new DevicePhoneNumber();
        devicePhoneNumber.setTransactionId("transactionId");
        return devicePhoneNumber;
    }
}
