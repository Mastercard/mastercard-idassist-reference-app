

# OtpVerification

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**code** | **String** | The 6-digit code which was sent to the user. | 
**otpId** | **String** | The otpId in the response body of create otp request. | 
**countryCode** | **String** | Size must be exactly 2 letters. This attribute is passed for all of the exposed APIs.   | 
**userConsent** | [**UserConsentEnum**](#UserConsentEnum) | Consent from User |  [optional]
**optedInConsentStatus** | **Boolean** | Should be true to proceed with the request. This attribute is passed for all of the exposed APIs. |  [optional]



## Enum: UserConsentEnum

Name | Value
---- | -----
ACCEPT | &quot;ACCEPT&quot;
DECLINE | &quot;DECLINE&quot;
REVOKE | &quot;REVOKE&quot;
EXPIRE | &quot;EXPIRE&quot;



