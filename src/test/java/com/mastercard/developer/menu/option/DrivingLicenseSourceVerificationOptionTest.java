package com.mastercard.developer.menu.option;


import com.mastercard.developer.example.SourceVerificationExample;
import com.mastercard.developer.service.SourceVerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static com.mastercard.developer.example.SourceVerificationExample.DOCUMENT_ISSUING_COUNTRY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DrivingLicenseSourceVerificationOptionTest {
    @InjectMocks
    DrivingLicenseSourceVerificationOption drivingLicenseSourceVerificationOption;

    @Mock
    private SourceVerificationService sourceVerificationServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void drivingLicenseSourceOption_performDrivingLicenseSourceVerification_successfulResponse() {
        drivingLicenseSourceVerificationOption.choose(scanner);
        verify(sourceVerificationServiceMock, times(1)).sourceVerificationDrivingLicense(DOCUMENT_ISSUING_COUNTRY, SourceVerificationExample.createSourceVerificationDrivingLicenseRequestAttributes());
    }

    @Test
    void drivingLicenseSourceOption_getOptionName_resultMatches() {
        assertEquals("Driving License Verification",drivingLicenseSourceVerificationOption.getOptionName());
    }
}
