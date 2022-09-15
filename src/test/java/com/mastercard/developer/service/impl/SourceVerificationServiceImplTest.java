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
import com.mastercard.developer.api.IdDocumentVerificationApi;
import com.mastercard.developer.example.SourceVerificationExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.model.Error;
import com.mastercard.developer.model.id.verification.DriversLicenseSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.PassportSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.SourceVerificationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mastercard.developer.example.SourceVerificationExample.DOCUMENT_ISSUING_COUNTRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceVerificationServiceImplTest {

    @Mock
    private IdDocumentVerificationApi idDocumentVerificationApi;

    @Mock
    private ExceptionUtil exceptionUtil;

    @InjectMocks
    private SourceVerificationServiceImpl sourceVerificationServiceImpl;

    @Test
    void sourceVerificationPassport_sourceVerificationApiNoException_returningVerifiedResult() throws ApiException {
        PassportSourceVerificationRequestAttributes sourceVerificationPassportAttributes = SourceVerificationExample.createSourceVerificationPassportRequestAttributes();
        SourceVerificationResult sourceVerificationResultExample = SourceVerificationExample.createSourceVerificationResult();
        when(idDocumentVerificationApi.verifyPassport(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationPassportAttributes))).thenReturn(sourceVerificationResultExample);

        SourceVerificationResult sourceVerificationResult = sourceVerificationServiceImpl.sourceVerificationPassport(DOCUMENT_ISSUING_COUNTRY, sourceVerificationPassportAttributes);

        assertEquals(sourceVerificationResultExample, sourceVerificationResult);
    }

    @Test
    void sourceVerificationPassport_sourceVerificationApiException_logAndConvertToServiceException() throws ApiException {
        PassportSourceVerificationRequestAttributes sourceVerificationPassportAttributes = SourceVerificationExample.createSourceVerificationPassportRequestAttributes();
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Error());
        doThrow(apiException).when(idDocumentVerificationApi).verifyPassport(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationPassportAttributes));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            sourceVerificationServiceImpl.sourceVerificationPassport(DOCUMENT_ISSUING_COUNTRY, sourceVerificationPassportAttributes);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }

    @Test
    void sourceVerificationDrivingLicense_sourceVerificationApiNoException_returningVerifiedResult() throws ApiException {
        DriversLicenseSourceVerificationRequestAttributes sourceVerificationDrivingLicenseAttributes = SourceVerificationExample.createSourceVerificationDrivingLicenseRequestAttributes();
        SourceVerificationResult sourceVerificationResultExample = SourceVerificationExample.createSourceVerificationResult();
        when(idDocumentVerificationApi.verifyDriversLicense(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationDrivingLicenseAttributes))).thenReturn(sourceVerificationResultExample);

        SourceVerificationResult sourceVerificationResult = sourceVerificationServiceImpl.sourceVerificationDrivingLicense(DOCUMENT_ISSUING_COUNTRY, sourceVerificationDrivingLicenseAttributes);

        assertEquals(sourceVerificationResultExample, sourceVerificationResult);
    }

    @Test
    void sourceVerificationDrivingLicense_sourceVerificationApiException_logAndConvertToServiceException() throws ApiException {
        DriversLicenseSourceVerificationRequestAttributes sourceVerificationDrivingLicenseAttributes = SourceVerificationExample.createSourceVerificationDrivingLicenseRequestAttributes();
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Error());
        doThrow(apiException).when(idDocumentVerificationApi).verifyDriversLicense(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationDrivingLicenseAttributes));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            sourceVerificationServiceImpl.sourceVerificationDrivingLicense(DOCUMENT_ISSUING_COUNTRY, sourceVerificationDrivingLicenseAttributes);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }
}
