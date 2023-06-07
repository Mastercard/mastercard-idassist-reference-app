package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.service.DataExtractionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_CONSENT;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_SELFIE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_DOCUMENT_IMAGES;
import static com.mastercard.developer.example.DataExtractionsServiceExample.RETRIEVE_FACEMAP;
import static com.mastercard.developer.example.DataExtractionsServiceExample.DOCUMENT_TYPE_DRIVING_LICENSE;
import static com.mastercard.developer.example.DataExtractionsServiceExample.USER_SELECTED_COUNTRY;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataExtractionOption implements MenuOption {

    private final DataExtractionsService dataExtractionsService;

    @Override
    public void choose(Scanner scanner) {
        log.info(" <--- Enter SCAN ID and press ENTER: ");
        String scanID = scanner.nextLine();
        dataExtractionsService.dataExtractionAccessToken(DataExtractionsServiceExample.getAccessTokenExample());
        dataExtractionsService.extractScannedDocumentData(scanID, USER_CONSENT, RETRIEVE_SELFIE, RETRIEVE_DOCUMENT_IMAGES, RETRIEVE_FACEMAP, DOCUMENT_TYPE_DRIVING_LICENSE, USER_SELECTED_COUNTRY);
    }

    @Override
    public String getOptionName() {
        return "Document Data Extraction";
    }
}
