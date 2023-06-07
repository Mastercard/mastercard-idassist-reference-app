package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.UserExample;
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
class UserIdentityRetrievalOptionTest {
    @InjectMocks
    UserIdentityRetrievalOption userIdentityRetrievalOption;

    @Mock
    private UserService idVerifyUserServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void idVerifyUserIdentityRetrieval_userIdentityRetrieval_successfulResponse() {
        userIdentityRetrievalOption.choose(scanner);
        verify(idVerifyUserServiceMock, times(1)).userIdentity(UserExample.getIdentity());
    }

    @Test
    void idVerifyUserIdentityRetrieval_getOptionName_resultMatches() {
        assertEquals("User Identity Retrieval", userIdentityRetrievalOption.getOptionName());
    }
}
