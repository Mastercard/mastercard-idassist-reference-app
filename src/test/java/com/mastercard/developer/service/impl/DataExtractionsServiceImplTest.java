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

import com.mastercard.developer.ApiException;
import com.mastercard.developer.api.IdDocumentDataExtractionOcrApi;
import com.mastercard.developer.example.AccessTokenExample;
import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.model.Error;
import com.mastercard.developer.model.id.verification.AccessToken;
import com.mastercard.developer.model.id.verification.DocumentVerificationExtractedData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_ISSUING_COUNTRY;
import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_TYPE_DRIVING_LICENSE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_DOCUMENT_IMAGES;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_FACEMAP;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_SELFIE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.SCAN_ID;
import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_CONSENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataExtractionsServiceImplTest {

    @Mock
    private IdDocumentDataExtractionOcrApi idDocumentDataExtractionOcrApi;
    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private DataExtractionsServiceImpl dataExtractionsServiceImpl;

    @Test
    void retrieveDataExtractionToken_accessTokenApiNoException_returningAccessToken() throws ApiException {
        AccessToken accessTokenExample = AccessTokenExample.createAccessToken();
        when(idDocumentDataExtractionOcrApi.retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExample()))).thenReturn(accessTokenExample);

        AccessToken accessToken = dataExtractionsServiceImpl.dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());

        assertEquals(accessTokenExample, accessToken);
    }

    @Test
    void retrieveDataExtractionToken_accessTokenApiException_logAndConvertToServiceException() throws ApiException {
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Error());
        doThrow(apiException).when(idDocumentDataExtractionOcrApi).retrieveDataExtractionAccessToken(eq(DataExtractionsServiceExample.getAccessTokenExample()));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            dataExtractionsServiceImpl.dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }

    @Test
    void extractScannedDocumentData_dataExtractionsApiNoException_returningAccessToken() throws ApiException {
        DocumentVerificationExtractedData documentVerificationExtractedDataExample = DataExtractionsServiceExample.createDocumentVerificationExtractedData();
        when(idDocumentDataExtractionOcrApi.extractScannedDocumentData(eq(SCAN_ID), eq(USER_CONSENT), eq(RETRIEVE_SELFIE), eq(RETRIEVE_DOCUMENT_IMAGES), eq(RETRIEVE_FACEMAP), eq(DOCUMENT_TYPE_DRIVING_LICENSE), eq(DOCUMENT_ISSUING_COUNTRY))).thenReturn(documentVerificationExtractedDataExample);
        DocumentVerificationExtractedData documentVerificationExtractedData = dataExtractionsServiceImpl.extractScannedDocumentData(DataExtractionsServiceExample.SCAN_ID, "ACCEPT", "false", "false", "false", DOCUMENT_TYPE_DRIVING_LICENSE, DOCUMENT_ISSUING_COUNTRY);
        assertEquals(documentVerificationExtractedDataExample, documentVerificationExtractedData);
    }

    @Test
    void extractScannedDocumentData_dataExtractionsApiException_logAndConvertToServiceException() throws ApiException {
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Error());
        doThrow(apiException).when(idDocumentDataExtractionOcrApi).extractScannedDocumentData(eq(SCAN_ID), eq(USER_CONSENT), eq(RETRIEVE_SELFIE), eq(RETRIEVE_DOCUMENT_IMAGES), eq(RETRIEVE_FACEMAP), eq(DOCUMENT_TYPE_DRIVING_LICENSE), eq(DOCUMENT_ISSUING_COUNTRY));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);
        try {
            dataExtractionsServiceImpl.extractScannedDocumentData(SCAN_ID,USER_CONSENT,RETRIEVE_SELFIE,RETRIEVE_DOCUMENT_IMAGES,RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, DOCUMENT_ISSUING_COUNTRY);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }
}
