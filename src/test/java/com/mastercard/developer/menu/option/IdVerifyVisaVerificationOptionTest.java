package com.mastercard.developer.menu.option;

import com.mastercard.developer.service.SourceVerificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IdVerifyVisaVerificationOptionTest {
    @InjectMocks
    IdVerifyVisaVerificationOption idVerifyVisaVerificationOption;

    @Mock
    SourceVerificationService sourceVerificationServiceMock;

    @Test
    void visaSourceVerification_visaVerification_successfulResponse() {
        String input = "CAN\nAUS";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try(Scanner scanner = new Scanner(System.in, "UTF-8")) {
            idVerifyVisaVerificationOption.choose(scanner);
            verify(sourceVerificationServiceMock, times(1)).sourceVerificationPassport(any(), any());
        }
    }

    @Test
    void visaSourceVerification_getOptionName_resultMatches() {
        assertEquals("Visa Verification", idVerifyVisaVerificationOption.getOptionName());
    }
}
