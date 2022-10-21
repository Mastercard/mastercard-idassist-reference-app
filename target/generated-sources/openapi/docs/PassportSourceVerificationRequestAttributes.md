

# PassportSourceVerificationRequestAttributes

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**documentNumber** | **String** | Document Number | 
**userConsent** | [**UserConsentEnum**](#UserConsentEnum) | Consent from User | 
**dateOfBirth** | [**LocalDate**](LocalDate.md) | YYYY-MM-DD format | 
**firstName** | **String** | Users First Name | 
**lastName** | **String** | Users Last Name | 
**gender** | **String** | Users Gender |  [optional]



## Enum: UserConsentEnum

Name | Value
---- | -----
ACCEPT | &quot;ACCEPT&quot;
DECLINE | &quot;DECLINE&quot;
REVOKE | &quot;REVOKE&quot;
EXPIRE | &quot;EXPIRE&quot;



