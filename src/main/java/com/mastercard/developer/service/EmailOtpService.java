package com.mastercard.developer.service;

import com.mastercard.dis.mids.model.id.verification.EmailOtp;
import com.mastercard.dis.mids.model.id.verification.Otp;
import com.mastercard.dis.mids.model.id.verification.OtpVerification;
import com.mastercard.dis.mids.model.id.verification.OtpVerificationResult;

public interface EmailOtpService {

    Otp createEmailOtp(EmailOtp emailOtp);

    OtpVerificationResult createVerifyOtp(OtpVerification otpVerification);
}
