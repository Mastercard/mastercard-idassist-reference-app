# IdDocumentVerificationApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**verifyDriversLicense**](IdDocumentVerificationApi.md#verifyDriversLicense) | **POST** /source-verifications/{issuing_country}/driving-licenses | Verifies the details of a driving license document with an identity verification provider
[**verifyMedicareCard**](IdDocumentVerificationApi.md#verifyMedicareCard) | **POST** /source-verifications/{issuing_country}/medicare-cards | Verifies the details of a medicare card document with an identity verification provider
[**verifyPassport**](IdDocumentVerificationApi.md#verifyPassport) | **POST** /source-verifications/{issuing_country}/passports | Verifies the details of a passport document with an identity verification provider


<a name="verifyDriversLicense"></a>
# **verifyDriversLicense**
> SourceVerificationResult verifyDriversLicense(issuingCountry, driversLicenseSourceVerificationRequestAttributes, xEncryptedPayload)

Verifies the details of a driving license document with an identity verification provider

Returns the Status of the Source Verification that has been processed by a trusted IVP.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdDocumentVerificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdDocumentVerificationApi apiInstance = new IdDocumentVerificationApi(defaultClient);
    String issuingCountry = USA; // String | Country of issue for the document in the ISO 3166-1 alpha-3 format
    DriversLicenseSourceVerificationRequestAttributes driversLicenseSourceVerificationRequestAttributes = new DriversLicenseSourceVerificationRequestAttributes(); // DriversLicenseSourceVerificationRequestAttributes | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      SourceVerificationResult result = apiInstance.verifyDriversLicense(issuingCountry, driversLicenseSourceVerificationRequestAttributes, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdDocumentVerificationApi#verifyDriversLicense");
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
 **issuingCountry** | **String**| Country of issue for the document in the ISO 3166-1 alpha-3 format |
 **driversLicenseSourceVerificationRequestAttributes** | [**DriversLicenseSourceVerificationRequestAttributes**](DriversLicenseSourceVerificationRequestAttributes.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**SourceVerificationResult**](SourceVerificationResult.md)

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
**403** | Consent not given. |  -  |

<a name="verifyMedicareCard"></a>
# **verifyMedicareCard**
> MedicareCardSourceVerificationResult verifyMedicareCard(issuingCountry, medicareCardSourceVerificationRequestAttributes, xEncryptedPayload)

Verifies the details of a medicare card document with an identity verification provider

Returns the status of the Medicare Card source verification as it&#39;s being processed by the vendor. Biometrics are not used with this API, which means document scanning is not required for the users to verify their identity. This will be a one-time verification with no data being stored.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdDocumentVerificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdDocumentVerificationApi apiInstance = new IdDocumentVerificationApi(defaultClient);
    String issuingCountry = USA; // String | Country of issue for the document in the ISO 3166-1 alpha-3 format
    MedicareCardSourceVerificationRequestAttributes medicareCardSourceVerificationRequestAttributes = new MedicareCardSourceVerificationRequestAttributes(); // MedicareCardSourceVerificationRequestAttributes | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      MedicareCardSourceVerificationResult result = apiInstance.verifyMedicareCard(issuingCountry, medicareCardSourceVerificationRequestAttributes, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdDocumentVerificationApi#verifyMedicareCard");
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
 **issuingCountry** | **String**| Country of issue for the document in the ISO 3166-1 alpha-3 format |
 **medicareCardSourceVerificationRequestAttributes** | [**MedicareCardSourceVerificationRequestAttributes**](MedicareCardSourceVerificationRequestAttributes.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**MedicareCardSourceVerificationResult**](MedicareCardSourceVerificationResult.md)

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
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="verifyPassport"></a>
# **verifyPassport**
> SourceVerificationResult verifyPassport(issuingCountry, passportSourceVerificationRequestAttributes, xEncryptedPayload)

Verifies the details of a passport document with an identity verification provider

Returns the Status of the Source Verification that has been processed by a trusted IVP.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdDocumentVerificationApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdDocumentVerificationApi apiInstance = new IdDocumentVerificationApi(defaultClient);
    String issuingCountry = USA; // String | Country of issue for the document in the ISO 3166-1 alpha-3 format
    PassportSourceVerificationRequestAttributes passportSourceVerificationRequestAttributes = new PassportSourceVerificationRequestAttributes(); // PassportSourceVerificationRequestAttributes | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      SourceVerificationResult result = apiInstance.verifyPassport(issuingCountry, passportSourceVerificationRequestAttributes, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdDocumentVerificationApi#verifyPassport");
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
 **issuingCountry** | **String**| Country of issue for the document in the ISO 3166-1 alpha-3 format |
 **passportSourceVerificationRequestAttributes** | [**PassportSourceVerificationRequestAttributes**](PassportSourceVerificationRequestAttributes.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**SourceVerificationResult**](SourceVerificationResult.md)

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
**403** | Consent not given. |  -  |

