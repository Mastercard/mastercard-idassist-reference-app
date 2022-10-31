package com.mastercard.developer.service;

import com.mastercard.dis.mids.model.id.verification.DeviceAuthenticationVerificationUrl;
import com.mastercard.dis.mids.model.id.verification.DeviceIpAddress;
import com.mastercard.dis.mids.model.id.verification.DevicePhoneNumber;
import com.mastercard.dis.mids.model.id.verification.DeviceVerificationFingerprint;

public interface IdVerifyDeviceAuthenticationService {

    DeviceAuthenticationVerificationUrl deviceAuthentication(DeviceIpAddress deviceIpAddress);

    DevicePhoneNumber deviceAuthenticationVerification(DeviceVerificationFingerprint deviceVerificationFingerprint);
}
