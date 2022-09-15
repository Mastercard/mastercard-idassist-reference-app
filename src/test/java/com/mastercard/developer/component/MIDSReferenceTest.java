package com.mastercard.developer.component;

import com.mastercard.developer.service.DataExtractionsService;
import com.mastercard.developer.service.DeviceAuthenticationService;
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
    private SmsOtpService smsOtpServiceMock;

    @Mock
    private DeviceAuthenticationService deviceAuthenticationServiceMock;

    @Mock
    private DataExtractionsService dataExtractionsServiceMock;

    @Mock
    private SourceVerificationService sourceVerificationServiceMock;

    @Mock
    private MedicareSourceVerificationService medicareSourceVerificationServiceMock;

    @Test
    void performUserIdentityRetrieval_successfulResponse() {
        midsReference.performUserIdentityRetrieval();

        verify(userServiceMock, times(1)).create(any());
    }

    @Test
    void performUserIdentityVerification_successfulResponse() {
        midsReference.performUserIdentityVerification();

        verify(userServiceMock, times(1)).createUserVerification(any());
    }

    @Test
    void smtpService_createAndVerifyOtp_successfulResponse() {
        midsReference.performSmsOtpFlow();

        verify(smsOtpServiceMock, times(1)).createOtpsSMS(any());
        verify(smsOtpServiceMock, times(1)).createVerifyOtps(any());
    }

    @Test
    void trustScoreService_performTrustScore_successfulResponse() {
        midsReference.performTrustScore();

        verify(userServiceMock, times(1)).trust(any());
    }

    @Test
    void deviceAuthentication_createAndVerifyDeviceAuthentication_successfulResponse() {
        midsReference.performDeviceAuthentication();

        verify(deviceAuthenticationServiceMock, times(1)).createDeviceAuthentication(any());
        verify(deviceAuthenticationServiceMock, times(1)).createDeviceAuthenticationVerification(any());
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
}
