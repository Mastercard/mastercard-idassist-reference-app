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

import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.OtpVerificationResult;
import com.mastercard.dis.mids.model.id.verification.SMSOtp;

public interface SmsOtpService {

    Otp createSMSOtp(SMSOtp smsOtp);

    OtpVerificationResult createVerifyOtp(OtpVerification otpVerification);
}