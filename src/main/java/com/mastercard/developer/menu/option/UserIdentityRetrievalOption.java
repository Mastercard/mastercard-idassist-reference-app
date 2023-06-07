package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.UserExample;
import com.mastercard.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class UserIdentityRetrievalOption implements MenuOption {

    private final UserService idVerifyUserService;

    @Override
    public void choose(Scanner scanner) {
        idVerifyUserService.userIdentity(UserExample.getIdentity());
    }

    @Override
    public String getOptionName() {
        return "User Identity Retrieval";
    }
}
