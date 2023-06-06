package com.micromimariwork.micromimariwork.exceptions.handler;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.exceptions.enums.FriendlyMessageCodes;
import com.micromimariwork.micromimariwork.exceptions.exception.ProductNotCreateException;
import com.micromimariwork.micromimariwork.exceptions.exception.ProductNotFoundException;
import com.micromimariwork.micromimariwork.exceptions.utils.FriendlyMessageUtils;
import com.micromimariwork.micromimariwork.respond.FriendlyMessage;
import com.micromimariwork.micromimariwork.respond.InternalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collection;
import java.util.Collections;

@RestControllerAdvice
public class GlobalHandlerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotCreateException.class)
    private InternalApiResponse<String> handleProductNotCreateException(ProductNotCreateException exception){
        return  InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    private InternalApiResponse<String> handleProductNotFoundException(ProductNotFoundException exception){
        return  InternalApiResponse.<String>builder()
                .friendlyMessage(FriendlyMessage.builder()
                        .title(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), FriendlyMessageCodes.ERROR))
                        .description(FriendlyMessageUtils.getFriendlyMessage(exception.getLanguage(), exception.getFriendlyMessageCode()))
                        .build())
                .httpStatus(HttpStatus.NOT_FOUND)
                .hasError(true)
                .errorMessage(Collections.singletonList(exception.getMessage()))
                .build();
    }

}
