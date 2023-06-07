package com.mastercard.developer.menu;

import com.mastercard.developer.menu.option.ExitOption;
import com.mastercard.developer.menu.option.MenuOption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuOptionProviderTest {
    @InjectMocks
    MenuOptionProvider menuOptionProvider;

    @Mock
    ExitOption exitOptionMock;

    @Test
    void when_getMenuOptions_shouldReturnMap() {
        Map<String, MenuOption> menuOptions = menuOptionProvider.getMenuOptions();
        assertEquals(14, menuOptions.size());
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void when_getting_exitApplication_should_return_boolean_flag(boolean exit) {
        when(exitOptionMock.isExitApplication()).thenReturn(exit);
        assertEquals(exit, menuOptionProvider.isExitApplication());
    }

}
