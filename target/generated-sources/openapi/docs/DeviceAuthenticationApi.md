# DeviceAuthenticationApi

All URIs are relative to *https://api.mastercard.com/mcidassist*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deviceAuthentication**](DeviceAuthenticationApi.md#deviceAuthentication) | **POST** /device-authentications | Device Authentication
[**deviceAuthenticationVerification**](DeviceAuthenticationApi.md#deviceAuthenticationVerification) | **POST** /device-authentication-verifications | Device Authentication Verification


<a name="deviceAuthentication"></a>
# **deviceAuthentication**
> DeviceAuthenticationVerificationUrl deviceAuthentication(deviceIpAddress)

Device Authentication

This API provides a &#x60;&#x60;redirectTargetUrl&#x60;&#x60; for a given device IP Address. The &#x60;&#x60;redirectTargetUrl&#x60;&#x60; can be used to retrieve a &#x60;&#x60;verificationFingerprint&#x60;&#x60; which is used for verification in &#x60;&#x60;/device-authentication-verifications&#x60;&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.DeviceAuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    DeviceAuthenticationApi apiInstance = new DeviceAuthenticationApi(defaultClient);
    DeviceIpAddress deviceIpAddress = new DeviceIpAddress(); // DeviceIpAddress | 
    try {
      DeviceAuthenticationVerificationUrl result = apiInstance.deviceAuthentication(deviceIpAddress);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeviceAuthenticationApi#deviceAuthentication");
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
**200** | Success |  -  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |

<a name="deviceAuthenticationVerification"></a>
# **deviceAuthenticationVerification**
> DevicePhoneNumber deviceAuthenticationVerification(deviceVerificationFingerprint)

Device Authentication Verification

Use this API to retrieve a phone number for a device &#x60;&#x60;verificationFingerprint&#x60;&#x60;. The &#x60;&#x60;verificationFingerprint&#x60;&#x60; is retrieved by calling the &#x60;&#x60;redirectTargetUrl&#x60;&#x60; returned from &#x60;&#x60;/device-authentications&#x60;&#x60;

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.DeviceAuthenticationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/mcidassist");

    DeviceAuthenticationApi apiInstance = new DeviceAuthenticationApi(defaultClient);
    DeviceVerificationFingerprint deviceVerificationFingerprint = new DeviceVerificationFingerprint(); // DeviceVerificationFingerprint | 
    try {
      DevicePhoneNumber result = apiInstance.deviceAuthenticationVerification(deviceVerificationFingerprint);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DeviceAuthenticationApi#deviceAuthenticationVerification");
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
**200** | Success |  -  |
**400** | Something was wrong with the request. |  -  |
**401** | Unauthorized request. |  -  |
**403** | Consent not given. |  -  |

