package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.TrustScoreExample;
import com.mastercard.developer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class TrustScoreOption implements MenuOption {

    private final UserService idVerifyUserService;

    @Override
    public void choose(Scanner scanner) {
        idVerifyUserService.userTrustScore(TrustScoreExample.getTrustScoreUserInfo());
    }

    @Override
    public String getOptionName() {
        return "Trust Score";
    }
}
