

# SMSOTPVerificationResult

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionId** | **String** | A random 128-bit. UUID representing the MIDS transaction | 
**responseCode** | **String** | The result of the OTP verification. It can be either &#x60;&#x60;SUCCESS&#x60;&#x60;, &#x60;&#x60;FAILURE&#x60;&#x60; or &#x60;&#x60;ATTEMPTS_EXCEEDED&#x60;&#x60; |  [optional]
**responseMessage** | **String** | A short message describing the response code |  [optional]
**attemptsRemaining** | **Integer** | The number of attempts remaining. This field is only populated for the &#x60;&#x60;FAILURE&#x60;&#x60; response code |  [optional]



