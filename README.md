# Mastercard Id Assist Reference Implementation

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](./LICENSE)

## Table of Contents
- [Overview](#overview)
  * [Compatibility](#compatibility)
  * [References](#references)
- [Usage](#usage)
  * [Prerequisites](#prerequisites)
  * [Configuration](#configuration)
  * [Integrating with OpenAPI Generator](#integrating-with-openapi-generator)
  * [Running The Project](#running-the-project)
- [Use Cases](#use-cases)
- [API Reference](#api-reference)
  * [Authorization](#authorization)
  * [Encryption and Decryption](#encryption-decryption)
  * [Request Examples](#request-examples)
  * [Recommendation](#recommendation)
- [Support](#support)
- [License](#license)

## Overview <a name="overview"></a>
Mastercard ID provides the technology platform and operational service to allow the secure storage and transmission of Digital Identity data from the user to the Relying Party having been verified to a required level of assurance by an Identity Verification Provider. Please see here for more details on the API: [Mastercard Developers](https://developer.mastercard.com/mastercard-id-assist/documentation/).

### Compatibility <a name="compatibility"></a>
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or later

### References <a name="references"></a>
* [Mastercard’s OAuth Signer library](https://github.com/Mastercard/oauth1-signer-java)
* [Using OAuth 1.0a to Access Mastercard APIs](https://developer.mastercard.com/platform/documentation/using-oauth-1a-to-access-mastercard-apis/)

## Usage <a name="usage"></a>
### Prerequisites <a name="prerequisites"></a>
* [Mastercard Developers Account](https://developer.mastercard.com/dashboard) with access to Mastercard ID Assist API
* A text editor or IDE
* [Spring Boot 2.2+](https://spring.io/projects/spring-boot)
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi)
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.
* SDK Implementation is required for APIs I, J, K, L listed in the Use Cases session, as found on [SDK Link](https://developer.mastercard.com/mastercard-id-verification/documentation/tutorials-and-guides). 
* A valid phone number that can retrieve the OTP code is required for API D. 
* The OTP code received in a valid phone number, as provided in API D is required as a parameter for successful validation of the code on API E. 

### Configuration <a name="configuration"></a>

1. Create an account at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).  
2. Create a new project and add `Mastercard ID Assist` API to your project.   
3. Configure the project and download all the keys. It will download multiple files.  
4. Select all `.p12` files, `.pem` file and copy it to `src/main/resources` in the project folder.
5. Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
    
    >`mastercard.api.base.path=corresponding MC ID Assist URL`, for example : `https://sandbox.api.mastercard.com/mcidassist`, it´s a static field, this will be used as a host to make API calls.
    
    **Below properties will be required for authentication of API calls.**
    
    >`mastercard.api.key.file=`, this refers to `.p12` file found in the signing key. Please place `.p12` file at `src\main\resources` in the project folder and add classpath for the `.p12` file.
    
    >`mastercard.api.consumer.key=`, this refers to your consumer key. Copy it from the "Keys" section on your project page.
      
    >`mastercard.api.keystore.alias=keyalias`, this is the default value of key alias. If it is modified, use the updated one from the keys section on your project page.
    
    >`mastercard.api.keystore.password=keystorepassword`, this is the default value of the keystore password. If it is modified, use the updated one from the keys section on the project page.

    **Encryption**
    
     >`mastercard.client.encryption.enable=`, this is the flag that will enable encryption payload request.  
        `mastercard.client.encryption.enable=false` 
    
    >`mastercard.api.encryption.certificateFile=`, this is the path to certificate (.pem) file. Add classpath for .crt file, after placing it at `src\main\resources` in the project folder.  
        `mastercard.api.encryption.certificateFile=classpath:`
    
    >`mastercard.api.encryption.fingerPrint=`, this is the encryption key, it is required to encrypt a request.
    
    **Decryption**
    
    >`mastercard.client.decryption.enable=`, this is the flag that will disable encryption payload request.  
     `mastercard.client.decryption.enable=false` 
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
    >`mastercard.api.decryption.keystore=`, copy the `.p12` file in `src/main/resources` and set value as `classpath:keyalias-encryption-mc.p12`
        
    >`mastercard.api.decryption.alias=`, this is the user provided keyalias that is used while creating the API project.
        
    >`mastercard.api.decryption.keystore.password=`, this is the password provided while creating the API project.


### Integrating with OpenAPI Generator <a name="integrating-with-openapi-generator"></a>
[OpenAPI Generator](https://github.com/OpenAPITools/openapi-generator) generates API client libraries from [OpenAPI Specs](https://github.com/OAI/OpenAPI-Specification). 
It provides generators and library templates for supporting multiple languages and frameworks.

See also:
* [OpenAPI Generator (maven Plugin)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin)
* [OpenAPI Generator (executable)](https://mvnrepository.com/artifact/org.openapitools/openapi-generator-cli)
* [CONFIG OPTIONS for java](https://github.com/OpenAPITools/openapi-generator/blob/master/docs/generators/java.md)

#### OpenAPI Generator Plugin Configuration
```xml
<!-- https://mvnrepository.com/artifact/org.openapitools/openapi-generator-maven-plugin -->
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>${openapi-generator.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/mastercard-idassist-reference_api_spec.yaml</inputSpec>
                <generatorName>java</generatorName>
                <library>okhttp-gson</library>
                <generateApiTests>false</generateApiTests>
                <generateModelTests>false</generateModelTests>
                <configOptions>
                    <sourceFolder>src/gen/main/java</sourceFolder>
                    <dateLibrary>java8</dateLibrary>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

#### Generating The API Client Sources
Now that you have all the dependencies you need, you can generate the sources. To do this, use one of the following two methods:

`Using IDE`
* **Method 1**<br/>
  In IDE, open the Maven window **(View > Tool Windows > Maven)**. Click the icons `Reimport All Maven Projects` and `Generate Sources and Update Folders for All Projects`

* **Method 2**<br/>

  In the same menu, navigate to the commands **({Project name} > Lifecycle)**, select `clean` and `compile` then click the icon `Run Maven Build`. 

`Using Terminal`
* Navigate to the root directory of the project within a terminal window and execute `mvn clean compile` command.

### Running the Project <a name="running-the-project"></a>
* Firstly, download the project into a folder of your preference through this link. [Reference Application](https://static.developer.mastercard.com/content/mastercard-id-assist/mastercard-idassist-reference.zip)
* Follow the steps on this link to generate your API Key and fill in the configuration parameters in your project. [Tutorial Link](https://developer.mastercard.com/platform/documentation/security-and-authentication/generating-and-configuring-a-mastercard-api-client/)
* Navigate to the `com.mastercard.dis.mids.reference` package and right-click to run `MIDSReferenceApplication`
* After that you can see all the menu options, such as:
1)   User Identity Retrieval
2)   User Identity Verification
3)   TrustScore
4)   SMS OTP
5)   Device Authentication
6)   Document Data Extraction
7)   Medicare Card Verification
8)   Passport Verification
9)   Driving License Verification
10)  Exit

### Use cases
Main use cases in Mastercard Id Assist Reference APIs are Device Authentication, Create otps - SMS, Device Authentication Verification,Identity Verification API,Identity API  and Verify SMS OTP. Below are few variations.   

Below are the different APIs available in Mastercard Id Assist Reference application:

Note: Below use-cases have sample requests and responses just for reference.

A] [Identity API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#user-identities):   

- This API will provide information about an individual user with one of the following: 
    - Phone Number and Last 4 Digits of SSN
    - Phone Number and National ID
    - Phone Number and Date of Birth
- Please refer to `userProfileOperations` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.

    URL      : /user-identities

    Request  : [IdentityPrefill](./docs/IdentityPrefill.md) 

    Response : [Identity](./docs/Identity.md) 
    
B] [Identity Verification API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#user-verifications):   

- Verifies user entered Personally Identifiable Information (PII) by returning a true/false or matching score per attribute along with an overall trust score for the record"
- Please refer to `userProfileOperations` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.
    
    URL      : /user-verifications

    Request  : [IdentityVerificationUserInfo](./docs/IdentityVerificationUserInfo.md) 

    Response : [IdentityVerificationResponse](./docs/IdentityVerification.md) 

C] [Trust Score API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#trust-score):   

- This API will provide the trust score for a user: 
- Please refer to `performTrustScore` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.

    URL      : /trust-score

    Request  : [TrustScoreUserInfo](./docs/TrustScoreUserInfo.md) 

    Response : [TrustScore](./docs/TrustScore.md) 

D] [SMS OTP API](https://developer.mastercard.com/send-cross-border/documentation/parameters/#sms-otps):   

- Create and Send a One-Time Passcode (OTP) via SMS to the phone number provided.
- Please refer to `performEnrollment` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.
    
    URL      : /sms-otps
    
    Request  : [SMSOTPGeneration](./docs/SMSOTPGeneration.md)
    
    Response : [SMSOTP](./docs/SMSOTP.md)

E] [SMS OTP Verification API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#sms-otp-verifications):   

- Verify that the provided code matches One-Time Passcode (OTP) sent via SMS during `/sms-otps`
- Please refer to `performEnrollment` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.
- This is dependent on the SMS OTP API. The ``otpId`` field from the SMS OTP API response should be used in this method request.

    URL      : /sms-otp-verifications
    
    Request  : [SMSOTPVerification](./docs/SMSOTPVerification.md)
    
    Response : [SMSOTPVerificationResult](./docs/SMSOTPVerificationResult.md)
    
F] [Device Authentication API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#device-authentications):

- Use this API to retrieve a phone number for a device ``verificationFingerprint``. The ``verificationFingerprint`` is retrieved by calling the ``redirectTargetUrl`` returned from ``/device-authentications``"
- This API provides a ``redirectTargetUrl`` for a given device IP Address. The ``redirectTargetUrl`` can be used to retrieve a ``verificationFingerprint`` which is used for verification in ``/device-authentication-verifications``.      
- Refer to `performDeviceAuthentication` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.  
    
    URL      : /device-authentications 

    Request  : [DeviceIpAddress](./docs/DeviceIpAddress.md) 

    Response : [DeviceAuthenticationVerificationUrl](./docs/DeviceAuthenticationVerificationUrl.md) 

G] [Device Authentication Verification API](https://developer.mastercard.com/mastercard-id-assist/documentation/#device-authentication-verifications):   

- This API provides a ``redirectTargetUrl`` for a given device IP Address. The ``redirectTargetUrl`` can be used to retrieve a ``verificationFingerprint`` which is used for verification in ``/device-authentication-verifications``
- Please refer to `performDeviceAuthentication` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details.
- This is dependent on the Device Authentication API. The ``verificationFingerprint`` field from the Device Authentication API response should be used in this method request.

    URL      : /device-authentication-verifications

    Request  : [DeviceVerificationFingerprint](./docs/DeviceVerificationFingerprint.md) 

    Response : [DevicePhoneNumber](./docs/DevicePhoneNumber.md) 
    
H] [Data Extraction Access Token API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#data-extractions/access-tokens)

- This API allows 

- Please refer to `performDataExtraction` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details from attached reference application.

    URL         :   /data-extractions/access-tokens

    Request     :   [DataExtractionAccessTokenRequest](./docs/DataExtractionAccessTokenRequest.md) 

    Response    :   [DataExtractionAccessTokenResponse](./docs/DataExtractionAccessTokenResponse.md) 

I] [Document Verifications API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#data-extractions/scans/{scan_id})

- This API allows 

- Please refer to `performDataExtraction` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details from attached reference application.

    URL         :   /data-extractions/scans/{scan_id}

    Request     :   [ScannedDataExtractionRequest](./docs/ScannedDataExtractionRequest.md) 

    Response    :   [ScannedDataExtractionResponse](./docs/ScannedDataExtractionResponse.md) 
    
J] [Source Verifications API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#source-verifications/{issuing_country}/passports)

- This API allows 

- Please refer to `performSourceVerification` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details from attached reference application.

    URL         :   /source-verifications/{issuing_country}/passports

    Request     :   [SourceVerificationPassportRequest](./docs/SourceVerificationPassportRequest.md) 

    Response    :   [SourceVerificationPassportResponse](./docs/SourceVerificationPassportResponse.md) 

K] [Source Verifications API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#source-verifications/{issuing_country}/driving-licenses)

- This API allows 

- Please refer to `performSourceVerification` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details from attached reference application.

    URL         :   /source-verifications/{issuing_country}/driving-licenses

    Request     :   [SourceVerificationDrivingLicenceRequest](./docs/SourceVerificationDrivingLicenceRequest.md) 

    Response    :   [SourceVerificationDrivingLicenceResponse](./docs/SourceVerificationDrivingLicenceResponse.md) 

L] [Medicare Card API](https://developer.mastercard.com/mastercard-id-assist/documentation/parameters/#source-verifications/{issuing_country}/medicare-cards)

- This API allows a user to verify their identity with their Medicare Card against the Australian Document Verification Service(DVS).

- Please refer to `performMedicalCare` in [Application.java](./src\main\java\com\mastercard\developer\Application.java) for details from attached reference application.

    URL         :   /source-verifications/{issuing_country}/medicare-cards

    Request     :   [SourceVerificationMedicareCardRequest](./docs/SourceVerificationMedicareCardRequest.md) 

    Response    :   [SourceVerificationMedicareCardResponse](./docs/SourceVerificationMedicareCardResponse.md) 

## API Reference <a name="api-reference"></a>

- To develop a client application that consumes a RESTful ID Assist API with Spring Boot, refer to the documentation below.
- [Mastercard ID Assist Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference/).

### Authorization <a name="authorization"></a>
The package named `com.mastercard.developer.config` will provide you access to configure the API client accordingly to user keys. This class will take care of adding the correct `Authorization` header before sending the request.

### Encryption and Decryption <a name="encryption-decryption"></a>
The package named `com.mastercard.developer.interceptors` will provide you access to the interceptor class which used in the API client. This class will encrypt the request body and add the encrypted text as a value to 'encryptedData' JSON property in the request body before sending the request and decrypt the 'encryptedData' JSON property value from the response body.

### Request Examples <a name="request-examples"></a>
You can change the default input passed to APIs, modify values in following files,
* `com.mastercard.developer.example`

### Recommendation <a name="recommendation"></a>
It is recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Support <a name="support"></a>
If you would like further information, please send an email to `IDassistpilothelp@mastercard.com`
- For information regarding licensing, refer to the [License file](LICENSE.md).
- For copyright information, refer to the [COPYRIGHT.md](COPYRIGHT.md).
- For instructions on how to contribute to this project, refer to the [Contributing file](CONTRIBUTING.md).
- For changelog information, refer to the [CHANGELOG.md](CHANGELOG.md).
