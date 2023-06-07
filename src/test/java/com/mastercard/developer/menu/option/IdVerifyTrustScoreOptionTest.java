package com.mastercard.developer.menu.option;


import com.mastercard.developer.example.TrustScoreExample;
import com.mastercard.developer.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class IdVerifyTrustScoreOptionTest {
    @InjectMocks
    TrustScoreOption trustScoreOption;

    @Mock
    private UserService idVerifyUserServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void idVerifyTrustScoreService_performTrustScore_successfulResponse() {
        trustScoreOption.choose(scanner);
        verify(idVerifyUserServiceMock, times(1)).userTrustScore(TrustScoreExample.getTrustScoreUserInfo());
    }

    @Test
    void idVerifyTrustScoreService_getOptionName_resultMatches() {
        assertEquals("Trust Score", trustScoreOption.getOptionName());
    }
}
