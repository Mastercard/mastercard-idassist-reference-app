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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;

@Slf4j
@Component
public class EncryptionDecryptionInterceptor  implements Interceptor {


    @Value("${mastercard.api.idAssist.encryption.certificateFile}")
    private Resource idAssistEncryptionCertificateFile;

    @Value("${mastercard.api.idAssist.encryption.fingerPrint}")
    private String idAssistEncryptionCertificateFingerPrint;


    @Value("${mastercard.api.idAssist.decryption.keystore}")
    private Resource idAssistDecryptionKeystore;

    @Value("${mastercard.api.idAssist.decryption.alias}")
    private String idAssistDecryptionKeystoreAlias;

    @Value("${mastercard.api.idAssist.decryption.keystore.password}")
    private String idAssistDecryptionKeystorePassword;

    // ************************ID Verify Credentials*************************

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


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request encryptedRequest = handleRequest(chain.request());
        Response encryptedResponse = chain.proceed(encryptedRequest);
        return handleResponse(encryptedResponse);
    }

    private Request handleRequest(Request request) {
        try{
            String body = bodyToString(request);

            String s = EncryptionUtils.jweEncrypt(body, idAssistEncryptionCertificateFile, idAssistEncryptionCertificateFingerPrint);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("encryptedData", s);
            log.info("Encrypted Payload sending to server: {}",jsonObject);
            Request.Builder post = request
                    .newBuilder()
                    .headers(request.headers())
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonObject.toJSONString()));
            return post.build();

        }catch (Exception e){
            log.error("Unable to encrypt request data to server", e);
            throw new EncryptionDecryptionException("Unable to encrypt request data to server", e );
        }

    }

    private Response handleResponse(Response encryptedResponse) {
        try{
            if(encryptedResponse.code() != 200){
                return encryptedResponse; // We will receive encrypted payload only for 200 response
            }
            ResponseBody responseBody = encryptedResponse.body();
            String string = responseBody.string();
            log.info("Encrypted Payload received from server: {}",string);
            JSONObject parse = (JSONObject)JSONValue.parse(string);
            PrivateKey signingKey = AuthenticationUtils.loadSigningKey(idAssistDecryptionKeystore.getFile().getAbsolutePath(), idAssistDecryptionKeystoreAlias, idAssistDecryptionKeystorePassword);
            String decryptedPayload = EncryptionUtils.jweDecrypt(parse.getAsString("encryptedData"), (RSAPrivateKey) signingKey);

            Response.Builder responseBuilder = encryptedResponse.newBuilder();
            ResponseBody decryptedBody = ResponseBody.create(responseBody.contentType(), decryptedPayload);

            return responseBuilder
                    .body(decryptedBody)
                    .header("Content-Length", String.valueOf(decryptedBody.contentLength()))
                    .build();
        }catch (Exception e){
            log.error("Unable to decrypt response from server", e);
            throw new EncryptionDecryptionException("Unable to process response from server", e );
        }

    }

    private static String bodyToString(final Request request){
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
