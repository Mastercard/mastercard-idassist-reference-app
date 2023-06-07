package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SourceVerificationMedicareExample;
import com.mastercard.developer.service.MedicareSourceVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mastercard.developer.example.SourceVerificationExample.DOCUMENT_ISSUING_COUNTRY;

@Component
@RequiredArgsConstructor
public class MedicalCareOption implements MenuOption {

    private final MedicareSourceVerificationService medicareSourceVerificationService;

    @Override
    public void choose(Scanner scanner) {
        medicareSourceVerificationService.createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes());
    }

    @Override
    public String getOptionName() {
        return "Medicare Card Verification";
    }
}
