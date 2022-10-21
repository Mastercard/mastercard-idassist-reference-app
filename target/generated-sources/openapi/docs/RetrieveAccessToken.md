

# RetrieveAccessToken

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**countryCode** | **String** | ISO-3166 Alpha-2 standard. | 
**sdkVersion** | **String** | sdk version |  [optional]
**livenessType** | [**LivenessTypeEnum**](#LivenessTypeEnum) | liveness type, GPA takes longer and provides more accuracy, LA is faster |  [optional]
**channelType** | [**ChannelTypeEnum**](#ChannelTypeEnum) | the platform of enrollment process | 
**successUrl** | **String** | This is mandatory in case of WEB only, Not Mandatory for SDK. Do not send these fields in case of SDK channelType |  [optional]
**errorUrl** | **String** | This is mandatory in case of WEB only, Not Mandatory for SDK. Do not send these fields in case of SDK channelType |  [optional]
**locale** | **String** | IETF BCP 47 Format E.g. \&quot;en-GB\&quot; (English - United Kingdom), \&quot;es-MX\&quot; (Spanish - Mexico). More information: Locale ID (LCID) as defined by Microsoft. Not required. The default value is \&quot;en-US\&quot;. |  [optional]



## Enum: LivenessTypeEnum

Name | Value
---- | -----
GPA | &quot;GPA&quot;
LA | &quot;LA&quot;



## Enum: ChannelTypeEnum

Name | Value
---- | -----
WEB | &quot;WEB&quot;
SDK | &quot;SDK&quot;



