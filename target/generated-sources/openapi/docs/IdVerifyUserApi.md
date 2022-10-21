# IdVerifyUserApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**identityVerification**](IdVerifyUserApi.md#identityVerification) | **POST** /user-verifications | Verify an Identity
[**userIdentity**](IdVerifyUserApi.md#userIdentity) | **POST** /user-identities | Fetch User Identity
[**userTrustScore**](IdVerifyUserApi.md#userTrustScore) | **POST** /trust-scores | Fetch Trust Score


<a name="identityVerification"></a>
# **identityVerification**
> IdentityVerification identityVerification(identityVerificationUserInfo, xEncryptedPayload)

Verify an Identity

Verifies user entered Personally Identifiable Information (PII) by returning a true/false or matching score per attribute along with an overall trust score for the record

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdVerifyUserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdVerifyUserApi apiInstance = new IdVerifyUserApi(defaultClient);
    IdentityVerificationUserInfo identityVerificationUserInfo = new IdentityVerificationUserInfo(); // IdentityVerificationUserInfo | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      IdentityVerification result = apiInstance.identityVerification(identityVerificationUserInfo, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdVerifyUserApi#identityVerification");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **identityVerificationUserInfo** | [**IdentityVerificationUserInfo**](IdentityVerificationUserInfo.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**IdentityVerification**](IdentityVerification.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  * X-Transaction-ID -  <br>  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="userIdentity"></a>
# **userIdentity**
> Identity userIdentity(identityPrefill, xEncryptedPayload)

Fetch User Identity

This API will provide information about an individual user with one of the following: - Phone Number and Last 4 Digits of SSN - Phone Number and National ID - Phone Number and Date of Birth

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdVerifyUserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdVerifyUserApi apiInstance = new IdVerifyUserApi(defaultClient);
    IdentityPrefill identityPrefill = new IdentityPrefill(); // IdentityPrefill | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      Identity result = apiInstance.userIdentity(identityPrefill, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdVerifyUserApi#userIdentity");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **identityPrefill** | [**IdentityPrefill**](IdentityPrefill.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**Identity**](Identity.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  * X-Transaction-ID -  <br>  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="userTrustScore"></a>
# **userTrustScore**
> TrustScore userTrustScore(trustScoreUserInfo, xEncryptedPayload)

Fetch Trust Score

This API will provide trust information about an individual user

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdVerifyUserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdVerifyUserApi apiInstance = new IdVerifyUserApi(defaultClient);
    TrustScoreUserInfo trustScoreUserInfo = new TrustScoreUserInfo(); // TrustScoreUserInfo | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      TrustScore result = apiInstance.userTrustScore(trustScoreUserInfo, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdVerifyUserApi#userTrustScore");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **trustScoreUserInfo** | [**TrustScoreUserInfo**](TrustScoreUserInfo.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**TrustScore**](TrustScore.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  * X-Transaction-ID -  <br>  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

