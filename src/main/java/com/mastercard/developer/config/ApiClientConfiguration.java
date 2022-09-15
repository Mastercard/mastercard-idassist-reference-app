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

import com.mastercard.developer.ApiClient;
import com.mastercard.developer.api.DeviceAuthenticationApi;
import com.mastercard.developer.api.IdDocumentDataExtractionOcrApi;
import com.mastercard.developer.api.IdDocumentVerificationApi;
import com.mastercard.developer.api.OtpApi;
import com.mastercard.developer.api.UserApi;
import com.mastercard.developer.exception.ServiceException;
import com.mastercard.developer.interceptor.EncryptionDecryptionInterceptor;
import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.developer.utils.AuthenticationUtils;
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

    @Value("${mastercard.api.idAssist.base.path}")
    private String idAssistBasePath;

    @Value("${mastercard.api.idAssist.consumer.key}")
    private String idAssistConsumerKey;

    @Value("${mastercard.api.idAssist.keystore.alias}")
    private String idAssistKeystoreAlias;

    @Value("${mastercard.api.idAssist.keystore.password}")
    private String idAssistKeystorePassword;

    @Value("${mastercard.api.idAssist.key.file}")
    private Resource idAssistKeyFile;


    @PostConstruct
    public void initialize() {
        if (null == idVerifyKeyFile || StringUtils.isEmpty(idVerifyConsumerKey)) {
            throw new ServiceException(".p12 file or consumerKey does not exist, please add details in application.properties");
        }
    }

    @Bean
    public ApiClient idVerifyApiClient(EncryptionDecryptionInterceptor encryptionDecryptionInterceptor) {
        ApiClient idVerifyApiClient = new ApiClient();
        try {
            idVerifyApiClient.setBasePath(idVerifyBasePath);
            idVerifyApiClient.setDebugging(true);
            idVerifyApiClient.setReadTimeout(40000);
            OkHttpClient.Builder okHttpClientBuilder = idVerifyApiClient.getHttpClient().newBuilder();
            if (!cicdEnabled) {
                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idVerifyKeyFile.getFile().getAbsolutePath(), idVerifyKeystoreAlias, idVerifyKeystorePassword);
                idVerifyApiClient.setHttpClient(okHttpClientBuilder
                        //.addInterceptor(encryptionDecryptionInterceptor) // This interceptor will encrypt and decrypt the payload, uncomment this line if encrypted payload is enabled
                        .addInterceptor(new OkHttpOAuth1Interceptor(idVerifyConsumerKey, signingKey))
                        .build());
            } else {
                idVerifyApiClient.setHttpClient(okHttpClientBuilder.build());
            }
            return idVerifyApiClient;
        } catch (Exception e) {
            log.error("Error occurred while configuring ApiClient", e);
            throw new ServiceException("Error occurred while configuring ApiClient");

        }
    }

    @Bean
    public ApiClient idAssistApiClient(EncryptionDecryptionInterceptor encryptionDecryptionInterceptor) {
        ApiClient idAssistApiClient = new ApiClient();
        try {
            idAssistApiClient.setBasePath(idAssistBasePath);
            idAssistApiClient.setDebugging(true);
            idAssistApiClient.setReadTimeout(40000);
            OkHttpClient.Builder okHttpClientBuilder = idAssistApiClient.getHttpClient().newBuilder();
            if (!cicdEnabled) {
                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idAssistKeyFile.getFile().getAbsolutePath(), idAssistKeystoreAlias, idAssistKeystorePassword);
                idAssistApiClient.setHttpClient(okHttpClientBuilder
                        .addInterceptor(encryptionDecryptionInterceptor) // This interceptor will encrypt and decrypt the payload, uncomment this line if encrypted payload is enabled
                        .addInterceptor(new OkHttpOAuth1Interceptor(idAssistConsumerKey, signingKey))
                        .build());
            } else {
                idAssistApiClient.setHttpClient(okHttpClientBuilder.build());
            }
            return idAssistApiClient;
        } catch (Exception e) {
            log.error("Error occurred while configuring ApiClient", e);
            throw new ServiceException("Error occurred while configuring ApiClient");

        }
    }

    @Bean
    public IdDocumentDataExtractionOcrApi idDocumentDataExtractionOcrApi(ApiClient idVerifyApiClient) {
        return new IdDocumentDataExtractionOcrApi(idVerifyApiClient);
    }

    @Bean
    public IdDocumentVerificationApi idDocumentVerificationApi(ApiClient idVerifyApiClient) {
        return new IdDocumentVerificationApi(idVerifyApiClient);
    }

    @Bean
    public UserApi userApi(ApiClient idAssistApiClient) {
        return new UserApi(idAssistApiClient);
    }

    @Bean
    public DeviceAuthenticationApi deviceAuthenticationApi(ApiClient idAssistApiClient) {
        return new DeviceAuthenticationApi(idAssistApiClient);
    }

    @Bean
    public OtpApi otpApi(ApiClient idAssistApiClient) {
        return new OtpApi(idAssistApiClient);
    }

}
