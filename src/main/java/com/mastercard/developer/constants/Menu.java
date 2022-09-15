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

package com.mastercard.developer.constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {

    private final LinkedHashMap<String, String> menuMap;

    public Menu() {
        menuMap = new LinkedHashMap<>();
        menuMap.put("1", "1)   User Identity Retrieval");
        menuMap.put("2", "2)   User Identity Verification");
        menuMap.put("3", "3)   Trust Score");
        menuMap.put("4", "4)   SMS OTP");
        menuMap.put("5", "5)   Device Authentication");
        menuMap.put("6", "6)   Document Data Extraction");
        menuMap.put("7", "7)   Medicare Card Verification");
        menuMap.put("8", "8)   Passport Verification");
        menuMap.put("9", "9)   Driving License Verification");
        menuMap.put("10", "10)  Exit");
    }

    public Map<String, String> get() {
        return menuMap;
    }
}
