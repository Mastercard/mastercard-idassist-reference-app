package com.mastercard.developer.menu;


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
import com.mastercard.developer.menu.option.SmsOtpFlowOption;
import com.mastercard.developer.menu.option.EmailOtpFlowOption;
import com.mastercard.developer.menu.option.MenuOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class MenuOptionProvider {

    private final ExitOption exitOption;
    private final DataExtractionOption dataExtractionOption;
    private final MedicalCareOption medicalCareOption;
    private final PassportSourceVerificationOption passportSourceVerificationOption;
    private final DrivingLicenseSourceVerificationOption drivingLicenseSourceVerificationOption;
    private final DataExtractionForWebOption dataExtractionForWebOption;
    private final UserIdentityRetrievalOption userIdentityRetrievalOption;
    private final UserIdentityVerificationOption userIdentityVerificationOption;
    private final TrustScoreOption trustScoreOption;
    private final DeviceAuthenticationOption deviceAuthenticationOption;
    private final DeviceVerificationOption deviceVerificationOption;
    private final IdVerifyVisaVerificationOption idVerifyVisaVerificationOption;
    private final SmsOtpFlowOption smsOtpFlowOption;
    private final EmailOtpFlowOption emailOtpFlowOption;

    public boolean isExitApplication() {
        return this.exitOption.isExitApplication();
    }

    public Map<String, MenuOption> getMenuOptions() {
        AtomicInteger index = new AtomicInteger();
        Map<String, MenuOption> options = new LinkedHashMap<>();
        List<MenuOption> menuOptions = Arrays.asList(
                exitOption,                                 //option 0
                dataExtractionOption,                       //option 1
                medicalCareOption,                          //option 2
                passportSourceVerificationOption,           //option 3
                drivingLicenseSourceVerificationOption,     //option 4
                dataExtractionForWebOption,                 //option 5
                userIdentityRetrievalOption,                //option 6
                userIdentityVerificationOption,             //option 7
                trustScoreOption,                           //option 8
                deviceAuthenticationOption,                 //option 9
                deviceVerificationOption,                   //option 10
                idVerifyVisaVerificationOption,             //option 11
                smsOtpFlowOption,                           //option 12
                emailOtpFlowOption                          //option 13
        );
        menuOptions.forEach(option -> {
            String indexAsString = String.valueOf(index.get());
            options.put(indexAsString, option);
            index.getAndIncrement();
        });
        return options;
    }
}
