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
import com.mastercard.developer.model.id.verification.DriversLicenseSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.PassportSourceVerificationRequestAttributes;
import com.mastercard.developer.model.id.verification.SourceVerificationResult;
import com.mastercard.developer.service.SourceVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SourceVerificationServiceImpl implements SourceVerificationService {

    private final IdDocumentVerificationApi idDocumentVerificationApi;
    private final ExceptionUtil exceptionUtil;

    @Override
    public SourceVerificationResult sourceVerificationPassport(String issuingCountry, PassportSourceVerificationRequestAttributes sourceVerificationPassportAttributes) {
        try {
            return idDocumentVerificationApi.verifyPassport(issuingCountry, sourceVerificationPassportAttributes);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public SourceVerificationResult sourceVerificationDrivingLicense(String issuingCountry, DriversLicenseSourceVerificationRequestAttributes sourceVerificationDrivingLicenseAttributes) {
        try {
            return idDocumentVerificationApi.verifyDriversLicense(issuingCountry, sourceVerificationDrivingLicenseAttributes);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}
