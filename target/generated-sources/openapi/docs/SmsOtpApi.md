# SmsOtpApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**sendSmsOtp**](SmsOtpApi.md#sendSmsOtp) | **POST** /sms-otps | Creates and Send a One-Time Passcode (OTP) via SMS
[**verifySmsOtp**](SmsOtpApi.md#verifySmsOtp) | **POST** /sms-otp-verifications | Verifies the provided code matches the SMS OTP


<a name="sendSmsOtp"></a>
# **sendSmsOtp**
> Otp sendSmsOtp(smSOtp, xEncryptedPayload)

Creates and Send a One-Time Passcode (OTP) via SMS

Create and Send a One-Time Passcode (OTP) via SMS to the address provided.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.SmsOtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    SmsOtpApi apiInstance = new SmsOtpApi(defaultClient);
    SMSOtp smSOtp = new SMSOtp(); // SMSOtp | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      Otp result = apiInstance.sendSmsOtp(smSOtp, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SmsOtpApi#sendSmsOtp");
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
 **smSOtp** | [**SMSOtp**](SMSOtp.md)|  |
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

<a name="verifySmsOtp"></a>
# **verifySmsOtp**
> OtpVerificationResult verifySmsOtp(otpVerification, xEncryptedPayload)

Verifies the provided code matches the SMS OTP

Verify provided code matches the SMS OTP

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.SmsOtpApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    SmsOtpApi apiInstance = new SmsOtpApi(defaultClient);
    OtpVerification otpVerification = new OtpVerification(); // OtpVerification | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      OtpVerificationResult result = apiInstance.verifySmsOtp(otpVerification, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling SmsOtpApi#verifySmsOtp");
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

