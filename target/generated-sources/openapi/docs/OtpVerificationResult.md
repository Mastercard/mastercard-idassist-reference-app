

# OtpVerificationResult

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**responseCode** | **String** | &#x60;SUCCESS&#x60;, &#x60;FAILURE&#x60; or &#x60;ATTEMPTS_EXCEEDED&#x60; | 
**responseMessage** | **String** | A short message describing the response code | 
**attemptsRemaining** | **Integer** | The number of attempts remaining. This field is only populated for the &#x60;FAILURE&#x60; response code |  [optional]
**transactionId** | **String** | A random 128-bit UUID representing the transaction. | 



