# UserApi

All URIs are relative to *https://api.mastercard.com/mcidassist*

Method | HTTP request | Description
------------- | ------------- | -------------
[**identityAPI**](UserApi.md#identityAPI) | **POST** /user-identities | Retrieve an Identity
[**identityVerificationAPI**](UserApi.md#identityVerificationAPI) | **POST** /user-verifications | Verify an Identity
[**trustAPI**](UserApi.md#trustAPI) | **POST** /trust-score | Retrieve trust for an identity


<a name="identityAPI"></a>
# **identityAPI**
> Identity identityAPI(identityPrefill)

Retrieve an Identity

This API will provide information about an individual user with one of the following: - Phone Number and Last 4 Digits of SSN - Phone Number and National ID - Phone Number and Date of Birth

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    UserApi apiInstance = new UserApi(defaultClient);
    IdentityPrefill identityPrefill = new IdentityPrefill(); // IdentityPrefill | 
    try {
      Identity result = apiInstance.identityAPI(identityPrefill);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#identityAPI");
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
**200** | Success |  -  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="identityVerificationAPI"></a>
# **identityVerificationAPI**
> IdentityVerification identityVerificationAPI(identityVerificationUserInfo)

Verify an Identity

Verifies user entered Personally Identifiable Information (PII) by returning a true/false or matching score per attribute along with an overall trust score for the record

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    UserApi apiInstance = new UserApi(defaultClient);
    IdentityVerificationUserInfo identityVerificationUserInfo = new IdentityVerificationUserInfo(); // IdentityVerificationUserInfo | 
    try {
      IdentityVerification result = apiInstance.identityVerificationAPI(identityVerificationUserInfo);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#identityVerificationAPI");
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
**200** | Success |  -  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="trustAPI"></a>
# **trustAPI**
> TrustScore trustAPI(trustScoreUserInfo)

Retrieve trust for an identity

This API will provide trust information about an individual user

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    UserApi apiInstance = new UserApi(defaultClient);
    TrustScoreUserInfo trustScoreUserInfo = new TrustScoreUserInfo(); // TrustScoreUserInfo | 
    try {
      TrustScore result = apiInstance.trustAPI(trustScoreUserInfo);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#trustAPI");
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
**200** | Success |  -  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

