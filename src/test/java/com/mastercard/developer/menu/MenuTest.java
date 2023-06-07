package com.mastercard.developer.menu;

import com.mastercard.developer.menu.option.MenuOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class MenuTest {
    @InjectMocks
    Menu menuMock;

    @Mock
    MenuOptionProvider menuOptionProviderMock;

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    void console_showMenu_works() {
        MenuOption optionMock = mock(MenuOption.class);
        Map<String, MenuOption> entries = new LinkedHashMap<>();
        entries.put("0", optionMock);

        when(optionMock.getOptionName()).thenReturn("Document Data Extraction");
        when(menuOptionProviderMock.getMenuOptions()).thenReturn(entries);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menuMock.show();
        verify(menuOptionProviderMock, times(1)).getMenuOptions();

        boolean hasPressEnterMessage = outContent.toString().lastIndexOf("0) Document Data Extraction") > 0;
        assertTrue(hasPressEnterMessage);
    }

    @Test
    void console_showMenu_optionsEmpty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        menuMock.show();
        verify(menuOptionProviderMock, times(1)).getMenuOptions();

        boolean hasPressEnterMessage = outContent.toString().lastIndexOf("1)   'ID Assist' User Identity Retrieval") > 0;
        assertFalse(hasPressEnterMessage);
    }

    @Test
    void console_chooseOption_works() {
        String input = "17";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            menuMock.chooseOption(scanner);
            verify(menuOptionProviderMock, times(1)).getMenuOptions();
        }
    }

    @Test
    void console_showPressAnyKeyMessage_works() {
        String input = "\n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            menuMock.showPressAnyKeyMessage(scanner);
            boolean hasPressEnterMessage = outContent.toString().lastIndexOf("Press ENTER to continue...") > 0;
            assertTrue(hasPressEnterMessage);
        }
    }

    @Test
    void console_showPressAnyKeyMessage_works_no_content() {
        String input = "\n\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(menuMock.isExitApplication()).thenReturn(true);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            menuMock.showPressAnyKeyMessage(scanner);
            assertTrue(outContent.toString().isEmpty());
        }
    }


    @Test
    void console_doChooseOption_works() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        MenuOption menuOptionMock = Mockito.mock(MenuOption.class);
        Map<String, MenuOption> menuOptions = new HashMap<>();
        menuOptions.put("1", menuOptionMock);
        when(menuOptionProviderMock.getMenuOptions()).thenReturn(menuOptions);
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            menuMock.chooseOption(scanner);
            verify(menuOptionProviderMock, times(1)).getMenuOptions();
            verify(menuOptionMock, times(1)).choose(scanner);
        }
    }

    @Test
    void console_doChooseOption_throwsException() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        MenuOption menuOptionMock = Mockito.mock(MenuOption.class);
        Map<String, MenuOption> menuOptions = new HashMap<>();
        menuOptions.put("1", menuOptionMock);
        when(menuOptionProviderMock.getMenuOptions()).thenReturn(menuOptions);
        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            given(menuOptionMock.getOptionName()).willAnswer(invocationOnMock -> {
                throw new Exception("Test Exception");
            });
            assertThrows(Exception.class, () -> menuMock.chooseOption(scanner));
        }
    }
}
