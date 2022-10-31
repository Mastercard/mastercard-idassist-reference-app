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

package com.mastercard.developer.service;

import com.mastercard.dis.mids.model.id.verification.AccessToken;
import com.mastercard.dis.mids.model.id.verification.DocumentVerificationExtractedData;
import com.mastercard.dis.mids.model.id.verification.RetrieveAccessToken;

public interface DataExtractionsService {

    AccessToken dataExtractionAccessToken(RetrieveAccessToken retrieveAccessToken);

    AccessToken dataExtractionAccessTokenForWeb(RetrieveAccessToken retrieveAccessToken);

    DocumentVerificationExtractedData extractScannedDocumentData(String scanId, String userConsent, String retrieveSelfie, String retrieveDocumentImages, String retrieveFacemap, String documentType, String userSelectedCountry);
}