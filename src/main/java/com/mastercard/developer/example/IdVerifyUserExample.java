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

import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IdVerifyUserExample {

    public static IdentityPrefill getIdentity() {
        IdentityPrefill identityPrefill = new IdentityPrefill();
        List<String> list = Arrays.asList("firstName", "lastName", "dob", "nationalId", "emailAddress","address");
        identityPrefill.setCountryCode("US");
        identityPrefill.setPhoneNumber("13334445555");
        identityPrefill.setLast4ssn("0003");
        identityPrefill.setDob("1982-05-14");
        identityPrefill.setNationalId("123456789");
        identityPrefill.setPerformEligibilityCheck(true);
        identityPrefill.setOptedInConsentStatus(true);
        identityPrefill.setScopedFields(list);

        return identityPrefill;
    }

    public static IdentityVerificationUserInfo getIdentityVerificationUserInfo() {
        IdentityVerificationUserInfo identityVerificationUserInfo = new IdentityVerificationUserInfo();

        identityVerificationUserInfo.setPhoneNumber("13143039289");
        identityVerificationUserInfo.setFirstName("John");
        identityVerificationUserInfo.setLastName("Smith");
        identityVerificationUserInfo.setEmailAddress("abc@def.com");
        identityVerificationUserInfo.setOptedInConsentStatus(true);
        identityVerificationUserInfo.setCountryCode("US");
        identityVerificationUserInfo.setNationalId("666739999");
        identityVerificationUserInfo.setAddress("11 Faux Street");
        identityVerificationUserInfo.setExtendedAddress("Apt. 202");
        identityVerificationUserInfo.setCity("New York");
        identityVerificationUserInfo.setRegion("NY");
        identityVerificationUserInfo.setPostalCode("80022");
        identityVerificationUserInfo.setDob("1984-12-11");
        identityVerificationUserInfo.setLast4ssn("6789");

        return identityVerificationUserInfo;
    }


}
