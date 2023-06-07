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

import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.service.DataExtractionsService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdDocumentDataExtractionApi;
import com.mastercard.dis.mids.model.id.verification.AccessToken;
import com.mastercard.dis.mids.model.id.verification.DocumentVerificationExtractedData;
import com.mastercard.dis.mids.model.id.verification.RetrieveAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataExtractionsServiceImpl implements DataExtractionsService {

    private final IdDocumentDataExtractionApi idDocumentDataExtractionOcrApi;
    private final ExceptionUtil exceptionUtil;

    @Value("${mastercard.client.encryption.enable:false}")
    private Boolean encryptionEnabled;

    @Override
    public AccessToken dataExtractionAccessToken(RetrieveAccessToken retrieveAccessToken) {
        try {
            return idDocumentDataExtractionOcrApi.retrieveDataExtractionAccessToken(retrieveAccessToken, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public AccessToken dataExtractionAccessTokenForWeb(RetrieveAccessToken retrieveAccessToken) {
        try {

            return idDocumentDataExtractionOcrApi.retrieveDataExtractionAccessToken(retrieveAccessToken, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public DocumentVerificationExtractedData extractScannedDocumentData(String scanId, String userConsent, String retrieveSelfie, String retrieveDocumentImages, String retrieveFacemap, String documentType, String userSelectedCountry) {
        try {
            return idDocumentDataExtractionOcrApi.extractScannedDocumentData(scanId, userConsent, retrieveSelfie, retrieveDocumentImages, retrieveFacemap, documentType, userSelectedCountry, encryptionEnabled);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}