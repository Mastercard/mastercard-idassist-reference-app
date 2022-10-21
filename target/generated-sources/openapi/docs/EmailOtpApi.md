# EmailOtpApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sendEmailOtp**](EmailOtpApi.md#sendEmailOtp) | **POST** /email-otps | Creates and Send a One-Time Passcode (OTP) via Email
[**verifyEmailOtp**](EmailOtpApi.md#verifyEmailOtp) | **POST** /email-otp-verifications | Verifies the provided code matches the Email OTP


<a name="sendEmailOtp"></a>
# **sendEmailOtp**
> Otp sendEmailOtp(emailOtp, xEncryptedPayload)

Creates and Send a One-Time Passcode (OTP) via Email

Create and Send a One-Time Passcode (OTP) via Email to the address provided.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.EmailOtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    EmailOtpApi apiInstance = new EmailOtpApi(defaultClient);
    EmailOtp emailOtp = new EmailOtp(); // EmailOtp | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      Otp result = apiInstance.sendEmailOtp(emailOtp, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmailOtpApi#sendEmailOtp");
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
 **emailOtp** | [**EmailOtp**](EmailOtp.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**Otp**](Otp.md)

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

<a name="verifyEmailOtp"></a>
# **verifyEmailOtp**
> OtpVerificationResult verifyEmailOtp(otpVerification, xEncryptedPayload)

Verifies the provided code matches the Email OTP

Verify that the provided code matches One-Time Passcode (OTP) sent via Email during &#x60;/email-otps&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.EmailOtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    EmailOtpApi apiInstance = new EmailOtpApi(defaultClient);
    OtpVerification otpVerification = new OtpVerification(); // OtpVerification | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      OtpVerificationResult result = apiInstance.verifyEmailOtp(otpVerification, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling EmailOtpApi#verifyEmailOtp");
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
 **otpVerification** | [**OtpVerification**](OtpVerification.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**OtpVerificationResult**](OtpVerificationResult.md)

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

