package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.DeviceAuthenticationExample;
import com.mastercard.developer.service.DeviceAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class DeviceVerificationOption implements MenuOption {

    private final DeviceAuthenticationService idVerifyDeviceAuthenticationService;

    @Override
    public void choose(Scanner scanner) {
        idVerifyDeviceAuthenticationService.createDeviceAuthenticationVerification(DeviceAuthenticationExample.getDeviceVerificationFingerprint());
    }

    @Override
    public String getOptionName() {
        return "Device Authentication Verification";
    }
}
