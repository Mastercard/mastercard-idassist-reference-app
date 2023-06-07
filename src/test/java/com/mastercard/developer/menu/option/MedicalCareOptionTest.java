package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SourceVerificationMedicareExample;
import com.mastercard.developer.service.MedicareSourceVerificationService;
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
class MedicalCareOptionTest {
    @InjectMocks
    MedicalCareOption medicalCareOption;

    @Mock
    MedicareSourceVerificationService medicareSourceVerificationServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void medicalCareVerification_verifyAgainstDriverLicenseAndPassport_successfulResponse() {
        medicalCareOption.choose(scanner);
        verify(medicareSourceVerificationServiceMock, times(1)).createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes());
    }

    @Test
    void medicalCareVerification_getOptionName_resultMatches() {
        assertEquals("Medicare Card Verification", medicalCareOption.getOptionName());
    }
}
