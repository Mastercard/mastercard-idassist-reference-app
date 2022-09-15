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

package com.mastercard.developer.example;

import com.mastercard.developer.model.id.verification.DriversLicenseSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.PassportSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.SourceVerificationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SourceVerificationExample {

    public static final String DOCUMENT_ISSUING_COUNTRY = "AUS";
    private static final String DRIVING_LICENSE_DOCUMENT_NUMBER = "DL1000311";
    private static final String DOCUMENT_FIRST_NAME = "KARL";
    private static final String DOCUMENT_LAST_NAME = "JONES";
    private static final String DOCUMENT_GENDER = "M";
    private static final LocalDate DOCUMENT_DATE_OF_BIRTH = LocalDate.parse("1996-07-15");
    private static final String DRIVING_LICENSE_STATE_CODE = "TAS";
    private static final String PASSPORT_DOCUMENT_NUMBER = "P1003110";
    private static final String VERIFICATION_RESULT_CODE_VERIFIED = "DOCUMENT_VERIFIED";

    public static PassportSourceVerificationRequestAttributes createSourceVerificationPassportRequestAttributes() {
        PassportSourceVerificationRequestAttributes sourceVerificationPassportAttributes = new PassportSourceVerificationRequestAttributes();
        sourceVerificationPassportAttributes.setDocumentNumber(PASSPORT_DOCUMENT_NUMBER);
        sourceVerificationPassportAttributes.setUserConsent(PassportSourceVerificationRequestAttributes.UserConsentEnum.ACCEPT);
        sourceVerificationPassportAttributes.setFirstName(DOCUMENT_FIRST_NAME);
        sourceVerificationPassportAttributes.setLastName(DOCUMENT_LAST_NAME);
        sourceVerificationPassportAttributes.setDateOfBirth(DOCUMENT_DATE_OF_BIRTH);
        sourceVerificationPassportAttributes.setGender(DOCUMENT_GENDER);
        return sourceVerificationPassportAttributes;
    }

    public static SourceVerificationResult createSourceVerificationResult() {
        SourceVerificationResult sourceVerificationResult = new SourceVerificationResult();
        sourceVerificationResult.setVerificationResult(VERIFICATION_RESULT_CODE_VERIFIED);
        return sourceVerificationResult;
    }

    public static DriversLicenseSourceVerificationRequestAttributes createSourceVerificationDrivingLicenseRequestAttributes() {
        DriversLicenseSourceVerificationRequestAttributes sourceVerificationDrivingLicenseAttributes = new DriversLicenseSourceVerificationRequestAttributes();
        sourceVerificationDrivingLicenseAttributes.setDocumentNumber(DRIVING_LICENSE_DOCUMENT_NUMBER);
        sourceVerificationDrivingLicenseAttributes.setUserConsent(DriversLicenseSourceVerificationRequestAttributes.UserConsentEnum.ACCEPT);
        sourceVerificationDrivingLicenseAttributes.setFirstName(DOCUMENT_FIRST_NAME);
        sourceVerificationDrivingLicenseAttributes.setLastName(DOCUMENT_LAST_NAME);
        sourceVerificationDrivingLicenseAttributes.setDateOfBirth(DOCUMENT_DATE_OF_BIRTH);
        sourceVerificationDrivingLicenseAttributes.setStateCode(DRIVING_LICENSE_STATE_CODE);
        return sourceVerificationDrivingLicenseAttributes;
    }
}