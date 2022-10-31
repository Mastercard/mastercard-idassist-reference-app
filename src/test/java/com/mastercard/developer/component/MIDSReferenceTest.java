package com.mastercard.developer.component;

import com.mastercard.developer.service.DataExtractionsService;
import com.mastercard.developer.service.DeviceAuthenticationService;
import com.mastercard.developer.service.IdVerifyDeviceAuthenticationService;
import com.mastercard.developer.service.IdVerifyUserService;
import com.mastercard.developer.service.MedicareSourceVerificationService;
import com.mastercard.developer.service.SmsOtpService;
import com.mastercard.developer.service.SourceVerificationService;
import com.mastercard.developer.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MIDSReferenceTest {

    @InjectMocks
    private MIDSReference midsReference;

    @Mock
    private UserService userServiceMock;

    @Mock
    private IdVerifyUserService idVerifyUserServiceMock;

    @Mock
    private SmsOtpService smsOtpServiceMock;

    @Mock
    private DeviceAuthenticationService deviceAuthenticationServiceMock;

    @Mock
    private IdVerifyDeviceAuthenticationService idVerifyDeviceAuthenticationServiceMock;

    @Mock
    private DataExtractionsService dataExtractionsServiceMock;

    @Mock
    private SourceVerificationService sourceVerificationServiceMock;

    @Mock
    private MedicareSourceVerificationService medicareSourceVerificationServiceMock;

    @Test
    void performUserIdentityRetrieval_successfulResponse() {
        midsReference.performIdAssistUserIdentityRetrieval();

        verify(userServiceMock, times(1)).create(any());
    }

    @Test
    void performUserIdentityVerification_successfulResponse() {
        midsReference.performIdAssistUserIdentityVerification();

        verify(userServiceMock, times(1)).createUserVerification(any());
    }

    @Test
    void performIdAssistSmtpService_createAndVerifyOtp_successfulResponse() {
        midsReference.performIdAssistSmsOtpFlow();

        verify(smsOtpServiceMock, times(1)).createOtpsSMS(any());
        verify(smsOtpServiceMock, times(1)).createVerifyOtps(any());
    }

    @Test
    void performIdAssistTrustScoreService_performTrustScore_successfulResponse() {
        midsReference.performIdAssistTrustScore();

        verify(userServiceMock, times(1)).trust(any());
    }

    @Test
    void performIdAssistDeviceAuthentication_createAndVerifyDeviceAuthentication_successfulResponse() {
        midsReference.performIdAssistDeviceAuthentication();

        verify(deviceAuthenticationServiceMock, times(1)).createDeviceAuthentication(any());
        verify(deviceAuthenticationServiceMock, times(1)).createDeviceAuthenticationVerification(any());
    }

    @Test
    void performIdVerifyUserIdentityRetrieval_successfulResponse() {
        midsReference.performIdVerifyUserIdentityRetrieval();

        verify(idVerifyUserServiceMock, times(1)).userIdentity(any());
    }

    @Test
    void performIdVerifyUserIdentityVerification_successfulResponse() {
        midsReference.performIdVerifyUserIdentityVerification();

        verify(idVerifyUserServiceMock, times(1)).identityVerification(any());
    }

    @Test
    void performIdVerifytrustScoreService_performTrustScore_successfulResponse() {
        midsReference.performIdVerifyTrustScore();

        verify(idVerifyUserServiceMock, times(1)).userTrustScore(any());
    }

    @Test
    void performIdVerifydeviceAuthentication_createAndVerifyDeviceAuthentication_successfulResponse() {
        midsReference.performIdVerifyDeviceAuthentication();

        verify(idVerifyDeviceAuthenticationServiceMock, times(1)).deviceAuthentication(any());
    }

    @Test
    void performIdVerifydeviceAuthenticationVerification_createAndVerifyDeviceAuthentication_successfulResponse() {
        midsReference.performIdVerifyDeviceAuthenticationVerification();

        verify(idVerifyDeviceAuthenticationServiceMock, times(1)).deviceAuthenticationVerification(any());
    }

    @Test
    void dataExtraction_extractAccessTokenAndScannedDocumentData_successfulResponse() {
        midsReference.performDataExtraction();

        verify(dataExtractionsServiceMock, times(1)).dataExtractionAccessToken(any());
    }


    @Test
    void performDrivingLicenseSourceVerification_successfulResponse() {
        midsReference.performDrivingLicenseSourceVerification();

        verify(sourceVerificationServiceMock, times(1)).sourceVerificationDrivingLicense(any(), any());
    }

    @Test
    void performPassportSourceVerification_successfulResponse() {
        midsReference.performPassportSourceVerification();

        verify(sourceVerificationServiceMock, times(1)).sourceVerificationPassport(any(), any());
    }

    @Test
    void medicalCareVerification_verifyAgainstDriverLicenseAndPassport_successfulResponse() {
        midsReference.performMedicalCare();

        verify(medicareSourceVerificationServiceMock, times(1)).createMedicareCardRequest(any(), any());
    }

    @Test
    void dataExtraction_extractAccessTokenForWeb_successfulResponse() {
        midsReference.performDataExtractionForWeb();

        verify(dataExtractionsServiceMock, times(1)).dataExtractionAccessTokenForWeb(any());
    }

    @Test
    void performVisaSourceVerification_successfulResponse() {
        midsReference.performVisaSourceVerification("CAN", "AUS");
       verify(sourceVerificationServiceMock, times(1)).sourceVerificationPassport(any(), any());
    }
}
