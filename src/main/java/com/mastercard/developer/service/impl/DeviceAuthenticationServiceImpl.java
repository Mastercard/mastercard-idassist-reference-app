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
import com.mastercard.developer.service.DeviceAuthenticationService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.DeviceAuthenticationApi;
import com.mastercard.dis.mids.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.dis.mids.model.id.verification.DeviceIpAddress;
import com.mastercard.dis.mids.model.id.verification.DevicePhoneNumber;
import com.mastercard.dis.mids.model.id.verification.DeviceVerificationFingerprint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceAuthenticationServiceImpl implements DeviceAuthenticationService {

    private final DeviceAuthenticationApi idVerifyDeviceAuthenticationApi;
    private final ExceptionUtil exceptionUtil;

    @Value("${mastercard.client.encryption.enable:false}")
    private Boolean encryptionEnabled;

    @Autowired
    public DeviceAuthenticationServiceImpl(DeviceAuthenticationApi idVerifyDeviceAuthenticationApi, ExceptionUtil exceptionUtil) {
        this.idVerifyDeviceAuthenticationApi = idVerifyDeviceAuthenticationApi;
        this.exceptionUtil = exceptionUtil;
    }

    @Override
    public DeviceAuthenticationVerificationUrl createDeviceAuthentication(DeviceIpAddress deviceIpAddress) {
        try {
            return idVerifyDeviceAuthenticationApi.deviceAuthentication(deviceIpAddress, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public DevicePhoneNumber createDeviceAuthenticationVerification(DeviceVerificationFingerprint deviceVerificationFingerprint) {
        try {
            return idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(deviceVerificationFingerprint, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}