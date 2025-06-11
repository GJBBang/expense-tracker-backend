package com.project.abook.global.util;


import com.project.abook.global.exception.BusinessException;
import com.project.abook.global.exception.ErrorCode;

import java.util.function.Supplier;

public class ServiceUtils {

    public static void throwIfExists(Supplier<Boolean> existsCheck, ErrorCode errorCode) {
        if (existsCheck.get()) {
            throw new BusinessException(errorCode);
        }
    }
}
