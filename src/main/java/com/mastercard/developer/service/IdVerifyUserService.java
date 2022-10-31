package com.mastercard.developer.service;

import com.mastercard.dis.mids.model.id.verification.Identity;
import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerification;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.id.verification.TrustScore;
import com.mastercard.dis.mids.model.id.verification.TrustScoreUserInfo;

public interface IdVerifyUserService {

    Identity userIdentity(IdentityPrefill identityPrefill);

    IdentityVerification identityVerification(IdentityVerificationUserInfo identityVerificationUserInfo);

    TrustScore userTrustScore(TrustScoreUserInfo trustScoreUserInfo);
}
