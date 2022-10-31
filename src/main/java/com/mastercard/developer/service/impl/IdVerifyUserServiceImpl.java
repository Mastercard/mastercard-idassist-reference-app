package com.mastercard.developer.service.impl;

import com.mastercard.developer.exception.ExceptionUtil;
import com.mastercard.developer.service.IdVerifyUserService;
import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.api.IdVerifyUserApi;
import com.mastercard.dis.mids.model.id.verification.Identity;
import com.mastercard.dis.mids.model.id.verification.IdentityPrefill;
import com.mastercard.dis.mids.model.id.verification.IdentityVerification;
import com.mastercard.dis.mids.model.id.verification.IdentityVerificationUserInfo;
import com.mastercard.dis.mids.model.id.verification.TrustScore;
import com.mastercard.dis.mids.model.id.verification.TrustScoreUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IdVerifyUserServiceImpl implements IdVerifyUserService {

    private final IdVerifyUserApi idVerifyUserApi;
    private final ExceptionUtil exceptionUtil;

    @Autowired
    public IdVerifyUserServiceImpl(IdVerifyUserApi idVerifyUserApi, ExceptionUtil exceptionUtil) {
        this.idVerifyUserApi = idVerifyUserApi;
        this.exceptionUtil = exceptionUtil;
    }

    @Override
    public Identity userIdentity(IdentityPrefill identityPrefill) {
        try {
            return idVerifyUserApi.userIdentity(identityPrefill, Boolean.TRUE);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public IdentityVerification identityVerification(IdentityVerificationUserInfo identityVerificationUserInfo) {
        try {
            return idVerifyUserApi.identityVerification(identityVerificationUserInfo, Boolean.TRUE);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }

    @Override
    public TrustScore userTrustScore(TrustScoreUserInfo trustScoreUserInfo) {
        try {
            return idVerifyUserApi.userTrustScore(trustScoreUserInfo, Boolean.TRUE);
        } catch (ApiException e) {
            throw exceptionUtil.logAndConvertToServiceException(e);
        }
    }
}
