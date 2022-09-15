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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.exit(0);
    }

    public Application(MIDSReference midsReference) {
        this.midsReference = midsReference;
    }

    @Override
    public void run(String... args) throws Exception {
        scanner = new Scanner(System.in, "UTF-8");

        while (!exit) {
            showMenu();
            handleOption(scanner.nextLine());
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
                performUserIdentityRetrieval();
                break;
            case "2":
                performUserIdentityVerification();
                break;
            case "3":
                performTrustScore();
                break;
            case "4":
                performSmsOtpFlow();
                break;
            case "5":
                performDeviceAuthentication();
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
                this.exit = true;
                break;
            default:
                log.info("Invalid option!");
        }
    }

    void performUserIdentityRetrieval() {
        try {
            log.info("<<--- User Identity Retrieval Started --->>");
            midsReference.performUserIdentityRetrieval();
            log.info("<<--- User Identity Retrieval Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- User Identity Retrieval Failed Ended --->>");
        }
    }

    void performUserIdentityVerification() {
        try {
            log.info("<<--- User Identity Verification Started --->>");
            midsReference.performUserIdentityVerification();
            log.info("<<--- User Identity Verification Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- User Identity Verification Failed Ended --->>");
        }
    }

    void performSmsOtpFlow() {
        try {
            log.info("<<--- SMS OTP Started --->>");
            midsReference.performSmsOtpFlow();
            log.info("<<--- SMS OTP Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- SMS OTP Failed Ended --->>");
        }
    }

    void performTrustScore() {
        try {
            log.info("<<--- TrustScore Started --->>");
            midsReference.performTrustScore();
            log.info("<<--- TrustScore Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- TrustScore Failed Ended --->>");
        }
    }

    void performDeviceAuthentication() {
        try {
            log.info("<<--- Device Authentication Started --->>");
            midsReference.performDeviceAuthentication();
            log.info("<<--- Device Authentication Successfully Ended --->>");
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info("<<--- Device Authentication Failed Ended --->>");
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

    void pressAnyKey() {
        if (!exit) {
            log.info("Press ENTER to continue...");
            scanner.nextLine();
        }
    }
}