package com.mastercard.developer.interceptor;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EncryptionDecryptionExceptionTest {

    @Test
    void EncryptionDecryptionException_Test() {
        Exception cause = new Exception();

        EncryptionDecryptionException encryptionDecryptionException = new EncryptionDecryptionException("test", cause);
        Assertions.assertNotNull(encryptionDecryptionException);
    }
}
