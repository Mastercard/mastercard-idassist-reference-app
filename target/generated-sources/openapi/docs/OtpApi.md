# OtpApi

All URIs are relative to *https://api.mastercard.com/mcidassist*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createOtpsSMS**](OtpApi.md#createOtpsSMS) | **POST** /sms-otps | Generate an SMS OTP
[**verifyOtps**](OtpApi.md#verifyOtps) | **POST** /sms-otp-verifications | Verify an SMS OTP


<a name="createOtpsSMS"></a>
# **createOtpsSMS**
> SMSOTP createOtpsSMS(smSOTPGeneration)

Generate an SMS OTP

Create and Send a One-Time Passcode (OTP) via SMS to the phone number provided.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.OtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    OtpApi apiInstance = new OtpApi(defaultClient);
    SMSOTPGeneration smSOTPGeneration = new SMSOTPGeneration(); // SMSOTPGeneration | 
    try {
      SMSOTP result = apiInstance.createOtpsSMS(smSOTPGeneration);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling OtpApi#createOtpsSMS");
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
 **smSOTPGeneration** | [**SMSOTPGeneration**](SMSOTPGeneration.md)|  |

### Return type

[**SMSOTP**](SMSOTP.md)

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

<a name="verifyOtps"></a>
# **verifyOtps**
> SMSOTPVerificationResult verifyOtps(smSOTPVerification)

Verify an SMS OTP

Verify that the provided code matches One-Time Passcode (OTP) sent via SMS during &#x60;/sms-otps&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.OtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    OtpApi apiInstance = new OtpApi(defaultClient);
    SMSOTPVerification smSOTPVerification = new SMSOTPVerification(); // SMSOTPVerification | 
    try {
      SMSOTPVerificationResult result = apiInstance.verifyOtps(smSOTPVerification);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling OtpApi#verifyOtps");
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
 **smSOTPVerification** | [**SMSOTPVerification**](SMSOTPVerification.md)|  |

### Return type

[**SMSOTPVerificationResult**](SMSOTPVerificationResult.md)

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

