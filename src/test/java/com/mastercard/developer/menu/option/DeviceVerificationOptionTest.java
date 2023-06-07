package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.DeviceAuthenticationExample;
import com.mastercard.developer.service.DeviceAuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DeviceVerificationOptionTest {
    @InjectMocks
    DeviceVerificationOption deviceVerificationOption;

    @Mock
    DeviceAuthenticationService idVerifyDeviceAuthenticationServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void idVerifyDeviceAuthenticationVerification_createAndVerifyDeviceAuthentication_successfulResponse() {
        deviceVerificationOption.choose(scanner);
        verify(idVerifyDeviceAuthenticationServiceMock, times(1)).createDeviceAuthenticationVerification(DeviceAuthenticationExample.getDeviceVerificationFingerprint());
    }

    @Test
    void idVerifyDeviceAuthenticationVerification_getOptionName_resultMatches() {
        assertEquals("Device Authentication Verification", deviceVerificationOption.getOptionName());
    }
}
