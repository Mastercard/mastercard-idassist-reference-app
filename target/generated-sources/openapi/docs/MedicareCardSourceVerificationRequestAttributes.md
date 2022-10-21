

# MedicareCardSourceVerificationRequestAttributes

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**userConsent** | [**UserConsentEnum**](#UserConsentEnum) | Consent from User | 
**cardColor** | [**CardColorEnum**](#CardColorEnum) | Medicard Color can be GREEN, BLUE OR YELLOW | 
**nameLine1** | **String** | Name Line 1 | 
**nameLine2** | **String** | Name Line 2 |  [optional]
**nameLine3** | **String** | Name Line 3 |  [optional]
**nameLine4** | **String** | Name Line 4 |  [optional]
**medicareCardNo** | **String** | Unique number for Medicare card | 
**individualReferenceNo** | **String** | Individual Reference Number | 
**countryCode** | **String** | Country code (case insensitive) as described in the ISO 3166 alpha-2 international standard | 
**birthDate** | [**LocalDate**](LocalDate.md) | YYYY-MM-DD format |  [optional]
**expiryDate** | **String** | The expiry date as it appears on the card, usually in DD/MM/YYYY for Green Medicare Cards and MM/YYYY for Blue and Yellow Medicare Cards. API accepts YYYY-MM format for Green Medicare Cards and YYYY-MM-DD for Blue and Yellow Medicare Cards. | 



## Enum: UserConsentEnum

Name | Value
---- | -----
ACCEPT | &quot;ACCEPT&quot;
DECLINE | &quot;DECLINE&quot;
REVOKE | &quot;REVOKE&quot;
EXPIRE | &quot;EXPIRE&quot;



## Enum: CardColorEnum

Name | Value
---- | -----
GREEN | &quot;GREEN&quot;
BLUE | &quot;BLUE&quot;
YELLOW | &quot;YELLOW&quot;



