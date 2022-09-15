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

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import com.mastercard.developer.ApiException;
import com.mastercard.developer.JSON;
import com.mastercard.developer.model.Error;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionUtilTest {

    private static final String ERROR_MESSAGE = "error message";
    private static final int ERROR_CODE = 1;
    private static final String RESPONSE_BODY = "response body";

    @Captor
    private ArgumentCaptor<ILoggingEvent> loggingEventCaptor;
    @Mock
    private Appender<ILoggingEvent> mockedAppender;
    @Mock
    private JSON json;

    @InjectMocks
    private ExceptionUtil exceptionUtil;

    @BeforeEach
    public void setUp() {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(ExceptionUtil.class);
        root.addAppender(mockedAppender);
    }

    @Test
    void logAndConvertToServiceException_notNullApiException_loggingAndReturningServiceException() {
        ApiException apiExceptionExample = createApiExceptionExample();
        Error errorExample = new Error();
        when(json.deserialize(eq(RESPONSE_BODY), eq(Error.class))).thenReturn(errorExample);

        ServiceException serviceException = exceptionUtil.logAndConvertToServiceException(apiExceptionExample);

        verify(mockedAppender, times(1)).doAppend(loggingEventCaptor.capture());
        assertEquals("Error wile processing request " + apiExceptionExample.getMessage() + " " + apiExceptionExample.getResponseBody(), loggingEventCaptor.getValue().getFormattedMessage());
        assertEquals(apiExceptionExample, serviceException.getCause());
        assertEquals(errorExample, serviceException.getErrors());
    }

    private ApiException createApiExceptionExample() {
        return new ApiException(ERROR_MESSAGE, ERROR_CODE, Collections.emptyMap(), RESPONSE_BODY);
    }
}