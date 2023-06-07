package com.mastercard.developer.menu.option;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class ExitOptionTest {
    @InjectMocks
    ExitOption exitOption;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void exit_getOptionName_resultMatches() {
        assertEquals("Exit",exitOption.getOptionName());
    }

    @Test
    void exit_getOptionName_changeExitMenuOptionFlag() {
        boolean exitBefore = exitOption.isExitApplication();
        exitOption.choose(scanner);
        boolean exitAfter = exitOption.isExitApplication();
        assertTrue(exitAfter);
        assertNotEquals(exitBefore, exitAfter);
    }

}