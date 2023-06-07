package com.mastercard.developer;

import com.mastercard.developer.menu.MenuOptionProvider;
import com.mastercard.developer.menu.option.ExitOption;
import com.mastercard.developer.menu.option.DataExtractionOption;
import com.mastercard.developer.menu.option.MedicalCareOption;
import com.mastercard.developer.menu.option.PassportSourceVerificationOption;
import com.mastercard.developer.menu.option.DrivingLicenseSourceVerificationOption;
import com.mastercard.developer.menu.option.DataExtractionForWebOption;
import com.mastercard.developer.menu.option.UserIdentityRetrievalOption;
import com.mastercard.developer.menu.option.UserIdentityVerificationOption;
import com.mastercard.developer.menu.option.TrustScoreOption;
import com.mastercard.developer.menu.option.DeviceAuthenticationOption;
import com.mastercard.developer.menu.option.DeviceVerificationOption;
import com.mastercard.developer.menu.option.IdVerifyVisaVerificationOption;
import com.mastercard.developer.menu.option.MenuOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainApplicationTest {

    @Mock
    ExitOption exitOption;
    @Mock
    DataExtractionOption dataExtractionOption;
    @Mock
    MedicalCareOption medicalCareOption;
    @Mock
    PassportSourceVerificationOption passportSourceVerificationOption;
    @Mock
    DrivingLicenseSourceVerificationOption drivingLicenseSourceVerificationOption;
    @Mock
    DataExtractionForWebOption dataExtractionForWebOption;
    @Mock
    UserIdentityRetrievalOption userIdentityRetrievalOption;
    @Mock
    UserIdentityVerificationOption userIdentityVerificationOption;
    @Mock
    TrustScoreOption trustScoreOption;
    @Mock
    DeviceAuthenticationOption deviceAuthenticationOption;
    @Mock
    DeviceVerificationOption deviceVerificationOption;
    @Mock
    IdVerifyVisaVerificationOption idVerifyVisaVerificationOption;

    @InjectMocks
    MenuOptionProvider menuOptionProvider;

    @BeforeAll
    static void setup() {

    }

    private static final Map<String, String> menuMapTest = new LinkedHashMap() {{
        put("0", "0) Exit");
        put("1", "1) Document Data Extraction");
        put("2", "2) Medicare Card Verification");
        put("3", "3) Passport Verification");
        put("4", "4) Driving License Verification");
        put("5", "5) Document Data Extraction For Web");
        put("6", "6) User Identity Retrieval");
        put("7", "7) User Identity Verification");
        put("8", "8) Trust Score");
        put("9", "9) Device Authentication");
        put("10", "10) Device Authentication Verification");
        put("11", "11) Visa Verification");
    }};

    @Test
    void consoleMenu_runAndCheckingValues_works() {
        when(exitOption.getOptionName()).thenReturn("0) Exit");
        when(dataExtractionOption.getOptionName()).thenReturn("1) Document Data Extraction");
        when(medicalCareOption.getOptionName()).thenReturn("2) Medicare Card Verification");
        when(passportSourceVerificationOption.getOptionName()).thenReturn("3) Passport Verification");
        when(drivingLicenseSourceVerificationOption.getOptionName()).thenReturn("4) Driving License Verification");
        when(dataExtractionForWebOption.getOptionName()).thenReturn("5) Document Data Extraction For Web");
        when(userIdentityRetrievalOption.getOptionName()).thenReturn("6) User Identity Retrieval");
        when(userIdentityVerificationOption.getOptionName()).thenReturn("7) User Identity Verification");
        when(trustScoreOption.getOptionName()).thenReturn("8) Trust Score");
        when(deviceAuthenticationOption.getOptionName()).thenReturn("9) Device Authentication");
        when(deviceVerificationOption.getOptionName()).thenReturn("10) Device Authentication Verification");
        when(idVerifyVisaVerificationOption.getOptionName()).thenReturn("11) Visa Verification");

        Map<String, MenuOption> menu = menuOptionProvider.getMenuOptions();
        for (Map.Entry<String, String> entry : menuMapTest.entrySet()) {
            String valueMenu = menu.get(entry.getKey()).getOptionName();
            Assertions.assertEquals(valueMenu, entry.getValue());
        }
    }
}
