# Mastercard ID Verification Reference Implementation

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](./LICENSE)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_client-encryption-ruby&metric=alert_status)](https://sonarcloud.io/dashboard?id=mastercard-idassist-reference)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_client-encryption-ruby&metric=coverage)](https://sonarcloud.io/dashboard?id=mastercard-idassist-reference)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_client-encryption-ruby&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=mastercard-idassist-reference)
[![](https://img.shields.io/badge/license-MIT-yellow.svg)](https://github.com/Mastercard/mastercard-idassist-reference/blob/master/LICENSE)

## Table of Contents
- [Overview](#overview)
- [API Reference](#api-reference)
- [Usage](#usage)
    * [Prerequisites](#prerequisites)
    * [Configuration](#configuration)
    * [Compiling and Running](#compiling-and-running)
- [Use Cases](#use-cases)
- [Support](#support)

## Overview <a name="overview"></a>
This is a reference application to demonstrate how ID Verification API can be used.

> This reference app uses two endpoints `mcidassist` and `idverify` which together consist of the `ID Verification` solution.

## API Reference <a name="api-reference"></a>

- The OpenAPI specification with `mcidassist` and `idverify` endpoints can be found [here](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference/).

## Usage <a name="usage"></a>

### Prerequisites <a name="prerequisites"></a>
* [Mastercard Developers Account](https://developer.mastercard.com/dashboard) with access to ID Verification API.
* A text editor or any other IDE of your choice.
* [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html).
* [Apache Maven 3.3+](https://maven.apache.org/download.cgi).
* Set up the `JAVA_HOME` environment variable to match the location of your Java installation.
* Set up the `MAVEN_HOME` environment variable to match the location of your maven installation (necessary when running from terminal / command line).
* A valid phone number that can retrieve the OTP code is required for the menu item [4 - SMS OTP]. 
  
> This `README.md` is inside the project, if necessary download and unzip the project Mastercard ID Verification Reference through this link [Reference Application](https://static.developer.mastercard.com/content/mastercard-id-assist/mastercard-idassist-reference.zip).

### Configuration <a name="configuration"></a>

1. Create an account or log in at [Mastercard Developers](https://developer.mastercard.com/account/sign-up).
2. Create a new project and add `ID Verification` API to your project.
3. Configure the project and download all the keys. It will download multiple files (in case of zip files extract all).
4. Download `.pem` file from the `Client Encryption Keys` section, open the `actions` combobox and click `download encryption`. 
5. Select all `.p12` files, `.pem` file and copy it to `src/main/resources` in the project folder.
6. Open `${project.basedir}/src/main/resources/application.properties` and configure below parameters.
7. The same keys can be used for `ID Assist`.

   **Connection**

   >`mastercard.api.idAssist.key.file=classpath:<your value>`, this refers to `.p12` file found in the signing key. For example `Mastercard_ID_Assist_Develop-sandbox.p12`.

   >`mastercard.api.idVerify.key.file=classpath:<your value>`, this refers to `.p12` file found in the signing key. For example `Mastercard_ID_Assist_Develop-sandbox.p12`.

    **Authentication**
   
    >`mastercard.api.idAssist.consumer.key=`, this refers to your consumer key. Copy it from the "Keys" section on your project page.

    >`mastercard.api.idVerify.consumer.key=`, this refers to your consumer key. Copy it from the "Keys" section on your project page.

    >`mastercard.api.idAssist.keystore.alias=keyalias`, this is the default value of key alias. If it is modified, use the updated one from the keys section on your project page.
    
    >`mastercard.api.idVerify.keystore.alias=keyalias`, this is the default value of key alias. If it is modified, use the updated one from the keys section on your project page.

    >`mastercard.api.keystore.password=keystorepassword`, this is the default value of the keystore password.

    >`mastercard.api.keystore.password=keystorepassword`, this is the default value of the keystore password.

    **Encryption**
    
    >`mastercard.api.idAssist.encryption.certificateFile=classpath:<your value>`, this is the path to certificate (.pem) file. For example `mastercard-id-assistClientEnc1593629971.pem` 
     
    >`mastercard.api.idVerify.encryption.certificateFile=classpath:<your value>`, this is the path to certificate (.pem) file. For example `mastercard-id-assistClientEnc1593629971.pem` 

    >`mastercard.api.idAssist.encryption.fingerPrint=`, this is fingerprint of encryption key, it is required to encrypt a request. For example `350f0b0268db2ab5b9c105aae77748d99850b773195f378527500c6269a59112`.

    >`mastercard.api.idVerify.encryption.fingerPrint=`, this is fingerprint of encryption key, it is required to encrypt a request. For example `350f0b0268db2ab5b9c105aae77748d99850b773195f378527500c6269a59112`.

    **Decryption**
    
    >`mastercard.api.idAssist.decryption.keystore=classpath:<your value>`, this refers to `.p12` file in Mastercard Encryption Keys. For example `classpath:alias-encryption-mc.p12`
        
    >`mastercard.api.idVerify.decryption.keystore=classpath:<your value>`, this refers to `.p12` file in Mastercard Encryption Keys. For example `classpath:alias-encryption-mc.p12`

    >`mastercard.api.idAssist.decryption.alias=`, this is the user provided keyalias that is used while creating the API project.

    >`mastercard.api.idVerify.decryption.alias=`, this is the user provided keyalias that is used while creating the API project.

    >`mastercard.api.idAssist.decryption.keystore.password=`, this is the password provided while creating the API project.

    >`mastercard.api.idVerify.decryption.keystore.password=`, this is the password provided while creating the API project.


### Compiling and Running <a name="compiling-and-running"></a>

Use one of the following two methods:

#### Using IDE
  In the same menu, navigate to the commands **({Project name} > Lifecycle)**, select `clean` and `compile` then click the icon `Run Maven Build`.

#### Using Terminal
* Navigate to the root directory of the project within a terminal command line window and execute `mvn clean compile install -DskipTests=true` command.

#### Running the Project

#### Using IDE
* Navigate to the `com.mastercard.developer` package, open `Application.java` and right-click to run.

#### Using Terminal
* Navigate to the root directory of the project within a terminal command line window and execute `mvn spring-boot:run`.

#### After that you can see all the menu options, such as:
1.   User Identity Retrieval
2.   User Identity Verification
3.   TrustScore
4.   SMS OTP
5.   Device Authentication
6.   Document Data Extraction
7.   Medicare Card Verification
8.   Passport Verification
9.   Driving License Verification
10.  Document Data Extraction For Web
11.  'ID Verify' User Identity Retrieval
12.  'ID Verify' User Identity Verification
13.  'ID Verify' Trust Score
14.  'ID Verify' Device Authentication
15.  'ID Verify' Device Authentication Verification
16.  'ID Verify' visa Verification
17.  Exit

### Use cases based in the above menu <a name="use-cases"></a>

#### 1. User Identity Retrieval 

This API provides information about an individual user either with the last 4 digits of SSN or National ID.
- Endpoint `../mcidassist/user-identities` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performUserIdentityRetrieval` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getIdentity` in [UserExample.java](./src/main/java/com/mastercard/developer/example/UserExample.java)

#### 2. User Identity Verification

Verifies user entered Personally Identifiable Information (PII) by returning a true/false or matching score per attribute along with an overall trust score for the record.
- Endpoint `../mcidassist/user-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performUserIdentityVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getIdentityVerificationUserInfo` in [UserExample.java](./src/main/java/com/mastercard/developer/example/UserExample.java)

#### 3. TrustScore

This API will provide trust information about an individual user.
- Endpoint `../mcidassist/trust-score` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performTrustScore` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getTrustScoreUserInfo` in [TrustScoreExample.java](./src/main/java/com/mastercard/developer/example/TrustScoreExample.java)

#### 4. SMS OTP

Create and Send a One-Time Passcode (OTP) via SMS to the phone number provided, and verify that the provided code matches One-Time Passcode (OTP) sent via SMS during `/sms-otps`.   
- details of implementation in method `performSmsOtpFlow` in [Application.java](./src/main/java/com/mastercard/developer/Application.java).

-  First endpoint `../mcidassist/sms-otps` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference), and request data in methods `getSmsOtp` in [SmsOtpExample.java](./src/main/java/com/mastercard/developer/example/SmsOtpExample.java).

-  Second endpoint `../mcidassist/sms-otp-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference),  and request data in methods `getOtpVerification` in [SmsOtpExample.java](./src/main/java/com/mastercard/developer/example/SmsOtpExample.java).

#### 5. Device Authentication 

It provides a `redirectTargetUrl` for a given device IP Address. The `redirectTargetUrl` can be used to retrieve
a `verificationFingerprint` which is used to retrieve a phone number for a device `verificationFingerprint`. 

- Details of implementation in method `performDeviceAuthentication` in [Application.java](./src/main/java/com/mastercard/developer/Application.java).

-  First endpoint `../mcidassist/device-authentications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference),and request data in methods `getDeviceIpAddress` in [DeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/DeviceAuthenticationExample.java).


-  Second endpoint `../mcidassist/device-authentication-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference),and request data in methods `getDeviceVerificationFingerprint` in [DeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/DeviceAuthenticationExample.java).

#### 6. Document Data Extraction 

Return a provider token to be passed to the MIDS Liveness SDK module.
- Details of implementation in method `performDataExtraction` in [Application.java](./src/main/java/com/mastercard/developer/Application.java).

-  First endpoint `../idverify/data-extractions/access-tokens` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference), and request data in method `getAccessTokenExample` in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).


-  Second endpoint `../idverify/data-extractions/scans/{scan_id}` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference), and request data constants are `SCAN_ID, USER_CONSENT, RETRIEVE_SELFIE, RETRIEVE_DOCUMENT_IMAGES, RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, USER_SELECTED_COUNTRY`  
   in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).

#### 7. Medicare Card Verification

Medicare cards API used to verify the identification data associated with a user’s Medicare Card with the Identity Verification Provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/medicare-cards` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performMedicalCare` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `createSourceVerificationMedicareCardRequestAttributes` in [SourceVerificationMedicareExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationMedicareExample.java).

#### 8. Passport Verification

Verifies the details of a passport document with an identity verification provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/passports` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performPassportSourceVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `createSourceVerificationPassportRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

#### 9. Driving License Verification

Verifies the details of a driving license document with an identity verification provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/driving-licenses` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performDrivingLicenseSourceVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `createSourceVerificationDrivingLicenseRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

#### 10. Document Data Extraction For Web

The provider token is retrieved by country code and SDK version.
- Endpoint `../idverify/data-extractions/access-tokens` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performDataExtractionForWeb` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getAccessTokenExampleForWeb` in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).

#### 11. 'ID Verify' User Identity Retrieval

- Endpoint `../idverify/user-identities` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performIdVerifyUserIdentityRetrieval` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getIdentity` in [IdVerifyUserExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyUserExample.java).

#### 12. 'ID Verify' User Identity Verification

- Endpoint `../idverify/user-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performIdVerifyUserIdentityVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getIdentityVerificationUserInfo` in [UserExample.java](./src/main/java/com/mastercard/developer/example/UserExample.java).

#### 13. 'ID Verify' Trust Score

- Endpoint `../idverify/trust-scores` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performIdVerifyTrustScore` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getTrustScoreUserInfo` in [IdVerifyTrustScoreExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyTrustScoreExample.java).

#### 14. 'ID Verify' Device Authentication

- Endpoint `../idverify/device-authentications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performIdVerifyDeviceAuthentication` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getDeviceIpAddress` in [IdVerifyDeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyDeviceAuthenticationExample.java).

#### 15. 'ID Verify' Device Authentication Verification

- Endpoint `../idverify/device-authentication-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performIdVerifyDeviceAuthenticationVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `getDeviceVerificationFingerprint` in [IdVerifyDeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyDeviceAuthenticationExample.java).

#### 16. 'ID Verify' visa Verification

- Endpoint `../idverify/source-verifications/{issuing_country}/passports` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in method `performVisaSourceVerification` in [Application.java](./src/main/java/com/mastercard/developer/Application.java)
- Request data in method `createSourceVerificationPassportRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

## Support <a name="support"></a>
If you would like further information, please send an email to `MC_ID@mastercard.com`
- For information regarding licensing, refer to the [License file](LICENSE.md).
- For copyright information, refer to the [COPYRIGHT.md](COPYRIGHT.md).
- For instructions on how to contribute to this project, refer to the [Contributing file](CONTRIBUTING.md).
- For changelog information, refer to the [CHANGELOG.md](CHANGELOG.md).
