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
import com.mastercard.dis.mids.api.IdDocumentDataExtractionOcrApi;
import com.mastercard.dis.mids.api.IdDocumentVerificationApi;
import com.mastercard.dis.mids.api.IdVerifyDeviceAuthenticationApi;
import com.mastercard.dis.mids.api.IdVerifyUserApi;
import com.mastercard.dis.mids.api.OtpApi;
import com.mastercard.dis.mids.api.UserApi;
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


    private OkHttpOAuth1InterceptorCustom curstomInterceptorOAuth = new OkHttpOAuth1InterceptorCustom();
    private static final String X_ENCRYPTED_HEADER = "X-Encrypted-Payload";
    private static final String ERROR_MSG_CONFIGURING_CLIENT = "Error occurred while configuring ApiClient";

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
            idVerifyApiClient.addDefaultHeader(X_ENCRYPTED_HEADER,  Boolean.FALSE.toString());
            // you can add extra headers here ..

            OkHttpClient.Builder okHttpClientBuilder = idVerifyApiClient.getHttpClient().newBuilder();
            if (!cicdEnabled) {
                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idVerifyKeyFile.getFile().getAbsolutePath(), idVerifyKeystoreAlias, idVerifyKeystorePassword);
                curstomInterceptorOAuth.initSignerIdVerify(idVerifyConsumerKey, signingKey);
                idVerifyApiClient.setHttpClient(okHttpClientBuilder
                        .addInterceptor(encryptionDecryptionInterceptor) // This interceptor will encrypt and decrypt the payload, uncomment this line if encrypted payload is enabled
                        .addInterceptor(curstomInterceptorOAuth)
                        .build());
            } else {
                idVerifyApiClient.setHttpClient(okHttpClientBuilder.build());
            }
            return idVerifyApiClient;
        } catch (Exception e) {
            log.error(ERROR_MSG_CONFIGURING_CLIENT, e);
            throw new ServiceException(ERROR_MSG_CONFIGURING_CLIENT);

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
            // you can add extra headers here ..

            if (!cicdEnabled) {
                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idAssistKeyFile.getFile().getAbsolutePath(), idAssistKeystoreAlias, idAssistKeystorePassword);
                curstomInterceptorOAuth.initSignerAssist(idAssistConsumerKey, signingKey);
                idAssistApiClient.setHttpClient(okHttpClientBuilder
                        .addInterceptor(encryptionDecryptionInterceptor) // This interceptor will encrypt and decrypt the payload, uncomment this line if encrypted payload is enabled
                        .addInterceptor(curstomInterceptorOAuth)
                        .build());
            } else {
                idAssistApiClient.setHttpClient(okHttpClientBuilder.build());
            }
            return idAssistApiClient;
        } catch (Exception e) {
            log.error(ERROR_MSG_CONFIGURING_CLIENT, e);
            throw new ServiceException(ERROR_MSG_CONFIGURING_CLIENT);

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
    public IdVerifyUserApi idVerifyUserApi(ApiClient idVerifyApiClient) {
        return new IdVerifyUserApi(idVerifyApiClient);
    }

    @Bean
    public IdVerifyDeviceAuthenticationApi idVerifyDeviceAuthenticationApi(ApiClient idVerifyApiClient) {
        return new IdVerifyDeviceAuthenticationApi(idVerifyApiClient);
    }

    @Bean
    public OtpApi otpApi(ApiClient idAssistApiClient) {
        return new OtpApi(idAssistApiClient);
    }

}
