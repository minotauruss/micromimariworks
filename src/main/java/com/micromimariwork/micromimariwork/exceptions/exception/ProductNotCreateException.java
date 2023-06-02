package com.micromimariwork.micromimariwork.exceptions.exception;

import com.micromimariwork.micromimariwork.enums.Language;
import com.micromimariwork.micromimariwork.exceptions.enums.IFriendlyMessageCode;
import com.micromimariwork.micromimariwork.exceptions.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotCreateException extends RuntimeException{
    private  final Language language;
    private  final IFriendlyMessageCode friendlyMessageCode;

    public ProductNotCreateException(Language language, IFriendlyMessageCode friendlyMessageCode, String message) {
        super(FriendlyMessageUtils.getFriendlyMessage(language,friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
        log.error("[ProductExceptionMessage} -> message{}, developer message{}",FriendlyMessageUtils.getFriendlyMessage(language,friendlyMessageCode),message);
    }
}
