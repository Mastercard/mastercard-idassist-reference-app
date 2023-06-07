package com.mastercard.developer.menu.option;

import java.util.Scanner;

public interface MenuOption {
    void choose(Scanner scanner);
    String getOptionName();
}
