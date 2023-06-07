/*
 Copyright (c) 2021 Mastercard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.mastercard.developer.menu;

import com.mastercard.developer.menu.option.MenuOption;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

@Slf4j
public class Menu {

    private final MenuOptionProvider menuOptionProvider;
    private static final String ERROR = "Error : ";

    public Menu(MenuOptionProvider menuOptionProvider) {
        this.menuOptionProvider = menuOptionProvider;
    }

    public void show() {
        log.info(" <--- Welcome to ID Reference APP --->");
        menuOptionProvider.getMenuOptions().forEach((key, value) -> log.info(key + ") " + value.getOptionName()));
        log.info(" ---> Type your option and press ENTER: ");
    }

    public void chooseOption(Scanner scanner) {
        String option = scanner.nextLine();
        log.info("Your option : " + option);
        Map<String, MenuOption> options = menuOptionProvider.getMenuOptions();
        MenuOption menuOption = options.getOrDefault(option, null);
        Optional.ofNullable(menuOption).ifPresent(opt -> doChooseOption(opt, scanner));
    }

    public void showPressAnyKeyMessage(Scanner sc) {
        if (!isExitApplication()) {
            log.info("Press ENTER to continue...");
            sc.nextLine();
        }
    }

    void doChooseOption(MenuOption option, Scanner scanner) {
        try {
            log.info(stylize(option.getOptionName() + " Started"));
            option.choose(scanner);
            log.info(stylize(option.getOptionName() + " Ended"));
        } catch (Exception e) {
            log.info(ERROR + e.getMessage());
            log.info(stylize(option.getOptionName() + " Failed Ended"));
        }
    }

    String stylize(String message) {
        return "<<--- " + message + " --->>";
    }

    public boolean isExitApplication(){
        return menuOptionProvider.isExitApplication();
    }
}
