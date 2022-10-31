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

import com.mastercard.dis.mids.model.IdentityPrefill;
import com.mastercard.dis.mids.model.IdentityVerificationUserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserExample {

    public static IdentityPrefill getIdentity() {
        IdentityPrefill identityPrefill = new IdentityPrefill();
        List<String> list = Arrays.asList("firstName", "lastName", "dob", "nationalId", "emailAddress");
        identityPrefill.setCountryCode("US");
        identityPrefill.setPhoneNumber("1112223333");
        identityPrefill.setOptedInConsentStatus(true);
        identityPrefill.setScopedFields(list);
        identityPrefill.setLast4ssn("2142");

        return identityPrefill;
    }

    public static IdentityVerificationUserInfo getIdentityVerificationUserInfo() {
        IdentityVerificationUserInfo IdentityVerificationUserInfo = new IdentityVerificationUserInfo();

        IdentityVerificationUserInfo.setPhoneNumber("13143039289");
        IdentityVerificationUserInfo.setFirstName("John");
        IdentityVerificationUserInfo.setLastName("Smith");
        IdentityVerificationUserInfo.setOptedInConsentStatus(true);
        IdentityVerificationUserInfo.setCountryCode("US");
        IdentityVerificationUserInfo.setAddress("11 Faux Street");
        IdentityVerificationUserInfo.setExtendedAddress("Apt. 202");
        IdentityVerificationUserInfo.setCity("New York");
        IdentityVerificationUserInfo.setRegion("NY");
        IdentityVerificationUserInfo.setPostalCode("80022");
        IdentityVerificationUserInfo.setDob("1984-12-11");
        IdentityVerificationUserInfo.setLast4ssn("6789");
        IdentityVerificationUserInfo.setNationalId("666739999");
        IdentityVerificationUserInfo.setEmailAddress("abc@def.com");

        return IdentityVerificationUserInfo;
    }

}
