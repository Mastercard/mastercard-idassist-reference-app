package com.mastercard.developer.menu.option;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ExitOption implements MenuOption {

    @Getter
    @Setter
    private boolean exitApplication = false;

    @Override
    public void choose(Scanner scanner) {
        setExitApplication(true);
    }

    @Override
    public String getOptionName() {
        return "Exit";
    }
}
