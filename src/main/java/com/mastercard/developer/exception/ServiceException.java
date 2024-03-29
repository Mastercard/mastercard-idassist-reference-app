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

import com.mastercard.dis.mids.model.id.verification.ApiError;
import com.mastercard.dis.mids.model.id.verification.Errors;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final transient Errors errors = new Errors();

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Errors serviceErrors) {
        super(message);
        errors.setError(serviceErrors.getError());
    }

    public ServiceException(Exception e, Errors serviceErrors) {
        super(e);
        errors.setError(serviceErrors.getError());
    }

    public ServiceException(Exception e, ApiError deserializeErrors) {
        super(e);
        errors.setError(deserializeErrors.getErrors().getError());
    }
}
