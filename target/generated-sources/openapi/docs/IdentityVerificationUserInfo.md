

# IdentityVerificationUserInfo

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**optedInConsentStatus** | **Boolean** | Should be true, validation exception will be thrown if it is false. | 
**phoneNumber** | **String** | The phone number being queried in standard international format. It should be prefixed with the international dialling code, without the &#39;+&#39;. | 
**firstName** | **String** | The user&#39;s first name |  [optional]
**lastName** | **String** | The user&#39;s last name |  [optional]
**countryCode** | **String** | ISO-3166 Alpha-2 standard. | 
**address** | **String** | The user&#39;s address |  [optional]
**extendedAddress** | **String** | The user&#39;s address complement |  [optional]
**city** | **String** | The user&#39;s city |  [optional]
**region** | **String** | The user&#39;s region |  [optional]
**postalCode** | **String** | If address matching is desired at a minimum &#x60;&#x60;postalCode&#x60;&#x60; is required |  [optional]
**dob** | **String** | The user&#39;s date of birth format YYYY-MM-DD. One of either dob or last4ssn is required. |  [optional]
**last4ssn** | **String** | The User&#39;s last four social security number digits. One of either dob or last4ssn is required. If the countryCode is US then, last4ssn or nationalId is required. |  [optional]
**nationalId** | **String** | The national identification number. If the countryCode is US then, last4ssn or nationalId is required. If both nationalId and last4ssn are present then, use only the nationalId. |  [optional]
**emailAddress** | **String** | The user&#39;s email address |  [optional]



