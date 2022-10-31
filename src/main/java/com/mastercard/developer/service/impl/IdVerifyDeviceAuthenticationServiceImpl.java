package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.service.IdVerifyDeviceAuthenticationService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdVerifyDeviceAuthenticationApi;
import com.mastercard.dis.mids.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.dis.mids.model.id.verification.DeviceIpAddress;
import com.mastercard.dis.mids.model.id.verification.DevicePhoneNumber;
import com.mastercard.dis.mids.model.id.verification.DeviceVerificationFingerprint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdVerifyDeviceAuthenticationServiceImpl implements IdVerifyDeviceAuthenticationService {

    private final IdVerifyDeviceAuthenticationApi idVerifyDeviceAuthenticationApi;
    private final ExceptionUtil exceptionUtil;

    @Autowired
    public IdVerifyDeviceAuthenticationServiceImpl(IdVerifyDeviceAuthenticationApi idVerifyDeviceAuthenticationApi, ExceptionUtil exceptionUtil) {
        this.idVerifyDeviceAuthenticationApi = idVerifyDeviceAuthenticationApi;
        this.exceptionUtil = exceptionUtil;
    }

    @Override
    public DeviceAuthenticationVerificationUrl deviceAuthentication(DeviceIpAddress deviceIpAddress) {
        try {
            return idVerifyDeviceAuthenticationApi.deviceAuthentication(deviceIpAddress, Boolean.TRUE);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public DevicePhoneNumber deviceAuthenticationVerification(DeviceVerificationFingerprint deviceVerificationFingerprint) {
        try {
            return idVerifyDeviceAuthenticationApi.deviceAuthenticationVerification(deviceVerificationFingerprint, Boolean.TRUE);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}
