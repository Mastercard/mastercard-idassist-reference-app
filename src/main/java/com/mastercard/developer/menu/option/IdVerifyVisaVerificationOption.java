package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.SourceVerificationExample;
import com.mastercard.developer.service.SourceVerificationService;
import com.mastercard.dis.mids.model.id.verification.PassportSourceVerificationRequestAttributes;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdVerifyVisaVerificationOption implements MenuOption {

    private final SourceVerificationService sourceVerificationService;

    @Override
    public void choose(Scanner scanner) {
        VisaDTO visa = promptForDataInput(scanner);
        PassportSourceVerificationRequestAttributes requestAttributes = SourceVerificationExample.createSourceVerificationPassportRequestAttributes();
        requestAttributes.setVisaMatched(true);
        requestAttributes.setVisaIssuingCountry(visa.getVerifyingCountry());
        sourceVerificationService.sourceVerificationPassport(visa.getIssuingCountry(), requestAttributes);
    }

    @Override
    public String getOptionName() {
        return "Visa Verification";
    }

    private VisaDTO promptForDataInput(Scanner scanner) {
        VisaDTO visa = new VisaDTO();
        log.info(" <--- Enter passport IssuingCountry 3 digit code and press ENTER: ");
        visa.setVerifyingCountry(scanner.nextLine());
        log.info(" <--- Enter visa verifying country 3 digit code and press ENTER: ");
        visa.setIssuingCountry(scanner.nextLine());
        return visa;
    }

    @Data
    static class VisaDTO {
        String verifyingCountry;
        String issuingCountry;
    }
}
