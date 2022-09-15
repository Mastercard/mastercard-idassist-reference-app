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
import com.mastercard.developer.example.SourceVerificationMedicareExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.model.Error;
import com.mastercard.developer.model.id.verification.MedicareCardSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.MedicareCardSourceVerificationResult;
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
public class SourceVerificationMedicareServiceImplTest {

    @Mock
    private ExceptionUtil exceptionUtil;

    @Mock
    private IdDocumentVerificationApi idDocumentVerificationApi;

    @InjectMocks
    private MedicareSourceVerificationServiceImpl medicareDocumentVerificationService;

    @Test
    void sourceVerificationMedicareCard_sourceVerificationApiNoException_returningVerifiedResult() throws ApiException {
        MedicareCardSourceVerificationRequestAttributes sourceVerificationMedicareAttributes = SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes();
        MedicareCardSourceVerificationResult sourceVerificationMedicareExample = SourceVerificationMedicareExample.createSourceVerificationMedicareCardResponse();
        when(idDocumentVerificationApi.verifyMedicareCard(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationMedicareAttributes))).thenReturn(sourceVerificationMedicareExample);

        MedicareCardSourceVerificationResult medicareVerificationResult = medicareDocumentVerificationService.createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, sourceVerificationMedicareAttributes);
        assertEquals(sourceVerificationMedicareExample, medicareVerificationResult);

    }

    @Test
    void sourceVerificationMedicareCard_sourceVerificationApiException_logAndConvertToServiceException() throws ApiException {
        MedicareCardSourceVerificationRequestAttributes sourceVerificationMedicareAttributes = SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes();
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Error());
        doThrow(apiException).when(idDocumentVerificationApi).verifyMedicareCard(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationMedicareAttributes));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            medicareDocumentVerificationService.createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, sourceVerificationMedicareAttributes);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }

}
