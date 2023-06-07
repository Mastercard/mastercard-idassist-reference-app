package com.mastercard.developer.menu.option;


import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.service.DataExtractionsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static com.mastercard.developer.example.DataExtractionsServiceExample.SCAN_ID;
import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_CONSENT;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_SELFIE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_DOCUMENT_IMAGES;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_FACEMAP;
import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_TYPE_DRIVING_LICENSE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_SELECTED_COUNTRY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DataExtractionOptionTest {
    @InjectMocks
    DataExtractionOption dataExtractionOption;

    @Mock
    DataExtractionsService dataExtractionsServiceMock;

    private final Scanner scanner = new Scanner(new BufferedInputStream(new ByteArrayInputStream(SCAN_ID.getBytes())), "UTF-8");

    @Test
    void dataExtraction_extractAccessTokenAndScannedDocumentData_successfulResponse() {

        dataExtractionOption.choose(scanner);
        verify(dataExtractionsServiceMock, times(1)).dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());
        verify(dataExtractionsServiceMock, times(1)).extractScannedDocumentData(SCAN_ID, USER_CONSENT, RETRIEVE_SELFIE, RETRIEVE_DOCUMENT_IMAGES, RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, USER_SELECTED_COUNTRY);
    }

    @Test
    void dataExtraction_getOptionName_resultMatches() {
        assertEquals("Document Data Extraction", dataExtractionOption.getOptionName());
    }
}
