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

package com.mastercard.developer.service.impl;

import com.mastercard.developer.example.AccessTokenExample;
import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdDocumentDataExtractionApi;
import com.mastercard.dis.mids.model.id.verification.AccessToken;
import com.mastercard.dis.mids.model.id.verification.DocumentVerificationExtractedData;
import com.mastercard.dis.mids.model.id.verification.Errors;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mastercard.developer.example.DataExtractionsServiceExample.SCAN_ID;
import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_CONSENT;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_SELFIE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_DOCUMENT_IMAGES;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_FACEMAP;
import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_TYPE_DRIVING_LICENSE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_ISSUING_COUNTRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataExtractionsServiceImplTest {

    @Mock
    private IdDocumentDataExtractionApi idDocumentDataExtractionOcrApi;
    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private DataExtractionsServiceImpl dataExtractionsServiceImpl;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void retrieveDataExtractionToken_accessTokenApiNoException_returningAccessToken(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        AccessToken accessTokenExample = AccessTokenExample.createAccessToken();
        when(idDocumentDataExtractionOcrApi.retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExample()), eq(encrypt))).thenReturn(accessTokenExample);

        AccessToken accessToken = dataExtractionsServiceImpl.dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());

        assertEquals(accessTokenExample, accessToken);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void retrieveDataExtractionTokenForWeb_returningAccessToken(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        AccessToken accessTokenExample = AccessTokenExample.createAccessToken();
        when(idDocumentDataExtractionOcrApi.retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExampleForWeb()), eq(encrypt))).thenReturn(accessTokenExample);

        AccessToken accessToken = dataExtractionsServiceImpl.dataExtractionAccessTokenForWeb(DataExtractionsServiceExample.getAccessTokenExampleForWeb());

        assertEquals(accessTokenExample, accessToken);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void retrieveDataExtractionTokenForWeb_accessTokenApiException_logAndConvertToServiceException(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Errors());
        doThrow(apiException).when(idDocumentDataExtractionOcrApi).retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExampleForWeb()), eq(encrypt));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            dataExtractionsServiceImpl.dataExtractionAccessTokenForWeb(DataExtractionsServiceExample.getAccessTokenExampleForWeb());
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void retrieveDataExtractionToken_accessTokenApiException_logAndConvertToServiceException(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Errors());
        doThrow(apiException).when(idDocumentDataExtractionOcrApi).retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExample()), eq(encrypt));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            dataExtractionsServiceImpl.dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void extractScannedDocumentData_dataExtractionsApiNoException_returningAccessToken(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        DocumentVerificationExtractedData documentVerificationExtractedDataExample = DataExtractionsServiceExample.createDocumentVerificationExtractedData();
        when(idDocumentDataExtractionOcrApi.extractScannedDocumentData(eq(SCAN_ID), eq(USER_CONSENT), eq(RETRIEVE_SELFIE), eq(RETRIEVE_DOCUMENT_IMAGES), eq(RETRIEVE_FACEMAP), eq(DOCUMENT_TYPE_DRIVING_LICENSE), eq(DOCUMENT_ISSUING_COUNTRY), eq(encrypt))).thenReturn(documentVerificationExtractedDataExample);
        DocumentVerificationExtractedData documentVerificationExtractedData = dataExtractionsServiceImpl.extractScannedDocumentData(DataExtractionsServiceExample.SCAN_ID, "ACCEPT", "false", "false", "false", DOCUMENT_TYPE_DRIVING_LICENSE, DOCUMENT_ISSUING_COUNTRY);
        assertEquals(documentVerificationExtractedDataExample, documentVerificationExtractedData);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void extractScannedDocumentData_dataExtractionsApiException_logAndConvertToServiceException(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(dataExtractionsServiceImpl, "encryptionEnabled", encrypt);

        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Errors());
        doThrow(apiException).when(idDocumentDataExtractionOcrApi).extractScannedDocumentData(eq(SCAN_ID), eq(USER_CONSENT), eq(RETRIEVE_SELFIE), eq(RETRIEVE_DOCUMENT_IMAGES), eq(RETRIEVE_FACEMAP), eq(DOCUMENT_TYPE_DRIVING_LICENSE), eq(DOCUMENT_ISSUING_COUNTRY), eq(encrypt));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);
        try {
            dataExtractionsServiceImpl.extractScannedDocumentData(SCAN_ID, USER_CONSENT, RETRIEVE_SELFIE, RETRIEVE_DOCUMENT_IMAGES, RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, DOCUMENT_ISSUING_COUNTRY);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }
}