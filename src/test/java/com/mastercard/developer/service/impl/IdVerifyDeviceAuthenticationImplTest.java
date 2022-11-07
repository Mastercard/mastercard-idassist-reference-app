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

import com.mastercard.developer.example.IdVerifyDeviceAuthenticationExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdVerifyDeviceAuthenticationApi;
import com.mastercard.dis.mids.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.dis.mids.model.id.verification.DeviceIpAddress;
import com.mastercard.dis.mids.model.id.verification.DevicePhoneNumber;
import com.mastercard.dis.mids.model.id.verification.DeviceVerificationFingerprint;
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
public class IdVerifyDeviceAuthenticationImplTest {

    @Mock
    private IdVerifyDeviceAuthenticationApi idVerifyDeviceAuthenticationApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private IdVerifyDeviceAuthenticationServiceImpl idVerifyDeviceAuthenticationService;

    @Test
    public void testcreateDeviceAuthentication() throws ServiceException, ApiException {
        when(idVerifyDeviceAuthenticationApi.deviceAuthentication(eq(IdVerifyDeviceAuthenticationExample.getDeviceIpAddress()), eq(true))).thenReturn(getDeviceAuthenticationVerificationUrl());

        DeviceAuthenticationVerificationUrl result = idVerifyDeviceAuthenticationService.deviceAuthentication(IdVerifyDeviceAuthenticationExample.getDeviceIpAddress());

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthentication(eq(IdVerifyDeviceAuthenticationExample.getDeviceIpAddress()), eq(true));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testcreateDeviceAuthenticationError() throws ApiException {
        when(idVerifyDeviceAuthenticationApi.deviceAuthentication(eq(new DeviceIpAddress()), eq(true))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));
        DeviceIpAddress deviceIpAddress  =  new DeviceIpAddress();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyDeviceAuthenticationService.deviceAuthentication(deviceIpAddress));

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthentication(eq(new DeviceIpAddress()), eq(true));
    }

    @Test
    public void testcreateDeviceAuthenticationVerification() throws ServiceException, ApiException {
        when(idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(eq(IdVerifyDeviceAuthenticationExample.getDeviceVerificationFingerprint()), eq(true))).thenReturn(getDevicePhoneNumber());

        DevicePhoneNumber result = idVerifyDeviceAuthenticationService.deviceAuthenticationVerification(IdVerifyDeviceAuthenticationExample.getDeviceVerificationFingerprint());

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthenticationVerification(eq(IdVerifyDeviceAuthenticationExample.getDeviceVerificationFingerprint()), eq(true));
        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals("transactionId", result.getTransactionId())
        );
    }

    @Test
    public void testcreateDeviceAuthenticationVerificationError() throws ServiceException, ApiException {
        when(idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(eq(new DeviceVerificationFingerprint()), eq(true))).thenThrow(new ApiException());
        when(exceptionUtil.logAndConvertToServiceException(any(ApiException.class))).thenReturn(new ServiceException(""));
        DeviceVerificationFingerprint deviceVerificationFingerprint = new DeviceVerificationFingerprint();
        Assertions.assertThrows(ServiceException.class, () -> idVerifyDeviceAuthenticationService.deviceAuthenticationVerification(deviceVerificationFingerprint));

        verify(idVerifyDeviceAuthenticationApi, times(1)).deviceAuthenticationVerification(eq(new DeviceVerificationFingerprint()), eq(true));
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
