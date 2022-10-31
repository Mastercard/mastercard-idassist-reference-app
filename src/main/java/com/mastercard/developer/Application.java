/*
 Copyright (c) 2021 Mastercard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.mastercard.developer;


import com.mastercard.developer.component.MIDSReference;
import com.mastercard.developer.constants.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    private boolean exit = false;
    private final MIDSReference midsReference;
    private Scanner scanner;
    private static final String ERROR = "Error : ";
    private String issuingCountry = "AUS";
    private String visaIssuingCountry ="AUS";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.exit(0);
    }

    public Application(MIDSReference midsReference) {
        this.midsReference = midsReference;
    }

    @Override
    public void run(String... args) {
        scanner = new Scanner(System.in, "UTF-8");

        while (!exit) {
            showMenu();
            String option = scanner.nextLine();

            //special case handling
            if ( "16".equalsIgnoreCase(option)){
                log.info(" <--- Enter passport IssuingCountry 3 digit code and press ENTER: ");
                issuingCountry = scanner.nextLine();
                log.info(" <--- Enter visa verifying country 3 digit code and press ENTER: ");
                visaIssuingCountry = scanner.nextLine();
            }
            handleOption(option);
            pressAnyKey();
        }
        System.exit(0);
    }


    void showMenu() {
        log.info(" <--- Welcome to ID Reference APP --->");
        for (Map.Entry<String, String> entry : new Menu().get().entrySet()) {
            log.info(entry.getValue());
        }
        log.info(" ---> Type your option and press ENTER: ");
    }

    void handleOption(String option) {
        log.info("Your option : " + option);
        switch (option) {
            case "1":
                performIdAssistUserIdentityRetrieval();
                break;
            case "2":
                performIdAssistUserIdentityVerification();
                break;
            case "3":
                performIdAssistTrustScore();
                break;
            case "4":
                performIdAssistSmsOtpFlow();
                break;
            case "5":
                performIdAssistDeviceAuthentication();
                break;
            case "6":
                performDataExtraction();
                break;
            case "7":
                performMedicalCare();
                break;
            case "8":
                performPassportSourceVerification();
                break;
            case "9":
                performDrivingLicenseSourceVerification();
                break;
            case "10":
                performDataExtractionForWeb();
                break;
            case "11":
                performIdVerifyUserIdentityRetrieval();
                break;
            case "12":
                performIdVerifyUserIdentityVerification();
                break;
            case "13":
                performIdVerifyTrustScore();
                break;
            case "14":
                performIdVerifyDeviceAuthentication();
                break;
            case "15":
                performIdVerifyDeviceAuthenticationVerification();
                break;
            case "16":
                performVisaSourceVerification();
                break;
            case "17":
                this.exit = true;
                break;
            default:
                log.info("Invalid option!");
        }
    }

    void performIdAssistUserIdentityRetrieval() {
        try {
            log.info("<<--- ID Assist User Identity Retrieval Started --->>");
            midsReference.performIdAssistUserIdentityRetrieval();
            log.info("<<--- ID Assist User Identity Retrieval Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Assist User Identity Retrieval Failed Ended --->>");
        }
    }

    void performIdAssistUserIdentityVerification() {
        try {
            log.info("<<--- ID Assist User Identity Verification Started --->>");
            midsReference.performIdAssistUserIdentityVerification();
            log.info("<<--- ID Assist User Identity Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Assist User Identity Verification Failed Ended --->>");
        }
    }

    void performIdAssistSmsOtpFlow() {
        try {
            log.info("<<--- ID Assist SMS OTP Started --->>");
            midsReference.performIdAssistSmsOtpFlow();
            log.info("<<--- ID Assist SMS OTP Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Assist SMS OTP Failed Ended --->>");
        }
    }

    void performIdAssistTrustScore() {
        try {
            log.info("<<--- ID Assist TrustScore Started --->>");
            midsReference.performIdAssistTrustScore();
            log.info("<<--- ID Assist TrustScore Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Assist TrustScore Failed Ended --->>");
        }
    }

    void performIdAssistDeviceAuthentication() {
        try {
            log.info("<<--- ID Assist Device Authentication Started --->>");
            midsReference.performIdAssistDeviceAuthentication();
            log.info("<<--- ID Assist Device Authentication Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Assist Device Authentication Failed Ended --->>");
        }
    }

    void performMedicalCare() {
        try {
            log.info("<<--- Medical Care Started --->>");
            midsReference.performMedicalCare();
            log.info("<<--- Medical Care Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Medical Care Failed Ended --->>");
        }
    }

    void performDataExtraction() {
        try {
            log.info("<<--- Data Extraction Started --->>");
            midsReference.performDataExtraction();
            log.info("<<--- Data Extraction Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Data Extraction Failed Ended --->>");
        }
    }

    void performDataExtractionForWeb() {
        try {
            log.info("<<--- Data Extraction Started --->>");
            midsReference.performDataExtractionForWeb();
            log.info("<<--- Data Extraction Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Data Extraction Failed Ended --->>");
        }
    }

    void performDrivingLicenseSourceVerification() {
        try {
            log.info("<<--- Driving License Source Verification Started --->>");
            midsReference.performDrivingLicenseSourceVerification();
            log.info("<<--- Driving License Source Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Driving License Source Verification Failed Ended --->>");
        }
    }

    void performPassportSourceVerification() {
        try {
            log.info("<<--- Passport Source Verification Started --->>");
            midsReference.performPassportSourceVerification();
            log.info("<<--- Passport Source Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Passport Source Verification Failed Ended --->>");
        }
    }

    void performIdVerifyUserIdentityRetrieval() {
        try {
            log.info("<<--- ID Verify User Identity Retrieval Started --->>");
            midsReference.performIdVerifyUserIdentityRetrieval();
            log.info("<<--- ID Verify User Identity Retrieval Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Verify User Identity Retrieval Failed Ended --->>");
        }
    }

    void performIdVerifyUserIdentityVerification() {
        try {
            log.info("<<--- ID Verify User Identity Verification Started --->>");
            midsReference.performIdVerifyUserIdentityVerification();
            log.info("<<--- ID Verify User Identity Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Verify User Identity Verification Failed Ended --->>");
        }
    }

    void performIdVerifyTrustScore() {
        try {
            log.info("<<--- ID Verify TrustScore Started --->>");
            midsReference.performIdVerifyTrustScore();
            log.info("<<--- ID Verify TrustScore Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Verify TrustScore Failed Ended --->>");
        }
    }

    void performIdVerifyDeviceAuthentication() {
        try {
            log.info("<<--- ID Verify Device Authentication Started --->>");
            midsReference.performIdVerifyDeviceAuthentication();
            log.info("<<--- ID Verify Device Authentication Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Verify Device Authentication Failed Ended --->>");
        }
    }

    void performIdVerifyDeviceAuthenticationVerification() {
        try {
            log.info("<<--- ID Verify Device Authentication Verification Started --->>");
            midsReference.performIdVerifyDeviceAuthenticationVerification();
            log.info("<<--- ID Verify Device Authentication Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- ID Verify Device Authentication Verification Failed Ended --->>");
        }
    }

    void performVisaSourceVerification() {
        try {
            log.info("<<--- Visa Source Verification Started --->>");
            midsReference.performVisaSourceVerification(issuingCountry, visaIssuingCountry);
            log.info("<<--- Visa Source Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<---Visa Source Verification Failed Ended --->>");
        }
    }


    void pressAnyKey() {
        if (!exit) {
            log.info("Press ENTER to continue...");
            scanner.nextLine();
        }
    }
}