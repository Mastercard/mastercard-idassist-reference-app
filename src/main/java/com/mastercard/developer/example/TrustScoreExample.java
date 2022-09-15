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

import com.mastercard.developer.model.TrustScoreUserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TrustScoreExample {

    public static TrustScoreUserInfo getTrustScoreUserInfo(){
        TrustScoreUserInfo request = new TrustScoreUserInfo();
        request.setCountryCode("US");
        request.setPhoneNumber("12001062100");
        request.setOptedInConsentStatus(true);
        request.setPerformEligibilityCheck(true);

        return request;
    }
}