package com.mastercard.developer.menu.option;


import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.service.DataExtractionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataExtractionOptionForWebTest {
    @InjectMocks
    DataExtractionForWebOption dataExtractionForWebOption;

    @Mock
    private DataExtractionsService dataExtractionsServiceMock;

    private final Scanner scanner = new Scanner(System.in, "UTF-8");

    @Test
    void dataExtraction_extractAccessTokenForWeb_successfulResponse() {
        dataExtractionForWebOption.choose(scanner);
        verify(dataExtractionsServiceMock, times(1)).dataExtractionAccessTokenForWeb(DataExtractionsServiceExample.getAccessTokenExampleForWeb());
    }

    @Test
    void dataExtraction_getOptionName_resultMatches() {
        assertEquals("Document Data Extraction For Web",dataExtractionForWebOption.getOptionName());
    }
}
