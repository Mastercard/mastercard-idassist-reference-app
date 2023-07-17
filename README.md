# Mastercard ID Verification Reference Implementation

[![](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](./LICENSE)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_mastercard-idassist-reference&metric=alert_status)](https://sonarcloud.io/dashboard?id=Mastercard_mastercard-idassist-reference)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_mastercard-idassist-reference&metric=coverage)](https://sonarcloud.io/dashboard?id=Mastercard_mastercard-idassist-reference)
[![](https://sonarcloud.io/api/project_badges/measure?project=Mastercard_mastercard-idassist-reference&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=Mastercard_mastercard-idassist-reference)


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

> This reference app consist of the `ID Verification` solution.

## API Reference <a name="api-reference"></a>

- The OpenAPI specification for `idverify` endpoints can be found [here](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference/).

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

   **Connection**

   >`mastercard.api.idVerify.key.file=classpath:<your value>`, this refers to `.p12` file found in the signing key. For example `Mastercard_ID_Assist_Develop-sandbox.p12`.

    **Authentication**
   
    >`mastercard.api.idVerify.consumer.key=`, this refers to your consumer key. Copy it from the "Keys" section on your project page.
    
    >`mastercard.api.idVerify.keystore.alias=keyalias`, this is the default value of key alias. If it is modified, use the updated one from the keys section on your project page.

    >`mastercard.api.keystore.password=keystorepassword`, this is the default value of the keystore password.

    **Encryption**
    
    >`mastercard.api.idVerify.encryption.certificateFile=classpath:<your value>`, this is the path to certificate (.pem) file. For example `mastercard-id-assistClientEnc1593629971.pem` 

    >`mastercard.api.idVerify.encryption.fingerPrint=`, this is fingerprint of encryption key, it is required to encrypt a request. For example `350f0b0268db2ab5b9c105aae77748d99850b773195f378527500c6269a59112`.

    **Decryption**
            
    >`mastercard.api.idVerify.decryption.keystore=classpath:<your value>`, this refers to `.p12` file in Mastercard Encryption Keys. For example `classpath:alias-encryption-mc.p12`

    >`mastercard.api.idVerify.decryption.alias=`, this is the user provided keyalias that is used while creating the API project.

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
0. Exit
1. Document Data Extraction
2. Medicare Card Verification
3. Passport Verification
4. Driving License Verification
5. Document Data Extraction For Web
6. User Identity Retrieval
7. User Identity Verification
8. Trust Score
9. Device Authentication
10. Device Authentication Verification
11. Visa Verification
12. SMS OTP

### Use cases based in the above menu <a name="use-cases"></a>

#### 1. Document Data Extraction 

Return a provider token to be passed to the MIDS Liveness SDK module.
- Details of implementation in class [DataExtractionOption](./src/main/java/com/mastercard/developer/menu/option/DataExtractionOption.java)

-  First endpoint `../idverify/data-extractions/access-tokens` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference), and request data in method `getAccessTokenExample` in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).


-  Second endpoint `../idverify/data-extractions/scans/{scan_id}` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference), and request data constants are `SCAN_ID, USER_CONSENT, RETRIEVE_SELFIE, RETRIEVE_DOCUMENT_IMAGES, RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, USER_SELECTED_COUNTRY`  
   in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).

#### 2. Medicare Card Verification

Medicare cards API used to verify the identification data associated with a user’s Medicare Card with the Identity Verification Provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/medicare-cards` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [MedicalCareOption](./src/main/java/com/mastercard/developer/menu/option/MedicalCareOption.java)
- Request data in method `createSourceVerificationMedicareCardRequestAttributes` in [SourceVerificationMedicareExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationMedicareExample.java).

#### 3. Passport Verification

Verifies the details of a passport document with an identity verification provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/passports` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [PassportSourceVerificationOption](./src/main/java/com/mastercard/developer/menu/option/PassportSourceVerificationOption.java)
- Request data in method `createSourceVerificationPassportRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

#### 4. Driving License Verification

Verifies the details of a driving license document with an identity verification provider.
- Endpoint `../idverify/source-verifications/{issuing_country}/driving-licenses` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [DrivingLicenseSourceVerificationOption](./src/main/java/com/mastercard/developer/menu/option/DrivingLicenseSourceVerificationOption.java)
- Request data in method `createSourceVerificationDrivingLicenseRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

#### 5. Document Data Extraction For Web

The provider token is retrieved by country code and SDK version.
- Endpoint `../idverify/data-extractions/access-tokens` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [DataExtractionForWebOption](./src/main/java/com/mastercard/developer/menu/option/DataExtractionForWebOption.java)
- Request data in method `getAccessTokenExampleForWeb` in [DataExtractionsServiceExample.java](./src/main/java/com/mastercard/developer/example/DataExtractionsServiceExample.java).

#### 6. User Identity Retrieval

- Endpoint `../idverify/user-identities` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [UserIdentityRetrievalOption](./src/main/java/com/mastercard/developer/menu/option/UserIdentityRetrievalOption.java)
- Request data in method `getIdentity` in [UserExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyUserExample.java).

#### 7. User Identity Verification

- Endpoint `../idverify/user-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [UserIdentityVerificationOption](./src/main/java/com/mastercard/developer/menu/option/UserIdentityVerificationOption.java)
- Request data in method `getIdentityVerificationUserInfo` in [UserExample.java](./src/main/java/com/mastercard/developer/example/UserExample.java).

#### 8. Trust Score

- Endpoint `../idverify/trust-scores` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [TrustScoreOption](./src/main/java/com/mastercard/developer/menu/option/TrustScoreOption.java)
- Request data in method `getTrustScoreUserInfo` in [TrustScoreExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyTrustScoreExample.java).

#### 9. Device Authentication

- Endpoint `../idverify/device-authentications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [DeviceAuthenticationOption](./src/main/java/com/mastercard/developer/menu/option/DeviceAuthenticationOption.java)
- Request data in method `getDeviceIpAddress` in [DeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyDeviceAuthenticationExample.java).

#### 10. Device Authentication Verification

- Endpoint `../idverify/device-authentication-verifications` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [DeviceVerificationOption](./src/main/java/com/mastercard/developer/menu/option/DeviceVerificationOption.java)
- Request data in method `getDeviceVerificationFingerprint` in [DeviceAuthenticationExample.java](./src/main/java/com/mastercard/developer/example/IdVerifyDeviceAuthenticationExample.java).

#### 11. Visa Verification

- Endpoint `../idverify/source-verifications/{issuing_country}/passports` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [VisaVerificationOption](./src/main/java/com/mastercard/developer/menu/option/VisaVerificationOption.java)
- Request data in method `createSourceVerificationPassportRequestAttributes` in [SourceVerificationExample.java](./src/main/java/com/mastercard/developer/example/SourceVerificationExample.java).

#### 12. SMS OTP

- `../idverify/sms-otps` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [SmsOptFlowOption](./src/main/java/com/mastercard/developer/menu/option/SmsOptFlowOption.java)
- Request data in method `getSmsOtp` in [SmsOtpExample.java](./src/main/java/com/mastercard/developer/example/SmsOtpExample.java).
- This option also execute `/sms-otp-verifications` endpoint to verify the result from previous request.

#### 13. E-mail OTP

- `../idverify/email-otps` in [API Reference](https://developer.mastercard.com/mastercard-id-assist/documentation/api-reference)
- See details of implementation in class [EmailOptFlowOption](./src/main/java/com/mastercard/developer/menu/option/EmailOtpFlowOption.java)
- Request data in method `getEmailOtp` in [EmailOtpExample.java](./src/main/java/com/mastercard/developer/example/EmailOtpExample.java).
- This option also execute `/email-otp-verifications` endpoint to verify the result from previous request.

## Support <a name="support"></a>
If you would like further information, please send an e-mail to `MC_ID@mastercard.com`
- For information regarding licensing, refer to the [License file](LICENSE.md).
- For copyright information, refer to the [COPYRIGHT.md](COPYRIGHT.md).
- For instructions on how to contribute to this project, refer to the [Contributing file](CONTRIBUTING.md).
- For changelog information, refer to the [CHANGELOG.md](CHANGELOG.md).
