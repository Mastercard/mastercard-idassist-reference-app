

# IdentityVerification

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transactionId** | **String** | A random 128-bit. UUID representing the MIDS transaction |  [optional]
**name** | [**IdentityVerificationName**](IdentityVerificationName.md) |  |  [optional]
**address** | [**IdentityVerificationAddress**](IdentityVerificationAddress.md) |  |  [optional]
**identifiers** | [**IdentityVerificationIdentifiers**](IdentityVerificationIdentifiers.md) |  |  [optional]
**email** | [**IdentityVerificationEmail**](IdentityVerificationEmail.md) |  |  [optional]
**trustScore** | **Integer** | The user&#39;s trust score |  [optional]
**phoneNumber** | **String** | The user&#39;s phone number |  [optional]
**reasonCodes** | **List&lt;String&gt;** | An array of indicators that provide additional context about the Transaction. For complete list. |   Reason Code |   Description | |:--------------|:------------------| |AC|Normalized address was used to complete empty address fields prior to match.| |AU|Address Undeliverable.| |BA|Business Address.| |BL|Mobile Business Line.| |CN|First and Last name combined in one field.| |DA|Dual address (Ex. 123 Main St PO Box 99).| |DI|Death Indicator found on data.| |DT|Data retrieval timeout (verify API only).| |DV|High device change velocity.| |FN|Family name found and used in matching.| |GL|Mobile Government Line.| |HR|High-rise; address contains apartment or building sub-units.| |HV|High velocity of change events associated with phone.| |IA|Inactive address.| |LA|Low Tenure Address.| |LP|Low Tenure Device.| |LS|Low Tenure SIM.| |LT|Low Tenure Mobile Identity.| |MA|Address in the request associated with multiple active addresses.| |MI|Military address.| |NA|Address is valid and has been normalized prior to calculating the match score.| |NC|Name &amp; Address information is not available.| |ND|Network Status information is not available.| |NM|Not a mobile line type.| |NN|Nick name found and used in matching. For example Bill matches with William.| |NP|Non personal line.| |NS|Names were swapped (first/last).| |NU|Phone number has been updated.| |NV|Phone number not valid.| |OL|How long the identity has been associated to a phone number. Long Tenure &gt; 90 days.| |OS|How long the identity has been associated to a phone number. Short Tenure between 8 and 90 days.| |OU|The date attributes associated to the phone number is not available. Ownership Tenure is Unknown.| |OV|How long the identity has been associated to a phone number. Very Short &lt; 7 days.| |P3|Postal code submitted matched first 3 digits.| |P5|Postal code submitted matched first 5 digits.| |P9|Postal code submitted matched first 9 digits.| |PM|Address associated with a Private Mailbox operator (Ex. UPS Store).| |PN|Mobile Phone number is not active.| |PO|Address is a PO Box.| |PT|Phone Number is currently in a ported state.| |RA|Raw Address matched better than normalized Address.| |RL|Phone Number is associated with a high-risk linetype (Non-Fixed &#x60;VoIP&#x60; or Prepaid &#x60;MVNO&#x60;).| |RM|Matching used only Raw data.| |SA|Sub-account line.| |UC|Insufficient data to calculate trustScore.| |UV|Unable to verify address.| |VA|Address is vacant (unoccupied in the past 90 days).| More details see [link](http://docs.payfone.com/v1.0/reference#reason-codes)  |  [optional]
**verified** | **Boolean** | Whether a user identity is verified |  [optional]
**lineType** | **String** | The line type. It can be either &#x60;&#x60;Mobile&#x60;&#x60;, &#x60;&#x60;Landline&#x60;&#x60;, &#x60;&#x60;FixedVoIP&#x60;&#x60; or &#x60;&#x60;NonFixedVoIP&#x60;&#x60; |  [optional]



