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

import com.mastercard.dis.mids.model.SMSOTPGeneration;
import com.mastercard.dis.mids.model.SMSOTPVerification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SmsOtpExample {

    public static SMSOTPGeneration getSmsOtp() {
        SMSOTPGeneration smsOtp = new SMSOTPGeneration();
        smsOtp.setPhoneNumber("11234567890");
        smsOtp.setCountryCode("US");
        smsOtp.setOptedInConsentStatus(true);

        return smsOtp;
    }

    public static SMSOTPVerification getOtpVerification() {
        SMSOTPVerification otpVerification = new SMSOTPVerification();
        //For unit testing update the OtpId with the otpId from the /sms-otps response
        otpVerification.setOtpId("c24e93a5-a0aa-4873-ad38-50d28b332969");

        //For unit testing update the code with opt received on the phone
        otpVerification.setCode("123456");
        otpVerification.setCountryCode("US");
        otpVerification.setOptedInConsentStatus(true);

        return otpVerification;
    }
}
