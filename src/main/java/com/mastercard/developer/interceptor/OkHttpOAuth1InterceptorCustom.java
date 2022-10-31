package com.mastercard.developer.interceptor;

import com.mastercard.developer.signers.OkHttpSigner;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

public class OkHttpOAuth1InterceptorCustom implements Interceptor {

    private OkHttpSigner signerIdVerify;
    private OkHttpSigner signerMcAssist;

    public void initSignerIdVerify(String consumerKey, PrivateKey signingKey) {
        this.signerIdVerify = new OkHttpSigner(consumerKey, signingKey);
    }

    public void initSignerAssist(String consumerKey, PrivateKey signingKey) {
        this.signerMcAssist = new OkHttpSigner(consumerKey, signingKey);
    }


    public Response intercept(Chain chain) throws IOException {

        Request.Builder request = chain.request().newBuilder();

        if (chain.request().url().toString().contains("mcidassist")) {
            if (isOAuth1RequiredAssist(chain.request())) {

                this.signerMcAssist.sign(request);
                return chain.proceed(request.build());
            }
        } else {
            if (isOAuth1RequiredIdVerify(chain.request())) {

                this.signerIdVerify.sign(request);
                return chain.proceed(request.build());
            }
        }
        return chain.proceed(chain.request());
    }

    private boolean isOAuth1RequiredIdVerify(Request request) {
        List<String> list = Arrays.asList(
                "/data-extractions/access-tokens",
                "/device-authentications",
                "/user-identities",
                "/device-authentication-verifications",
                "/passports",
                "/data-extractions/scans",
                "/medicare-cards",
                "/driving-licenses",
                "/user-verifications",
                "/trust-scores"
        );
        return list.stream().anyMatch(entry -> request.url().uri().getPath().contains(entry));
    }

    private boolean isOAuth1RequiredAssist(Request request) {
        List<String> list = Arrays.asList(
                "/user-identities",
                "/user-verifications",
                "/trust-score",
                "/sms-otps",
                "/sms-otp-verifications",
                "/device-authentications",
                "/device-authentication-verifications"
        );
        return list.stream().anyMatch(entry -> request.url().uri().getPath().contains(entry));
    }

}
