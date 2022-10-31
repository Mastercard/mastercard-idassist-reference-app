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

package com.mastercard.developer.exception;

import com.mastercard.dis.mids.ApiException;
import com.mastercard.dis.mids.JSON;
import com.mastercard.dis.mids.model.Error;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExceptionUtil {

    public ServiceException logAndConvertToServiceException(ApiException e) {
        log.error("Error wile processing request {} {}", e.getMessage(), e.getResponseBody());
        return new ServiceException(e, deserializeErrors(e.getResponseBody()));
    }

    private Error deserializeErrors(String body) {
        return JSON.deserialize(body, Error.class);
    }
}
