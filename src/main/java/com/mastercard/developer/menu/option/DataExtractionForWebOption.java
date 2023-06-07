package com.mastercard.developer.menu.option;

import com.mastercard.developer.example.DataExtractionsServiceExample;
import com.mastercard.developer.service.DataExtractionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class DataExtractionForWebOption implements MenuOption {

    private final DataExtractionsService dataExtractionsService;

    @Override
    public void choose(Scanner scanner) {
        dataExtractionsService.dataExtractionAccessTokenForWeb(DataExtractionsServiceExample.getAccessTokenExampleForWeb());
    }

    @Override
    public String getOptionName() {
        return "Document Data Extraction For Web";
    }
}
