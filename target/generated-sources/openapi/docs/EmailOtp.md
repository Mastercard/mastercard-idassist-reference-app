

# EmailOtp

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**emailAddress** | **String** | The email address in which to send the OTP to. | 
**locale** | **String** | IETF BCP 47 Format E.g. \&quot;en-GB\&quot; (English - United Kingdom), \&quot;es-MX\&quot; (Spanish - Mexico). More information: Locale ID (LCID) as defined by Microsoft. Not required. The default value is \&quot;en-US\&quot;. |  [optional]
**countryCode** | **String** | Size must be exactly 2 letters. This attribute is passed for all of the exposed APIs. | 
**userConsent** | [**UserConsentEnum**](#UserConsentEnum) | Consent from User |  [optional]
**optedInConsentStatus** | **Boolean** | Should be true to proceed with the request. This attribute is passed for all of the exposed APIs. |  [optional]



## Enum: UserConsentEnum

Name | Value
---- | -----
ACCEPT | &quot;ACCEPT&quot;
DECLINE | &quot;DECLINE&quot;
REVOKE | &quot;REVOKE&quot;
EXPIRE | &quot;EXPIRE&quot;



