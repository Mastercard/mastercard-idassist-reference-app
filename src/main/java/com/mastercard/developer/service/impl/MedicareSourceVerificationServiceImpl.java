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
import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.model.id.verification.MedicareCardSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.MedicareCardSourceVerificationResult;
import com.mastercard.developer.service.MedicareSourceVerificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MedicareSourceVerificationServiceImpl implements MedicareSourceVerificationService {

    private final IdDocumentVerificationApi idDocumentVerificationApi;
    private final ExceptionUtil exceptionUtil;

    @Override
    public MedicareCardSourceVerificationResult createMedicareCardRequest(String issuingCountry, MedicareCardSourceVerificationRequestAttributes medicareCardRequestAttributes) {
        try {
            return idDocumentVerificationApi.verifyMedicareCard(issuingCountry, medicareCardRequestAttributes);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}