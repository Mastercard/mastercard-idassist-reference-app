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

import com.mastercard.dis.mids.model.id.verification.MedicareCardSourceVerificationRequestAttributes;
import com.mastercard.dis.mids.model.id.verification.MedicareCardSourceVerificationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SourceVerificationMedicareExample {

    private static final String NAME_LINE1 = "Shane Warne";
    private static final String MEDICARE_CARD_NO = "1234567892";
    private static final String COUNTRY_CODE = "AUS";
    private static final String EXPIRY_DATE = "2023-12";
    private static final String BIRTH_DATE = "1971-01-01";
    private static final String INDIVIDUAL_REFERENCE_NO = "1";
    private static final String VERIFICATION_RESULT_CODE_VERIFIED = "DOCUMENT_VERIFIED";

    public static MedicareCardSourceVerificationRequestAttributes createSourceVerificationMedicareCardRequestAttributes() {
        MedicareCardSourceVerificationRequestAttributes medicareCardRequestAttributes = new MedicareCardSourceVerificationRequestAttributes();
        medicareCardRequestAttributes.setUserConsent(MedicareCardSourceVerificationRequestAttributes.UserConsentEnum.ACCEPT);
        medicareCardRequestAttributes.setCardColor(MedicareCardSourceVerificationRequestAttributes.CardColorEnum.GREEN);
        medicareCardRequestAttributes.setNameLine1(NAME_LINE1);
        medicareCardRequestAttributes.setMedicareCardNo(MEDICARE_CARD_NO);
        medicareCardRequestAttributes.countryCode(COUNTRY_CODE);
        medicareCardRequestAttributes.setExpiryDate(EXPIRY_DATE);
        medicareCardRequestAttributes.setBirthDate(LocalDate.parse(BIRTH_DATE));
        medicareCardRequestAttributes.setIndividualReferenceNo(INDIVIDUAL_REFERENCE_NO);
        return medicareCardRequestAttributes;
    }

    public static MedicareCardSourceVerificationResult createSourceVerificationMedicareCardResponse() {
        MedicareCardSourceVerificationResult medicareCardVerificationResult = new MedicareCardSourceVerificationResult();
        medicareCardVerificationResult.setVerificationResult(VERIFICATION_RESULT_CODE_VERIFIED);
        return medicareCardVerificationResult;
    }
}
