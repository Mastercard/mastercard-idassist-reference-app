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

package com.mastercard.developer.interceptor;

import com.mastercard.developer.util.EncryptionUtils;
import com.mastercard.developer.utils.AuthenticationUtils;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class EncryptionDecryptionInterceptor implements Interceptor {

    @Value("${mastercard.api.idVerify.encryption.certificateFile}")
    private Resource idVerifyEncryptionCertificateFile;

    @Value("${mastercard.api.idVerify.encryption.fingerPrint}")
    private String idVerifyEncryptionCertificateFingerPrint;

    @Value("${mastercard.api.idVerify.decryption.keystore}")
    private Resource idVerifyDecryptionKeystore;

    @Value("${mastercard.api.idVerify.decryption.alias}")
    private String idVerifyDecryptionKeystoreAlias;

    @Value("${mastercard.api.idVerify.decryption.keystore.password}")
    private String idVerifyDecryptionKeystorePassword;

    @Value("${mastercard.api.idVerify.consumer.key}")
    private String idVerifyConsumerKey;

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request encryptedRequest = handleRequest(chain.request());
        Response encryptedResponse = chain.proceed(encryptedRequest);
        return handleResponse(encryptedRequest, encryptedResponse);
    }

    private Request handleRequest(Request request) {
        if (isEncryptionRequired(request)) {
            try {
                String body = bodyToString(request);
                String encryptedRequest = EncryptionUtils.jweEncrypt(body, idVerifyEncryptionCertificateFile, idVerifyEncryptionCertificateFingerPrint);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("encryptedData", encryptedRequest);
                log.info("Encrypted Payload sending to server: {}", jsonObject);
                Request.Builder post = request
                        .newBuilder()
                        .headers(request.headers())
                        .post(RequestBody.create(jsonObject.toJSONString(), MediaType.parse("application/json")));

                return post.build();

            } catch (Exception e) {
                log.error("Unable to encrypt request data to server", e);
                throw new EncryptionDecryptionException("Unable to encrypt request data to server", e);
            }
        }
        return request;
    }

    private Response handleResponse(Request request, Response encryptedResponse) {
        if (isDecryptionRequired(request)) {
            try {
                if (encryptedResponse.code() != 200) {
                    return encryptedResponse; // We will receive encrypted payload only for 200 response
                }
                ResponseBody responseBody = encryptedResponse.body();
                String string = Objects.requireNonNull(responseBody).string();
                log.info("Encrypted Payload received from server: {}", string);
                JSONObject parse = (JSONObject) JSONValue.parse(string);

                PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idVerifyDecryptionKeystore.getFile().getAbsolutePath(), idVerifyDecryptionKeystoreAlias, idVerifyDecryptionKeystorePassword);

                String decryptedPayload = EncryptionUtils.jweDecrypt(parse.getAsString("encryptedData"), (RSAPrivateKey) signingKey);

                Response.Builder responseBuilder = encryptedResponse.newBuilder();
                ResponseBody decryptedBody = ResponseBody.create(decryptedPayload, responseBody.contentType());

                return responseBuilder
                        .body(decryptedBody)
                        .header("Content-Length", String.valueOf(decryptedBody.contentLength()))
                        .build();
            } catch (Exception e) {
                log.error("Unable to decrypt response from server", e);
                throw new EncryptionDecryptionException("Unable to process response from server", e);
            }
        }
        return encryptedResponse;
    }

    private boolean isEncryptionRequired(Request request) {
        List<String> list = Arrays.asList(
                "/user-identities",
                "/user-verifications",
                "/trust-score",
                "/device-authentication-verifications",
                "/device-authentications",
                "/source-verifications/{issuing_country}/medicare-cards",
                "/passports",
                "/source-verifications/{issuing_country}/driving-licenses",
                "/email-otps",
                "/email-otp-verifications",
                "/sms-otps",
                "/sms-otp-verifications");
        return list.stream().anyMatch(entry -> request.url().uri().getPath().contains(entry));
    }

    private boolean isDecryptionRequired(Request request) {
        List<String> list = Arrays.asList(
                "/data-extractions/access-tokens",
                "/device-authentications",
                "/trust-scores",
                "/user-identities",
                "/user-verifications",
                "/data-extractions/scans",
                "/device-authentication-verifications",
                "/email-otps",
                "/email-otp-verifications",
                "/sms-otps",
                "/sms-otp-verifications");
        return list.stream().anyMatch(entry -> request.url().uri().getPath().contains(entry));
    }

    private static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            Objects.requireNonNull(copy.body()).writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
