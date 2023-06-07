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

package com.mastercard.developer.config;

import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.interceptor.EncryptionDecryptionInterceptor;
import com.mastercard.developer.interceptor.OkHttpOAuth1InterceptorCustom;
import com.mastercard.developer.utils.AuthenticationUtils;
import com.mastercard.dis.mids.ApiClient;
import com.mastercard.dis.mids.api.DeviceAuthenticationApi;
import com.mastercard.dis.mids.api.IdDocumentDataExtractionApi;
import com.mastercard.dis.mids.api.IdDocumentDataSourceVerificationApi;
import com.mastercard.dis.mids.api.EmailOtpApi;
import com.mastercard.dis.mids.api.SmsOtpApi;
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityVerificationApi;
import com.mastercard.dis.mids.api.PhoneNumberBasedIdentityPrefillApi;
import com.mastercard.dis.mids.api.PhoneNumberTrustScoringApi;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;

/**
 * This is ApiClient configuration, it will read properties from application.properties and create instance of ApiClient.
 */
@Slf4j
@Configuration
public class ApiClientConfiguration {

    @Value("${mastercard.api.idVerify.base.path}")
    private String idVerifyBasePath;

    @Value("${mastercard.api.idVerify.consumer.key}")
    private String idVerifyConsumerKey;

    @Value("${mastercard.api.idVerify.keystore.alias}")
    private String idVerifyKeystoreAlias;

    @Value("${mastercard.api.idVerify.keystore.password}")
    private String idVerifyKeystorePassword;

    @Value("${mastercard.api.idVerify.key.file}")
    private Resource idVerifyKeyFile;

    @Value("${mastercard.cicd.enabled:false}")
    private boolean cicdEnabled;

    @Value("${mastercard.client.encryption.enable:false}")
    private Boolean encryptionEnabled;


    private OkHttpOAuth1InterceptorCustom customInterceptorOAuth = new OkHttpOAuth1InterceptorCustom();
    private static final String X_ENCRYPTED_HEADER = "X-Encrypted-Payload";
    private static final String ERROR_MSG_CONFIGURING_CLIENT = "Error occurred while configuring ApiClient";

    @PostConstruct
    public void initialize() {
        if (null == idVerifyKeyFile || StringUtils.isEmpty(idVerifyConsumerKey)) {
            throw new ServiceException(".p12 file or consumerKey does not exist, please add details in application.properties");
        }
        showPayloadEncryptionEnableMessage();
    }

    @Bean
    public ApiClient idVerifyApiClient(EncryptionDecryptionInterceptor encryptionDecryptionInterceptor) {
        ApiClient idVerifyApiClient = new ApiClient();
        try {
            idVerifyApiClient.setBasePath(idVerifyBasePath);
            idVerifyApiClient.setDebugging(true);
            idVerifyApiClient.setReadTimeout(40000);
            idVerifyApiClient.addDefaultHeader(X_ENCRYPTED_HEADER, Boolean.toString(encryptionEnabled));
            // you can add extra headers here ..

            OkHttpClient.Builder okHttpClientBuilder = idVerifyApiClient.getHttpClient().newBuilder();
            if (!cicdEnabled) {
                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idVerifyKeyFile.getFile().getAbsolutePath(), idVerifyKeystoreAlias, idVerifyKeystorePassword);
                customInterceptorOAuth.initSignerIdVerify(idVerifyConsumerKey, signingKey);
                OkHttpClient client = buildClient(okHttpClientBuilder, encryptionDecryptionInterceptor);
                idVerifyApiClient.setHttpClient(client);
            } else {
                idVerifyApiClient.setHttpClient(okHttpClientBuilder.build());
            }
            return idVerifyApiClient;
        } catch (Exception e) {
            log.error(ERROR_MSG_CONFIGURING_CLIENT, e);
            throw new ServiceException(ERROR_MSG_CONFIGURING_CLIENT);
        }
    }


    OkHttpClient buildClient(OkHttpClient.Builder builder, EncryptionDecryptionInterceptor encryptionDecryptionInterceptor) {
        return Boolean.TRUE.equals(encryptionEnabled) ? (
                builder.addInterceptor(encryptionDecryptionInterceptor)
                        .addInterceptor(customInterceptorOAuth).build()
        ) : (
                builder.addInterceptor(customInterceptorOAuth)
                        .build()
        );
    }

    void showPayloadEncryptionEnableMessage() {
        if (Boolean.TRUE.equals(encryptionEnabled)) {
            log.warn("<--- Payload encryption enabled. To disable it change mastercard.client.encryption.enable in application.properties to false --->");
        }
    }

    @Bean
    public IdDocumentDataExtractionApi idDocumentDataExtractionOcrApi(ApiClient idVerifyApiClient) {
        return new IdDocumentDataExtractionApi(idVerifyApiClient);
    }

    @Bean
    public IdDocumentDataSourceVerificationApi idDocumentVerificationApi(ApiClient idVerifyApiClient) {
        return new IdDocumentDataSourceVerificationApi(idVerifyApiClient);
    }

    @Bean
    public PhoneNumberBasedIdentityPrefillApi phoneNumberBasedIdentityPrefillApi(ApiClient idAssistApiClient) {
        return new PhoneNumberBasedIdentityPrefillApi(idAssistApiClient);
    }

    @Bean
    public DeviceAuthenticationApi deviceAuthenticationApi(ApiClient idAssistApiClient) {
        return new DeviceAuthenticationApi(idAssistApiClient);
    }

    @Bean
    public PhoneNumberBasedIdentityVerificationApi phoneNumberBasedIdentityVerificationApi(ApiClient idAssistApiClient) {
        return new PhoneNumberBasedIdentityVerificationApi(idAssistApiClient);
    }

    @Bean
    public PhoneNumberTrustScoringApi phoneNumberTrustScoringApi(ApiClient idAssistApiClient) {
        return new PhoneNumberTrustScoringApi(idAssistApiClient);
    }

    @Bean
    public SmsOtpApi otpApi(ApiClient idAssistApiClient) {
        return new SmsOtpApi(idAssistApiClient);
    }

    @Bean
    public EmailOtpApi emailOtpApi(ApiClient apiClient) {
        return new EmailOtpApi(apiClient);
    }

}
