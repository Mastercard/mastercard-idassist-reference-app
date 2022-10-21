# IdDocumentDataExtractionOcrApi

All URIs are relative to *https://api.mastercard.com/idverify*

Method | HTTP request | Description
------------- | ------------- | -------------
[**extractScannedDocumentData**](IdDocumentDataExtractionOcrApi.md#extractScannedDocumentData) | **GET** /data-extractions/scans/{scan_id} | Returns the status of the document verification.
[**retrieveDataExtractionAccessToken**](IdDocumentDataExtractionOcrApi.md#retrieveDataExtractionAccessToken) | **POST** /data-extractions/access-tokens | The provider token is retrieved by country code and SDK version


<a name="extractScannedDocumentData"></a>
# **extractScannedDocumentData**
> DocumentVerificationExtractedData extractScannedDocumentData(scanId, userConsent, retrieveSelfie, retrieveDocumentImages, retrieveFacemap, documentType, userSelectedCountry, xEncryptedPayload)

Returns the status of the document verification.

Returns the status of the document verification as it&#39;s being processed by the vendor.

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdDocumentDataExtractionOcrApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdDocumentDataExtractionOcrApi apiInstance = new IdDocumentDataExtractionOcrApi(defaultClient);
    String scanId = df52649e-4096-456a-bca0-751ee470009f; // String | UUID representing the scanned document verification process
    String userConsent = ACCEPT; // String | Consent from User
    String retrieveSelfie = true; // String | Flag indicating if the selfie needs to be retrieved.
    String retrieveDocumentImages = true; // String | Flag indicating if the document images needs to be retrieved.
    String retrieveFacemap = true; // String | Flag indicating if the facemap needs to be retrieved.
    String documentType = DRIVING_LICENSE; // String | document Type
    String userSelectedCountry = USA; // String | country where the the document provided
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      DocumentVerificationExtractedData result = apiInstance.extractScannedDocumentData(scanId, userConsent, retrieveSelfie, retrieveDocumentImages, retrieveFacemap, documentType, userSelectedCountry, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdDocumentDataExtractionOcrApi#extractScannedDocumentData");
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
 **scanId** | **String**| UUID representing the scanned document verification process |
 **userConsent** | **String**| Consent from User |
 **retrieveSelfie** | **String**| Flag indicating if the selfie needs to be retrieved. |
 **retrieveDocumentImages** | **String**| Flag indicating if the document images needs to be retrieved. |
 **retrieveFacemap** | **String**| Flag indicating if the facemap needs to be retrieved. |
 **documentType** | **String**| document Type |
 **userSelectedCountry** | **String**| country where the the document provided |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**DocumentVerificationExtractedData**](DocumentVerificationExtractedData.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Success |  * X-Transaction-ID -  <br>  |
**400** | Something was wrong with the request. |  -  |
**403** | Consent not given. |  -  |
**404** | Request didn&#39;t match an existing resource. |  -  |

<a name="retrieveDataExtractionAccessToken"></a>
# **retrieveDataExtractionAccessToken**
> AccessToken retrieveDataExtractionAccessToken(retrieveAccessToken, xEncryptedPayload)

The provider token is retrieved by country code and SDK version

Return a provider token to be passed to the MIDS Liveness SDK module

### Example
```java
// Import classes:
import com.mastercard.developer.ApiClient;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.Configuration;
import com.mastercard.developer.models.*;
import com.mastercard.developer.api.IdDocumentDataExtractionOcrApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.mastercard.com/idverify");

    IdDocumentDataExtractionOcrApi apiInstance = new IdDocumentDataExtractionOcrApi(defaultClient);
    RetrieveAccessToken retrieveAccessToken = new RetrieveAccessToken(); // RetrieveAccessToken | 
    Boolean xEncryptedPayload = true; // Boolean | Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext. 
    try {
      AccessToken result = apiInstance.retrieveDataExtractionAccessToken(retrieveAccessToken, xEncryptedPayload);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling IdDocumentDataExtractionOcrApi#retrieveDataExtractionAccessToken");
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
 **retrieveAccessToken** | [**RetrieveAccessToken**](RetrieveAccessToken.md)|  |
 **xEncryptedPayload** | **Boolean**| Indicator that request is encrypted or to indicate that client is able to receive a encrypted response. If not set, payload will treated as plaintext.  | [optional]

### Return type

[**AccessToken**](AccessToken.md)

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

