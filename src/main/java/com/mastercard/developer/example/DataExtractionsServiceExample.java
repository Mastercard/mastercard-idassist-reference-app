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

import com.mastercard.dis.mids.model.id.verification.DocumentVerificationExtractedData;
import com.mastercard.dis.mids.model.id.verification.DocumentVerificationExtractedDataDocumentData;
import com.mastercard.dis.mids.model.id.verification.RetrieveAccessToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataExtractionsServiceExample {

    private static final String SDK_VERSION = "1.0.0";
    private static final String COUNTRY_CODE = "US";
    private static final String DOCUMENT_FRONT_IMAGE_ENCODED = "AAAB";
    private static final String DOCUMENT_BACK_IMAGE_ENCODED = "AAEA";
    private static final String SELFIE_IMAGE_ENCODED = "AAEB";
    private static final String FACE_MAP_ENCODED = "AQAA";
    private static final String CPF = "123.456.789-00";
    private static final String RG_NUMBER = "98.765.432-1";
    private static final String MOTHERS_NAME = "Jane Doe";
    private static final String FATHERS_NAME = "John Doe";
    private static final String TRANSACTION_ID = "fde54fb0-7505-11eb-9439-0242ac130002";
    private static final String STATUS_SUCCESS = "SUCCESS";
    private static final String DRIVING_LICENSE_ISSUING_AUTHORITY = "Ministry of Foreign Affair";
    private static final String DRIVING_LICENSE_ISSUING_PLACE = "Hobart";
    private static final String DRIVING_LICENSE_SUBDIVISION = "TAS";
    private static final String DRIVING_LICENSE_ADDRESS_CITY = "Hobart";
    private static final String DRIVING_LICENSE_ADDRESS_COUNTRY = "Australia";
    private static final String DRIVING_LICENSE_ADDRESS_LINE_1 = "22 Melville Street";
    private static final String DRIVING_LICENSE_ADDRESS_ZIP_CODE = "7000";
    private static final LocalDate DRIVING_LICENSE_EXPIRY_DATE = LocalDate.parse("2022-05-17");
    private static final LocalDate DRIVING_LICENSE_ISSUING_DATE = LocalDate.parse("2017-05-17");
    private static final String DRIVING_LICENSE_CARD_NUMBER = "TA8675309";
    private static final String DRIVING_LICENSE_DOCUMENT_NUMBER = "DL1000311";
    private static final String DOCUMENT_FIRST_NAME = "KARL";
    private static final String DOCUMENT_LAST_NAME = "JONES";
    private static final String DOCUMENT_GENDER = "M";
    private static final LocalDate DOCUMENT_DATE_OF_BIRTH = LocalDate.parse("1996-07-15");
    public static final String DOCUMENT_TYPE_DRIVING_LICENSE = "DRIVING_LICENSE";
    public static final String DOCUMENT_ISSUING_COUNTRY = "AUS";
    public static final String USER_SELECTED_COUNTRY = "USA";
    public static final String SCAN_ID = "5226539e-78e7-45ac-a924-072d1301c24c";
    public static final String USER_CONSENT = "ACCEPT";
    public static final String RETRIEVE_SELFIE = "false";
    public static final String RETRIEVE_DOCUMENT_IMAGES = "false";
    public static final String RETRIEVE_FACEMAP = "false";

    public static RetrieveAccessToken getAccessTokenExample() {
        return new RetrieveAccessToken().livenessType(RetrieveAccessToken.LivenessTypeEnum.GPA)
                .channelType(RetrieveAccessToken.ChannelTypeEnum.SDK)
                .sdkVersion(SDK_VERSION)
                .countryCode(COUNTRY_CODE);
    }

    public static RetrieveAccessToken getAccessTokenExampleForWeb() {
        return new RetrieveAccessToken().livenessType(RetrieveAccessToken.LivenessTypeEnum.LA)
                .channelType(RetrieveAccessToken.ChannelTypeEnum.WEB)
                .countryCode(COUNTRY_CODE)
                .successUrl("https://www.google.com")
                .errorUrl("https://www.google.com")
                .locale("en-US")
                .sdkVersion(SDK_VERSION);

    }

    public static DocumentVerificationExtractedData createDocumentVerificationExtractedData() {
        DocumentVerificationExtractedData documentVerificationExtractedData = new DocumentVerificationExtractedData();
        DocumentVerificationExtractedDataDocumentData documentData = new DocumentVerificationExtractedDataDocumentData();
        documentData.setDocumentType(DOCUMENT_TYPE_DRIVING_LICENSE);
        documentData.setDocumentNumber(DRIVING_LICENSE_DOCUMENT_NUMBER);
        documentData.setCardNumber(DRIVING_LICENSE_CARD_NUMBER);
        documentData.setDateOfBirth(DOCUMENT_DATE_OF_BIRTH.toString());
        documentData.setGender(DOCUMENT_GENDER);
        documentData.setFirstName(DOCUMENT_FIRST_NAME);
        documentData.setLastName(DOCUMENT_LAST_NAME);
        documentData.setExpiryDate(DRIVING_LICENSE_EXPIRY_DATE);
        documentData.setIssuingCountry(DOCUMENT_ISSUING_COUNTRY);
        documentData.setIssuingDate(DRIVING_LICENSE_ISSUING_DATE);
        documentData.setIssuingAuthority(DRIVING_LICENSE_ISSUING_AUTHORITY);
        documentData.setIssuingPlace(DRIVING_LICENSE_ISSUING_PLACE);
        documentData.setAddressLine1(DRIVING_LICENSE_ADDRESS_LINE_1);
        documentData.setAddressCity(DRIVING_LICENSE_ADDRESS_CITY);
        documentData.setAddressCountry(DRIVING_LICENSE_ADDRESS_COUNTRY);
        documentData.setAddressZipCode(DRIVING_LICENSE_ADDRESS_ZIP_CODE);
        documentData.setAddressSubdivision(DRIVING_LICENSE_SUBDIVISION);
        documentData.setDocumentImageFront(DOCUMENT_FRONT_IMAGE_ENCODED);
        documentData.setDocumentImageBack(DOCUMENT_BACK_IMAGE_ENCODED);
        documentData.setSelfie(SELFIE_IMAGE_ENCODED);
        documentData.setFacemap(FACE_MAP_ENCODED);
        documentData.setCpf(CPF);
        documentData.setRgNumber(RG_NUMBER);
        documentData.setMothersName(MOTHERS_NAME);
        documentData.setFathersName(FATHERS_NAME);
        documentVerificationExtractedData.setDocumentData(documentData);
        documentVerificationExtractedData.setStatus(STATUS_SUCCESS);
        documentVerificationExtractedData.setTransactionId(TRANSACTION_ID);
        return documentVerificationExtractedData;
    }
}