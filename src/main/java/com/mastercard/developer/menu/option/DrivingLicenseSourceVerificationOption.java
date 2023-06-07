package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SourceVerificationExample;
import com.mastercard.developer.service.SourceVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mastercard.developer.example.SourceVerificationExample.DOCUMENT_ISSUING_COUNTRY;

@Component
@RequiredArgsConstructor
public class DrivingLicenseSourceVerificationOption implements MenuOption {

    private final SourceVerificationService sourceVerificationService;

    @Override
    public void choose(Scanner scanner) {
        sourceVerificationService.sourceVerificationDrivingLicense(DOCUMENT_ISSUING_COUNTRY, SourceVerificationExample.createSourceVerificationDrivingLicenseRequestAttributes());
    }

    @Override
    public String getOptionName() {
        return "Driving License Verification";
    }
}
