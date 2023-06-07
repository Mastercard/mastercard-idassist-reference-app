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

import com.mastercard.dis.mids.model.id.verification.Error;
import com.mastercard.dis.mids.model.id.verification.Errors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ServiceExceptionTest {

    private static final String MESSAGE = "The service had an internal exception";
    private static final String SOURCE = "MRS";
    private static final String REASON_CODE = "INVALID_FORMAT";
    private static final String DESCRIPTION = "Member ICA should be numeric and less than or equal to 11 digit.";

    @Test
    void testServiceExceptionMessage() {
        ServiceException exception = new ServiceException(MESSAGE);
        assertEquals(MESSAGE, exception.getMessage());
    }

    @Test
    void testServiceExceptionThrowable() {
        try {
            throwException();
        } catch (ServiceException e) {
            Assertions.assertAll(
                    () -> assertEquals(MESSAGE, e.getMessage()),
                    () -> {
                        Errors errors = e.getErrors();
                        assertNotNull(errors);
                        List<Error> errorList = errors.getError();
                        assertFalse(errorList.isEmpty());
                        errorList.forEach(error -> {
                            assertEquals(SOURCE, error.getSource());
                            assertEquals(REASON_CODE, error.getReasonCode());
                            assertEquals(DESCRIPTION, error.getDescription());
                            assertFalse(Objects.requireNonNull(error.getRecoverable()));
                        });
                    }
            );
        }
    }

    private void throwException() throws ServiceException {
        throw new ServiceException(MESSAGE, getCustomError());
    }

    private Errors getCustomError() {
        Error error = new Error()
                .source(SOURCE)
                .reasonCode(REASON_CODE)
                .description(DESCRIPTION)
                .recoverable(false);
        return new Errors().error(Collections.singletonList(error));
    }
}
