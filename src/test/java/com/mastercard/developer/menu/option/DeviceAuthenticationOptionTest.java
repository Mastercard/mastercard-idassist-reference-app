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
class DeviceAuthenticationOptionTest {
    @InjectMocks
    DeviceAuthenticationOption deviceAuthenticationOption;

    @Mock
    private DeviceAuthenticationService idVerifyDeviceAuthenticationServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void idVerifyDeviceAuthentication_createAndVerifyDeviceAuthentication_successfulResponse() {
        deviceAuthenticationOption.choose(scanner);
        verify(idVerifyDeviceAuthenticationServiceMock, times(1)).createDeviceAuthentication(DeviceAuthenticationExample.getDeviceIpAddress());
    }

    @Test
    void idVerifyDeviceAuthentication_getOptionName_resultMatches() {
        assertEquals("Device Authentication", deviceAuthenticationOption.getOptionName());
    }
}
