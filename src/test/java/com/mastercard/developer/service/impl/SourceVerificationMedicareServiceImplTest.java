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

import com.mastercard.developer.example.SourceVerificationMedicareExample;
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdDocumentDataSourceVerificationApi;
import com.mastercard.dis.mids.model.id.verification.Errors;
import com.mastercard.dis.mids.model.id.verification.MedicareCardSourceVerificationRequestAttributes;
import com.mastercard.dis.mids.model.id.verification.MedicareCardSourceVerificationResult;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static com.mastercard.developer.example.SourceVerificationExample.DOCUMENT_ISSUING_COUNTRY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceVerificationMedicareServiceImplTest {

    @Mock
    private ExceptionUtil exceptionUtil;

    @Mock
    private IdDocumentDataSourceVerificationApi idDocumentVerificationApi;

    @InjectMocks
    private MedicareSourceVerificationServiceImpl medicareDocumentVerificationService;

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void sourceVerificationMedicareCard_sourceVerificationApiNoException_returningVerifiedResult(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(medicareDocumentVerificationService, "encryptionEnabled", encrypt);

        MedicareCardSourceVerificationRequestAttributes sourceVerificationMedicareAttributes = SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes();
        MedicareCardSourceVerificationResult sourceVerificationMedicareExample = SourceVerificationMedicareExample.createSourceVerificationMedicareCardResponse();
        when(idDocumentVerificationApi.verifyMedicareCard(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationMedicareAttributes), eq(encrypt))).thenReturn(sourceVerificationMedicareExample);

        MedicareCardSourceVerificationResult medicareVerificationResult = medicareDocumentVerificationService.createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, sourceVerificationMedicareAttributes);
        assertEquals(sourceVerificationMedicareExample, medicareVerificationResult);

    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void sourceVerificationMedicareCard_sourceVerificationApiException_logAndConvertToServiceException(boolean encrypt) throws ApiException {
        ReflectionTestUtils.setField(medicareDocumentVerificationService, "encryptionEnabled", encrypt);

        MedicareCardSourceVerificationRequestAttributes sourceVerificationMedicareAttributes = SourceVerificationMedicareExample.createSourceVerificationMedicareCardRequestAttributes();
        ApiException apiException = new ApiException();
        ServiceException serviceException = new ServiceException(apiException, new Errors());
        doThrow(apiException).when(idDocumentVerificationApi).verifyMedicareCard(eq(DOCUMENT_ISSUING_COUNTRY), eq(sourceVerificationMedicareAttributes), eq(encrypt));
        when(exceptionUtil.logAndConvertToServiceException(eq(apiException))).thenReturn(serviceException);

        try {
            medicareDocumentVerificationService.createMedicareCardRequest(DOCUMENT_ISSUING_COUNTRY, sourceVerificationMedicareAttributes);
        } catch (ServiceException e) {
            assertEquals(serviceException, e);
        }
    }
}
