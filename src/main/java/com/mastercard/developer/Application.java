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

package com.mastercard.developer;


import com.mastercard.developer.menu.Menu;
import com.mastercard.developer.menu.MenuOptionProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.exit(0);
    }

    public Application(MenuOptionProvider menuOptionProvider) {
        this.menu = new Menu(menuOptionProvider);
    }

    @Override
    public void run(String... args) {
        try(Scanner scanner = new Scanner(System.in, "UTF-8")) {

            while (!menu.isExitApplication()) {
                menu.show();
                menu.chooseOption(scanner);
                menu.showPressAnyKeyMessage(scanner);
            }
        }
    }
}