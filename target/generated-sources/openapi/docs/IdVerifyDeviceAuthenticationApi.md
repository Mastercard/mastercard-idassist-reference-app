# IdVerifyDeviceAuthenticationApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deviceAuthentication**](IdVerifyDeviceAuthenticationApi.md#deviceAuthentication) | **POST** /device-authentications | Provide Redirect URL for Device Authentication Verification
[**deviceAuthenticationVerification**](IdVerifyDeviceAuthenticationApi.md#deviceAuthenticationVerification) | **POST** /device-authentication-verifications | Provide Phone Number using verificationFingerprint


<a name="deviceAuthentication"></a>
# **deviceAuthentication**
> DeviceAuthenticationVerificationUrl deviceAuthentication(deviceIpAddress, xEncryptedPayload)

Provide Redirect URL for Device Authentication Verification

This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdVerifyDeviceAuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdVerifyDeviceAuthenticationApi apiInstance = new IdVerifyDeviceAuthenticationApi(defaultClient);
    DeviceIpAddress deviceIpAddress = new DeviceIpAddress(); // DeviceIpAddress | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      DeviceAuthenticationVerificationUrl result = apiInstance.deviceAuthentication(deviceIpAddress, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdVerifyDeviceAuthenticationApi#deviceAuthentication");
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
 **deviceIpAddress** | [**DeviceIpAddress**](DeviceIpAddress.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**DeviceAuthenticationVerificationUrl**](DeviceAuthenticationVerificationUrl.md)

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

<a name="deviceAuthenticationVerification"></a>
# **deviceAuthenticationVerification**
> DevicePhoneNumber deviceAuthenticationVerification(deviceVerificationFingerprint, xEncryptedPayload)

Provide Phone Number using verificationFingerprint

This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdVerifyDeviceAuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdVerifyDeviceAuthenticationApi apiInstance = new IdVerifyDeviceAuthenticationApi(defaultClient);
    DeviceVerificationFingerprint deviceVerificationFingerprint = new DeviceVerificationFingerprint(); // DeviceVerificationFingerprint | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      DevicePhoneNumber result = apiInstance.deviceAuthenticationVerification(deviceVerificationFingerprint, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdVerifyDeviceAuthenticationApi#deviceAuthenticationVerification");
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
 **deviceVerificationFingerprint** | [**DeviceVerificationFingerprint**](DeviceVerificationFingerprint.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**DevicePhoneNumber**](DevicePhoneNumber.md)

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

