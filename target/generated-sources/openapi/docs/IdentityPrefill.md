

# IdentityPrefill

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**phoneNumber** | **String** | The phone number being queried in standard international format. It should be prefixed with the international dialling code, without the &#39;+&#39;. | 
**dob** | **String** | date of birth |  [optional]
**last4ssn** | **String** | The User&#39;s last four social security number digits. One of either dob or last4ssn is required. If the countryCode is US then, last4ssn or nationalId is required. |  [optional]
**nationalId** | **String** | The national identification number. If the countryCode is US then, last4ssn or nationalId is required. If both nationalId and last4ssn are present then, use only the nationalId. If the countryCode is US then this field must be a numeric value that is 9 digits long. |  [optional]
**countryCode** | **String** | ISO-3166 Alpha-2 standard. | 
**scopedFields** | **List&lt;String&gt;** | The fields to be scoped.  At least one field in the array should be present. | 
**optedInConsentStatus** | **Boolean** | Should be true, validation exception will be thrown if it is false. | 
**performEligibilityCheck** | **Boolean** | If false will not perform an eligibility check |  [optional]



