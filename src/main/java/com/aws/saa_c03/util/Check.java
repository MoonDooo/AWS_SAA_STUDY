package com.aws.saa_c03.util;

import com.aws.saa_c03.dto.CustomException;

public final class Check {
    public static void notNull(Object o, CustomException e){
        if (o == null) {
            throw e;
        }
    }
}
