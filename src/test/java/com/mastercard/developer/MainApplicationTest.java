package com.mastercard.developer;

import com.mastercard.developer.constants.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainApplicationTest {

    private static Map<String, String> menuMapTest = new HashMap<String, String>() {{
       put("1", "1)   User Identity Retrieval");
       put("2", "2)   User Identity Verification");
       put("3", "3)   Trust Score");
       put("4", "4)   SMS OTP");
       put("5", "5)   Device Authentication");
       put("6", "6)   Document Data Extraction");
       put("7", "7)   Medicare Card Verification");
       put("8", "8)   Passport Verification");
       put("9", "9)   Driving License Verification");
       put("10", "10)  Exit");
    }};

    @Test
    void consoleMenu_runAndcheckingValues_works() {
        Map<String, String> menu = new Menu().get();
        for (Map.Entry<String, String> entry : menuMapTest.entrySet()) {
            String valueMenu = menu.get(entry.getKey());
            Assertions.assertEquals(valueMenu, entry.getValue());
        }
    }

    @Test
    void console_showMenu_works() {
        Application spyMIDSReferenceApplication = spy(new Application(null));
        spyMIDSReferenceApplication.showMenu();
        verify(spyMIDSReferenceApplication, times(1)).showMenu();
    }

    @Test
    void console_handleOption_works() {
        Application spyMIDSReferenceApplication = spy(new Application(null));
        menuMapTest.put("99", "Invalid option!");
        for (Map.Entry<String, String> entry : menuMapTest.entrySet()) {
            spyMIDSReferenceApplication.handleOption(entry.getKey());
        }
        verify(spyMIDSReferenceApplication, times(menuMapTest.size())).handleOption(anyString());
    }

}
