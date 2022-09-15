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

import com.mastercard.developer.model.id.verification.AccessToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenExample {

    private static final String JUMIO_API_DATACENTER = "US";
    private static final String SDK_TOKEN = "1a67635c-f384-11ea-adc1-0242ac120002";
    private static final String TRANSACTION_ID = "testTransactionId";

    public static AccessToken createAccessToken() {
        AccessToken accessToken = new AccessToken();
        accessToken.setApiDataCenter(JUMIO_API_DATACENTER);
        accessToken.setSdkToken(SDK_TOKEN);
        accessToken.setTransactionId(TRANSACTION_ID);
        return accessToken;
    }
}
